DELETE
FROM meals;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('21.01.2021 07:00', 'Завтрак', 600, 100000),
       ('21.01.2021 12:00', 'Обед', 750, 100000),
       ('21.01.2021 18:30', 'Ужин', 500, 100000),
       ('20.01.2021 13:00', 'breakfast', 800, 100001),
       ('20.01.2021 17:00', 'lunch', 600, 100001),
       ('20.01.2021 21:20', 'dinner', 500, 100001);