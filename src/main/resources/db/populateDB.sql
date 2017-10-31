DELETE FROM user_roles;
DELETE FROM todos;
DELETE FROM tasks;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100001);

INSERT INTO todos (title, user_id) VALUES
  ('Скоро и поспим', 100000),
  ('Не проспи', 100000),
  ('Пиши код', 100000),
  ('Ты не должен это видеть', 100001);

INSERT INTO tasks (title, complete, todo_id) VALUES
  ('Задача 1', TRUE ,100003),
  ('Задача 2', FALSE, 100003),
  ('Задача 3', FALSE, 100003),
  ('Задача админа 1', FALSE, 100004);