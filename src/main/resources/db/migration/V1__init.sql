
CREATE SEQUENCE hibernate_sequence;

CREATE TABLE Topic(
  id bigint PRIMARY KEY,
  title VARCHAR(200),
  nickname VARCHAR(100),
  email VARCHAR(100),
  content VARCHAR(2500),
  secret VARCHAR(20),
  atime DATETIME NOT NULL
);

CREATE TABLE Message:(
  id bigint PRIMARY KEY,
  topic_id bigint NOT NULL REFERENCES Topic (id),
  content VARCHAR(2500),
  nickname VARCHAR(100),
  email VARCHAR(100),
  secret VARCHAR(20),
  atime DATETIME NOT NULL
);
