DELETE FROM user_roles;
DELETE FROM todos;
DELETE FROM tasks;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
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