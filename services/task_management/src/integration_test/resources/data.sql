insert into users (external_id, name)
values
('external_id_1', 'John'),
('external_id_2', 'David');

insert into boards (id, name, icon_slug, created_at, updated_at)
values
(1001, 'test board 1', 'icon_1',now(), now()),
(1002, 'test board 2', 'icon_2',now(), now()),
(1003, 'test board 3', 'icon_3',now(), now()),
(1004, 'test board 4', 'icon_4',now(), now());

insert into users_boards (user_id, board_id, role)
values
(1, 1001, 'observer'),
(1, 1002, 'owner'),
(1, 1003, 'member'),
(2, 1001, 'owner'),
(2, 1004, 'owner');