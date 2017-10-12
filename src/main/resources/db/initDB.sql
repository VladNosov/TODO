DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS todos;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                  NOT NULL,
  email            VARCHAR                  NOT NULL,
  password         VARCHAR                  NOT NULL,
  registered       TIMESTAMP DEFAULT now()  NOT NULL,
  enabled          BOOL DEFAULT TRUE        NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE todos (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id     INTEGER   NOT NULL,
  title       TEXT      NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE tasks (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  todo_id     INTEGER             NOT NULL,
  title       TEXT                NOT NULL,
  enabled     BOOL DEFAULT TRUE   NOT NULL,
  FOREIGN KEY (todo_id) REFERENCES todos (id) ON DELETE CASCADE
);