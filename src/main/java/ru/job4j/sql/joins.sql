create table teens(
       id serial primary key,
       name varchar(255),
       gender varchar(2)
);

create table departments (
       id serial primary key,
       name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
    departments_id int  references departments(id)
);

insert into  departments(name) values('OOO FirstCompany');
insert into  departments(name) values('OOO SecondCompany');
insert into  departments(name) values('OOO EmptyCompany');
insert into  departments(name) values('OOO SimpleCompany');
insert into  departments(name) values('OOO Company');

insert into teens(name, gender) VALUES ('Women', 'W');
insert into teens(name, gender) VALUES ('Men', 'M');

insert into employees(name, departments_id) values ('Kate', 1);
insert into employees(name, departments_id) values ('igor', 2);
insert into employees(name, departments_id) values ('Olga', 2);
insert into employees(name, departments_id) values ('lion', 1);
insert into employees(name, departments_id) values ('Petr', 2);
insert into employees(name, departments_id) values ('Lena', 1);
insert into employees(name, departments_id) values ('Fedot', 2);
insert into employees(name, departments_id) values ('Alex', 1);
insert into employees(name, departments_id) values ('Anton', 2);
insert into employees(name, departments_id) values ('Anna', 1);



select * from employees e left join departments d on e.departments_id = d.id;

select * from employees e right join departments d on e.departments_id = d.id;

select * from employees e full join departments d on e.departments_id = d.id;

select * from employees e cross join  departments d;

select  d.name Контора, e.name Имя_работника from departments d
   left join employees e on d.id = e.departments_id
where e.departments_id is null;

select  d.name Контора, e.name Имя_работника from departments d
        left join employees e on d.id = e.departments_id;


select  d.name Контора, e.name Имя_работника from employees e
         right join departments d on d.id = e.departments_id;

select t1.name, t2.name from teens t1 cross join teens t2
where t1.name != t2.name;