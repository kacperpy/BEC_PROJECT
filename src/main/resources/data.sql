INSERT INTO facility_type VALUES
(1, 'restaurant'),
(2, 'cinema'),
(3, 'gym');

INSERT INTO address VALUES
(4, 'warsaw', 'poland', '10', '01-111', 'marszalkowska', '111'),
(5, 'warsaw', 'poland', '11', '02-222', 'dzika', '222'),
(6, 'warsaw', 'poland', '13', '03-333', 'piekna', '333');

INSERT INTO facility VALUES
(6, '1234567890', 'good restaurant', '1234567890', 4, 1),
(7, '1111111111', 'bad cinema', '1111111222', 5, 2),
(8, '2222222222', 'good gym', '1112221212', 6, 3);

INSERT INTO day_open VALUES
(9, 1, {t '10:00:00.00'}, {t '16:00:00.00'}, 6),
(10, 2, {t '10:00:00.00'}, {t '16:00:00.00'}, 6),
(11, 3, {t '10:00:00.00'}, {t '16:00:00.00'}, 6),
(12, 4, {t '10:00:00.00'}, {t '16:00:00.00'}, 6),
(13, 5, {t '10:00:00.00'}, {t '16:00:00.00'}, 6),

(14, 1, {t '10:00:00.00'}, {t '16:00:00.00'}, 7),
(15, 2, {t '10:00:00.00'}, {t '16:00:00.00'}, 7),
(16, 3, {t '10:00:00.00'}, {t '16:00:00.00'}, 7),
(17, 4, {t '10:00:00.00'}, {t '16:00:00.00'}, 7),
(18, 5, {t '10:00:00.00'}, {t '16:00:00.00'}, 7),

(19, 1, {t '10:00:00.00'}, {t '16:00:00.00'}, 8),
(20, 2, {t '10:00:00.00'}, {t '16:00:00.00'}, 8),
(21, 3, {t '10:00:00.00'}, {t '16:00:00.00'}, 8),
(22, 4, {t '10:00:00.00'}, {t '16:00:00.00'}, 8),
(23, 5, {t '10:00:00.00'}, {t '16:00:00.00'}, 8);

INSERT INTO feature VALUES
(24, 'You can book a table', 'book a table', 6),
(25, 'You can buy a movie ticket', 'buy a ticket', 7),
(26, 'You can sign up for a yoga class', 'Sign up for a class', 8);

INSERT INTO bookable_object (id, capacity, description, name, price, reusable, time_period, feature_id) VALUES
(27, 4, 'table for 4 people', 'small table', 100, 0, 60, 24),
(28, 6, 'table for 6 people', 'medium table', 150, 0, 60, 24),
(29, 8, 'table for 8 people', 'large table', 200, 0, 60, 24);

INSERT INTO bookable_object VALUES
(30, 100, {ts '2022-03-20 21:00:00.00'}, 'ticket for spider man',  'movie ticket spiderman', 30,1, 180, 25),
(31, 100, {ts '2022-03-20 20:00:00.00'}, 'ticket for avengers',  'movie ticket avengers', 30, 1, 200, 25),
(32, 100, {ts '2022-04-20 20:15:00.00'}, 'ticket for some horror movie',  'movie ticket some horror movie', 30, 1, 120, 25),
(33, 50, {ts '2022-03-15 15:00:00.00'}, 'yoga class',  'yoga class ticket', 70,1,  90, 26);

INSERT INTO role VALUES
(34, 'admin'),
(35, 'normal_user'),
(36, 'facility_owner');

INSERT INTO user VALUES
(37, {ts '2010-01-17 10:00:00.00'}, 'user1@gmail.com', 'password1', '111222333', 35),
(38, {ts '2011-02-17 10:00:00.00'}, 'user2@gmail.com', 'password2', '333444555', 35),
(39, {ts '2012-03-17 10:00:00.00'}, 'user3@gmail.com', 'password3', '333222111', 35),
(40, {ts '2013-04-17 10:00:00.00'}, 'admin@gmail.com', 'hardpassword1', '555666777', 34),
(41, {ts '2014-06-17 10:00:00.00'}, 'owner@gmail.com', 'hardpassword2', '444333222', 36);

INSERT INTO reservation VALUES
(42, {ts '2022-03-10 15:00:00.00'}, {ts '2022-03-10 16:00:00.00'}, 4, 27, 37),
(43, {ts '2022-03-11 20:00:00.00'}, {ts '2022-03-11 22:00:00.00'}, 1, 32, 37),
(44, {ts '2022-03-13 16:30:00.00'}, {ts '2022-03-13 17:30:00.00'}, 8, 29, 37),

(45, {ts '2022-03-15 15:00:00.00'}, {ts '2022-03-15 16:30:00.00'}, 1, 33, 37),
(46, {ts '2022-03-15 15:00:00.00'}, {ts '2022-03-15 16:30:00.00'}, 1, 33, 38),
(47, {ts '2022-03-15 15:00:00.00'}, {ts '2022-03-15 16:30:00.00'}, 1, 33, 39),

(48, {ts '2022-03-18 13:00:00.00'}, {ts '2022-03-18 14:00:00.00'}, 6, 28, 39),
(49, {ts '2022-03-19 14:00:00.00'}, {ts '2022-03-19 15:00:00.00'}, 4, 27, 39),
(50, {ts '2022-03-20 20:00:00.00'}, {ts '2022-03-20 23:20:00.00'}, 1, 31, 39);