
INSERT INTO Topic (id, title, nickname, email, content, secret, atime) VALUES ((SELECT hibernate_sequence.nextval), 'Test1', 'Curro', 'casascurro@gmail.com', 'Viva España', '%1234Abcd', (CURRENT_TIMESTAMP());
INSERT INTO Topic (id, title, nickname, email, content, secret, atime) VALUES ((SELECT hibernate_sequence.nextval), 'Test2', 'Shireen', 'test2@gmail.com', 'Viva Bellhop', '%1234Asfd', (CURRENT_TIMESTAMP());
INSERT INTO Topic (id, title, nickname, email, content, secret, atime) VALUES ((SELECT hibernate_sequence.nextval), 'Test3', 'Mame', 'test3@gmail.com', 'We are doing it', '%1234Avfb', (CURRENT_TIMESTAMP());

INSERT INTO Message (id, topic_id, content, nickname, email, secret, atime) VALUES ((SELECT hibernate_sequence.nextval), (SELECT id FROM topic), 'Yeah Viva', 'Payam', 'test@gmail.com', '$12Asdfghj',(CURRENT_TIMESTAMP()));
INSERT INTO Message (id, topic_id, content, nickname, email, secret, atime) VALUES ((SELECT hibernate_sequence.nextval), (SELECT id FROM topic), 'Whatever', 'Shireen', 'test2@gmail.com', '$12Asdfghj',(CURRENT_TIMESTAMP()));
INSERT INTO Message (id, topic_id, content, nickname, email, secret, atime) VALUES ((SELECT hibernate_sequence.nextval), (SELECT id FROM topic), 'Just do it', 'Mary', 'test3@gmail.com', '$12Asdfghj',(CURRENT_TIMESTAMP()));
