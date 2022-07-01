create table person(
    id serial primary key,
    name varchar(20),
    surname varchar(30),
    date_birth DATE,
    weight DECIMAL(5, 2)
);
insert into person(name, surname, date_birth, weight) values ('Иван', 'Иванов', '2000-05-05', 75.3);
insert into person(name, surname, date_birth, weight) values ('Петр', 'Петров', '2001-06-01', 74.3);
insert into person(name, surname, date_birth, weight) values ('Пидр', 'Сидоров', '2002-07-02', 78.3);
insert into person(name, surname, date_birth, weight) values ('Вася', 'Васин', '2003-07-03', 70.3);
delete from person where name='Вася';
update person set name = 'Cидр' where name = 'Пидр';