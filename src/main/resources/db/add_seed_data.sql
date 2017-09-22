--liquibase formatted sql

--changeset blackbaud:1
insert into event (id, date, location, capacity, name) values (1, '2017-09-15 03:35:00.000000', 'The Chicago Theater', 2330, 'Swan Lake');
insert into event (id, date, location, capacity, name) values (2, '2017-10-13 09:00:00.000000', 'Dock Street Theater', 245, 'Fiddler on the Roof');
insert into event (id, date, location, capacity, name) values (3, '2016-02-09 05:30:00.000000', 'Bass Concert Hall', 720, 'Romeo & Juliet');
insert into event (id, date, location, capacity, name) values (4, '2017-08-05 07:30:00.000000', 'Metropolitan Opera', 1000, 'Phantom of the Opera');
insert into event (id, date, location, capacity, name) values (5, '2017-08-05 08:30:00.000000', 'Broadway', 658, 'Hamilton');

-- --changeset blackbaud:2
insert into ticket (id, base_price, event_id, capacity) values (1, 15, 1, 60);
insert into ticket (id, base_price, event_id, capacity) values (2, 50, 2, 100);
insert into ticket (id, base_price, event_id, capacity) values (3, 80, 3, 50);
insert into ticket (id, base_price, event_id, capacity) values (4, 100, 4, 1000);
insert into ticket (id, base_price, event_id, capacity) values (5, 89, 5, 100);
