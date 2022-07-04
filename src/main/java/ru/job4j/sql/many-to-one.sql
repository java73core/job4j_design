create table worker(
      id serial primary key,
      name varchar(255),
      experience serial
);

create table job(
      id serial primary key,
      name varchar(255),
      address varchar(255),
      worker_id int references worker(id)
);

insert into worker(name, experience) values ('Ivan', 6);
insert into worker(name, experience) values ('Fedor', 2);
insert into worker(name, experience) values ('Petr', 8);
insert into worker(name, experience) values ('Alex', 1);
insert into worker(name, experience) values ('Dmitry', 0);
insert into worker(name, experience) values ('Yury', 4);
insert into worker(name, experience) values ('Stepan', 5);
insert into job(name, address, worker_id) values ('OOO FirstJob', 'Moscow', 2);
insert into job(name, address, worker_id) values ('OOO SecondJob', 'Kazan', 3);
insert into job(name, address, worker_id) values ('OOO ThirdJob', 'Omsk', 5);
insert into job(name, address, worker_id) values ('OOO FirstJob', 'Moscow', 1);
insert into job(name, address, worker_id) values ('OOO SecondJob', 'Kazan', 6);
insert into job(name, address, worker_id) values ('OOO ThirdJob', 'Omsk', 7);

select pp.name Имя, pp.experience Опыт, p.name Место_работы, p.address Адрес_работы
from worker pp join job p on p.worker_id = pp.id;

select pp.name as Имя, pp.experience as Опыт, p.name as Место_работы, p.address as Адрес_работы
from worker as pp join job as p on p.worker_id = pp.id;

select pp.name Имя, pp.experience Опыт, p.address Адрес_работы
from worker pp join job p on p.worker_id = pp.id
where pp.id = 5
order by Опыт desc;
