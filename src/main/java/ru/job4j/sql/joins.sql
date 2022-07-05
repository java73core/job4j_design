create table employees (
    id serial primary key,
    name varchar(255)
);

create table departments (
    id serial primary key,
    name varchar(255),
    employees_id int  references employees(id)
);

create table teens(
      id serial primary key,
      name varchar(255),
      gender varchar(2),
      employees_id int  references employees(id)
);
insert into employees(name) values ('Kate');
insert into employees(name) values ('igor');
insert into employees(name) values ('Olga');
insert into employees(name) values ('lion');
insert into employees(name) values ('Petr');
insert into employees(name) values ('Lena');
insert into employees(name) values ('Fedot');
insert into employees(name) values ('Alex');
insert into employees(name) values ('Anton');
insert into employees(name) values ('Anna');

insert into teens(name, gender, employees_id) VALUES ('Women', 'W', 1);
insert into teens(name, gender, employees_id) VALUES ('Men', 'M', 2);
insert into teens(name, gender, employees_id) VALUES ('Women', 'W', 3);
insert into teens(name, gender, employees_id) VALUES ('Men', 'M', 4);
insert into teens(name, gender, employees_id) VALUES ('Men', 'M', 5);
insert into teens(name, gender, employees_id) VALUES ('Women', 'W', 6);
insert into teens(name, gender, employees_id) VALUES ('Men', 'M', 7);
insert into teens(name, gender, employees_id) VALUES ('Men', 'M', 8);
insert into teens(name, gender, employees_id) VALUES ('Men', 'M', 9);
insert into teens(name, gender, employees_id) VALUES ('Women', 'W', 10);

insert into  departments(name, employees_id) values('OOO FirstCompany', 1);
insert into  departments(name, employees_id) values('OOO FirstCompany', 2);
insert into  departments(name, employees_id) values('OOO FirstCompany', 3);
insert into  departments(name, employees_id) values('OOO FirstCompany', 4);
insert into  departments(name, employees_id) values('OOO FirstCompany', 5);
insert into  departments(name, employees_id) values('OOO SecondCompany', 6);
insert into  departments(name, employees_id) values('OOO SecondCompany', 7);
insert into  departments(name, employees_id) values('OOO SecondCompany', 8);
insert into  departments(name, employees_id) values('OOO SecondCompany', 9);
insert into  departments(name, employees_id) values('OOO SecondCompany', 10);
insert into  departments(name, employees_id) values('OOO EmptyCompany', null);
insert into  departments(name, employees_id) values('OOO SimpleCompany', null);
insert into  departments(name, employees_id) values('OOO Compan0y', null);

select * from employees e left join departments d on e.id = d.employees_id;

select * from employees e right join departments d on e.id = d.employees_id;

select * from employees e full join departments d on e.id = d.employees_id;

select * from employees e cross join  departments d;

select  d.name Контора, e.name Имя_работника from departments d
   left join employees e on d.employees_id = e.id
group by e.name, d.name
having e.name is null;

select  d.name Контора, e.name Имя_работника from departments d
        left join employees e on d.employees_id = e.id
group by e.name, d.name

select  d.name Контора, e.name Имя_работника from employees e
         right join departments d on e.id = d.employees_id
group by e.name, d.name;

select e.name, t.name from employees e cross join teens t;