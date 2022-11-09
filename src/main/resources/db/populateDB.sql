DELETE FROM vote;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurant (id, name)
VALUES (100002, 'Tasty and that''s it'),
       (100003, 'Praga restaurant');

INSERT INTO VOTE (restaurant_id, user_id, date)
VALUES (100002, 100000, NOW()),
       (100003, 100000, NOW() - INTERVAL '1' DAY),
       (100002, 100000, NOW() - INTERVAL '2' DAY),
       (100003, 100000, NOW() - INTERVAL '3' DAY),
       (100002, 100000, NOW() - INTERVAL '4' DAY),
       (100003, 100001, NOW()),
       (100003, 100001, NOW() - INTERVAL '1' DAY),
       (100002, 100001, NOW() - INTERVAL '2' DAY),
       (100002, 100001, NOW() - INTERVAL '3' DAY);