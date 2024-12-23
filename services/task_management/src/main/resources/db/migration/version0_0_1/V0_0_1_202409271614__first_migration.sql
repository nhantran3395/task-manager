CREATE TABLE "boards" (
  "id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "name" varchar NOT NULL,
  "icon_slug" varchar,
  "created_at" timestamp DEFAULT now(),
  "updated_at" timestamp
);

CREATE TABLE "users" (
  "id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "external_id" varchar UNIQUE NOT NULL,
  "name" varchar NOT NULL,
  "created_at" timestamp DEFAULT now(),
  "updated_at" timestamp
);

CREATE TABLE "users_boards" (
  "user_id" bigint,
  "board_id" bigint,
  "role" varchar NOT NULL CHECK ("role" IN ('owner', 'member', 'observer', 'guess'))
);

CREATE TABLE "tags" (
  "id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "board_id" bigint,
  "label" varchar NOT NULL,
  "slug" varchar NOT NULL,
  "created_at" timestamp DEFAULT now(),
  "updated_at" timestamp
);

CREATE TABLE "tasks" (
  "id" BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "title" varchar NOT NULL,
  "description" text NOT NULL,
  "status" varchar DEFAULT 'new' CHECK ("status" IN ('new', 'todo', 'in_progress', 'in_review', 'ready_for_test', 'in_testing','completed', 'cancelled', 'on_hold')),
  "previous_status" varchar CHECK ("status" IN ('new', 'todo', 'in_progress', 'in_review', 'ready_for_test', 'in_testing','completed', 'cancelled', 'on_hold')),
  "thumbnail_url" varchar,
  "created_at" timestamp DEFAULT now(),
  "updated_at" timestamp,
  "board_id" bigint
);

CREATE TABLE "tasks_tags" (
  "task_id" bigint,
  "tag_id" bigint
);

ALTER TABLE "users_boards" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "users_boards" ADD FOREIGN KEY ("board_id") REFERENCES "boards" ("id");

ALTER TABLE "tasks" ADD FOREIGN KEY ("board_id") REFERENCES "boards" ("id");

ALTER TABLE "tags" ADD FOREIGN KEY ("board_id") REFERENCES "boards" ("id");

ALTER TABLE "tasks_tags" ADD FOREIGN KEY ("task_id") REFERENCES "tasks" ("id");

ALTER TABLE "tasks_tags" ADD FOREIGN KEY ("tag_id") REFERENCES "tags" ("id");
