create table car_bodies (
      id serial primary key,
      name varchar(255)
);

create table car_engines (
      id serial primary key,
      name varchar(255)
);

create table car_transmissions (
      id serial primary key,
      name varchar(255)
);

create table car (
      id serial primary key,
      name varchar(255),
      body_id int references car_bodies(id),
      engine_id int references car_engines(id),
      transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Седан');
insert into car_bodies(name) values ('Универсал');
insert into car_bodies(name) values ('Хэтчбек');
insert into car_bodies(name) values ('Лифтбек');
insert into car_bodies(name) values ('Лимузин');
insert into car_bodies(name) values ('Внедорожник');
insert into car_bodies(name) values ('Кроссовер');
insert into car_bodies(name) values ('Стретч');
insert into car_bodies(name) values ('Родстер');
insert into car_bodies(name) values ('Тарга');
insert into car_bodies(name) values ('Пикап');
insert into car_bodies(name) values ('Фургон');
insert into car_bodies(name) values ('Минивэн');
insert into car_bodies(name) values ('Купе');
insert into car_bodies(name) values ('Кабриолет');

insert into car_engines(name) values ('Оппозитный двигатель');
insert into car_engines(name) values ('Бензиновый двигатель');
insert into car_engines(name) values ('Дизельный двигатель');
insert into car_engines(name) values ('Газовый двигатель');
insert into car_engines(name) values ('Гибридный двигатель');
insert into car_engines(name) values ('Электрический двигатель');

insert into car_transmissions(name) values ('Механическая трансмиссия');
insert into car_transmissions(name) values ('Гидромеханическая трансмиссия');
insert into car_transmissions(name) values ('Гидравлическая трансмиссия');
insert into car_transmissions(name) values ('Электромеханическая трансмиссия');
insert into car_transmissions(name) values ('Автоматическая трансмиссия');

insert into car(name, body_id, engine_id, transmission_id) VALUES ('Ларгус', 2, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('Ларгус', 12, 2, 5);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('УАЗ Патриот', 11, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('УАЗ Патриот', 2, null, 1);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('УАЗ Патриот', 6, 3, 4);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('УАЗ Патриот', 6, 5, 1);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('Лада Приора', 1, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('Лада Приора', 2, null, 1);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('Лада Приора', 3, 5, 1);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('Лада Приора', 4, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) VALUES ('Ниссан икстреил', 7, 3, 1);

select * from car;
select c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name from car c
    right join car_bodies cb on cb.id = c.body_id
    left join car_engines ce on ce.id = c.engine_id
    join car_transmissions ct on ct.id = c.transmission_id;

select cb.name body_name from car c
       right join car_bodies cb on cb.id = c.body_id
where c.name is null;

select ce.name body_name from car c
       right join car_engines ce on ce.id = c.engine_id
where c.name is null;

select ct.name body_name from car c
       right join car_transmissions ct on ct.id = c.transmission_id
where c.name is null;
