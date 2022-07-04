create table fauna (
                       id serial primary key,
                       name text,
                       avg_age int,
                       discovery_date date
);

insert into fauna(name, avg_age, discovery_date) VALUES ('dog', 10, null);
insert into fauna(name, avg_age, discovery_date) VALUES ('cat', 15,  null);
insert into fauna(name, avg_age, discovery_date) VALUES ('fish', 2, '1100-07-07');
insert into fauna(name, avg_age, discovery_date) VALUES ('bird', 13, '800-07-06');
insert into fauna(name, avg_age, discovery_date) VALUES ('tiger', 7, '1300-01-04');
insert into fauna(name, avg_age, discovery_date) VALUES ('leon', 5, '1150-02-09');
insert into fauna(name, avg_age, discovery_date) VALUES ('turtle', 20, '100-01-08');
insert into fauna(name, avg_age, discovery_date) VALUES ('elephant', 10, '1200-06-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where  discovery_date is null;
select * from fauna where (SELECT EXTRACT(YEAR FROM discovery_date)) < '1950';