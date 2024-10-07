package com.nhantran.task_management;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.BoardAccessRole;
import com.nhantran.task_management.domain.model.UserBoardAccess;
import com.nhantran.task_management.exception.UserNotFoundException;
import com.nhantran.task_management.port.in.command.CreateNewBoardCommand;
import com.nhantran.task_management.port.in.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.port.in.use_case.BoardManagementUseCase;
import com.nhantran.task_management.port.out.BoardManagementPersistencePort;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class BoardManagementServiceTests {
	@Autowired
	private BoardManagementUseCase boardManagementUseCase;

	@Autowired
	private BoardManagementPersistencePort boardManagementPersistencePort;

    @Test
	void givenUserNotExist_whenCreateNewBoard_shouldThrowUserNotFoundException() {
		CreateNewBoardCommand newBoardCommand = new CreateNewBoardCommand(
			"test board",
			"rocket",
			"not_exist_user"
		);

		assertThrows(UserNotFoundException.class, () -> {
			boardManagementUseCase.createBoard(newBoardCommand);
		});
	}

	@Test
	@Transactional
	void givenUserExistAndPayloadIsValid_whenCreateNewBoard_shouldCreateThatBoardWithUserAsOwnerAndReturnNewlyCreatedId() {
		CreateNewBoardCommand newBoardCommand = new CreateNewBoardCommand(
				"test board",
				"rocket",
				"external_id_1"
		);

		var newBoardResult = boardManagementUseCase.createBoard(newBoardCommand);

		assertThat(newBoardResult).isNotNull();
		assertInstanceOf(Long.class, newBoardResult);

		Optional<Board> maybeBoard = boardManagementPersistencePort.findBoard(newBoardResult);
		assertThat(maybeBoard).isPresent();

		Board board = maybeBoard.get();
		Set<UserBoardAccess> accesses = board.getAccesses();
		assertThat(accesses.size()).isEqualTo(1);

		UserBoardAccess access = accesses.stream().toList().getFirst();
		assertThat(access.getUserId()).isEqualTo(1L);
		assertThat(access.getBoardId()).isEqualTo(board.getId());
		assertThat(access.getRole()).isEqualTo(BoardAccessRole.OWNER);
	}

	@Test
	void givenUserNotExist_whenGetListOfBoards_shouldThrowUserNotFoundException() {
		BoardsViewableByUserQuery query = new BoardsViewableByUserQuery("not_exist_user");

		assertThrows(UserNotFoundException.class, () -> {
			boardManagementUseCase.getBoardsViewableByUser(query);
		});
	}

	@Test
	@Transactional
	void givenUserExist_whenGetListOfBoards_shouldReturnOnlyBoardsThatUserCanView() {
		BoardsViewableByUserQuery queryUser1 = new BoardsViewableByUserQuery("external_id_1");
		BoardsViewableByUserQuery queryUser2 = new BoardsViewableByUserQuery("external_id_2");

		List<Board> user1boards = boardManagementUseCase.getBoardsViewableByUser(queryUser1);
		List<Board> user2boards = boardManagementUseCase.getBoardsViewableByUser(queryUser2);

		assertThat(user1boards.size()).isEqualTo(3);
		assertThat(user2boards.size()).isEqualTo(2);
	}
}
