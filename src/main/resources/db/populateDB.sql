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
VALUES ('22.01.2021 00:00', 'Поздний ужин', 500, 100000),
       ('21.01.2021 07:00', 'Завтрак', 600, 100000),
       ('21.01.2021 12:00', 'Обед', 750, 100000),
       ('21.01.2021 18:30', 'Ужин', 500, 100000),
       ('20.01.2021 07:50', 'Завтрак', 500, 100000),
       ('20.01.2021 13:00', 'Обед', 800, 100000),
       ('20.01.2021 17:00', 'Полдник', 250, 100000),
       ('20.01.2021 21:20', 'Ужин', 500, 100000),
       ('21.01.2021 07:00', 'Breakfast', 400, 100001),
       ('21.01.2021 12:00', 'Brunch', 450, 100001),
       ('21.01.2021 14:30', 'Lunch', 500, 100001),
       ('21.01.2021 17:50', 'Dinner', 700, 100001),
       ('20.01.2021 13:00', 'Breakfast', 800, 100001),
       ('20.01.2021 17:00', 'Lunch', 600, 100001),
       ('20.01.2021 21:20', 'Dinner', 500, 100001);