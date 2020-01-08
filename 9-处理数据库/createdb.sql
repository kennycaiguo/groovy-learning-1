create database if not exists weatherinfo;
use weatherinfo;

drop table if exists weather;
create table weather
(
    city        varchar(100) not null,
    temperature integer      not null
);

insert into weather (city, temperature)
values ('Austin', 48),
       ('Baton Rouge', 57),
       ('Jackson', 50),
       ('Montgomery', 53),
       ('Phoenix', 67),
       ('Sacramento', 66),
       ('Santa', 27),
       ('Tallhassee', 59);
