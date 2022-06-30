create table person(
    id serial primary key,
    name varchar,
    surname varchar
);
insert into person(name, surname) values ('Иван', 'Иванов');
insert into person(name, surname) values ('Петр', 'Петров');
insert into person(name, surname) values ('Пидр', 'Сидоров');
insert into person(name, surname) values ('Вася', 'Васин');
delete from person where name='Вася';
update person set name = 'Cидр' where name = 'Пидр';