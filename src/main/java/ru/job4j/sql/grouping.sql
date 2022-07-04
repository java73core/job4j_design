create table devices(
                        id serial primary key,
                        name varchar(255),
                        price float
);

create table people(
                       id serial primary key,
                       name varchar(255)
);

create table devices_people(
                               id serial primary key,
                               device_id int references devices(id),
                               people_id int references people(id)
);

insert into devices(name, price) VALUES ('iphone 1', 15000.00);
insert into devices(name, price) VALUES ('iphone 2', 17000.00);
insert into devices(name, price) VALUES ('iphone 3', 10000.00);
insert into devices(name, price) VALUES ('iphone 4', 4500.00);
insert into devices(name, price) VALUES ('iphone 5', 14000.00);
insert into people(name) VALUES ('Ivan');
insert into people(name) VALUES ('Igor');
insert into people(name) VALUES ('Ilon');
insert into people(name) VALUES ('Ilya');
insert into people(name) VALUES ('Issa');
insert into devices_people(device_id, people_id) VALUES (1, 2);
insert into devices_people(device_id, people_id) VALUES (1, 1);
insert into devices_people(device_id, people_id) VALUES (2, 3);
insert into devices_people(device_id, people_id) VALUES (3, 4);
insert into devices_people(device_id, people_id) VALUES (5, 5);
insert into devices_people(device_id, people_id) VALUES (4, 4);
insert into devices_people(device_id, people_id) VALUES (1, 3);
insert into devices_people(device_id, people_id) VALUES (1, 4);
insert into devices_people(device_id, people_id) VALUES (2, 5);
insert into devices_people(device_id, people_id) VALUES (3, 1);
insert into devices_people(device_id, people_id) VALUES (5, 2);
insert into devices_people(device_id, people_id) VALUES (4, 3);


select avg(d.price) from devices d;

select p.name, avg(d.price)
from people as p join devices_people dp on
        p.id= dp.people_id
    join devices as d on
        dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people as p join devices_people dp on
        p.id= dp.people_id
                 join devices as d on
        dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;





