create table type(
                     id serial primary key,
                     name varchar(255)
);
create table product(
                        id serial primary key,
                        name varchar(255),
                        type_id int references type(id),
                        expired_date date,
                        price float
                    );

insert into type(name) values ('сыр');
insert into type(name) values ('молоко');
insert into type(name) values ('масло');
insert into type(name) values ('мороженое');
insert into type(name) values ('сметана');
insert into type(name) values ('кефир');
insert into type(name) values ('ряженка');
insert into product(name, type_id, expired_date, price) VALUES ('сыр плавленный', 1, '2022-08-10', 120.00);
insert into product(name, type_id, expired_date, price) VALUES ('cыр моцарелла', 1, '2022-08-20', 190.00);
insert into product(name, type_id, expired_date, price) VALUES ('сыр российский', 1, '2022-08-17', 100.00);
insert into product(name, type_id, expired_date, price) VALUES ('сыр гауда', 1, '2022-08-19', 150.00);
insert into product(name, type_id, expired_date, price) VALUES ('сыр творожный', 1, '2022-08-01', 200.00);

insert into product(name, type_id, expired_date, price) VALUES ('молоко 3,2', 2, '2022-07-07', 62.00);
insert into product(name, type_id, expired_date, price) VALUES ('молоко 0,1', 2, '2022-07-09', 58.00);
insert into product(name, type_id, expired_date, price) VALUES ('молоко 2,5', 2, '2022-07-05', 60.00);
insert into product(name, type_id, expired_date, price) VALUES ('молоко 3,2 кошкинское', 2, '2022-07-05', 72.00);
insert into product(name, type_id, expired_date, price) VALUES ('молоко 3,2 простоквашено', 2, '2022-07-07', 81.00);

insert into product(name, type_id, expired_date, price) VALUES ('масло сливочное', 3, '2022-09-02', 240.00);
insert into product(name, type_id, expired_date, price) VALUES ('масло подсолнечное', 3, '2022-12-20', 200.00);
insert into product(name, type_id, expired_date, price) VALUES ('масло растительное', 3, '2022-12-17', 190.00);
insert into product(name, type_id, expired_date, price) VALUES ('масло оливковое', 3, '2023-01-10', 320.00);
insert into product(name, type_id, expired_date, price) VALUES ('масло моторное', 3, '2023-12-01', 400.00);

insert into product(name, type_id, expired_date, price) VALUES ('мороженое шоколадное', 4, '2022-09-01', 240.00);
insert into product(name, type_id, expired_date, price) VALUES ('мороженое кремовое', 4, '2022-09-04', 200.00);
insert into product(name, type_id, expired_date, price) VALUES ('мороженое фисташковой', 4, '2022-09-03', 190.00);
insert into product(name, type_id, expired_date, price) VALUES ('мороженое крем-брюле', 4, '2022-09-05', 320.00);
insert into product(name, type_id, expired_date, price) VALUES ('мороженое малиновое', 4, '2022-09-01', 400.00);

insert into product(name, type_id, expired_date, price) VALUES ('сметана кошкинская', 5, '2022-09-02', 130.00);
insert into product(name, type_id, expired_date, price) VALUES ('сметана простоквашено', 5, '2022-09-03', 140.00);
insert into product(name, type_id, expired_date, price) VALUES ('сметана здоровое лето', 5, '2022-09-04', 120.00);
insert into product(name, type_id, expired_date, price) VALUES ('сметана веселая корова', 5, '2022-09-01', 145.00);
insert into product(name, type_id, expired_date, price) VALUES ('сметана классическая', 5, '2022-09-02', 150.00);

insert into product(name, type_id, expired_date, price) VALUES ('кефир кошкинский', 6, '2022-07-12', 100.00);
insert into product(name, type_id, expired_date, price) VALUES ('кефир простоквашено', 6, '2022-07-13', 87.00);
insert into product(name, type_id, expired_date, price) VALUES ('кефир здоровое лето', 6, '2022-07-14', 90.00);
insert into product(name, type_id, expired_date, price) VALUES ('кефир веселая корова', 6, '2022-07-11', 110.00);
insert into product(name, type_id, expired_date, price) VALUES ('кефир классическая', 6, '2022-07-12', 75.00);

insert into product(name, type_id, expired_date, price) VALUES ('ряженка кошкинская', 7, '2022-07-12', 58.00);
insert into product(name, type_id, expired_date, price) VALUES ('ряженка простоквашено', 7, '2022-07-13', 52.00);
insert into product(name, type_id, expired_date, price) VALUES ('ряженка здоровое лето', 7, '2022-07-14', 47.00);
insert into product(name, type_id, expired_date, price) VALUES ('ряженка веселая корова', 7, '2022-07-11', 50.00);
insert into product(name, type_id, expired_date, price) VALUES ('ряженка классическая', 7, '2022-07-12', 45.00);
insert into product(name, type_id, expired_date, price) VALUES ('ряженка классическая', 7, '2021-07-12', 45.00);

select p.name Продукт, t.name Категория from product p inner join type t on t.id = p.type_id group by p.name, t.name
having t.name = 'сыр';

select p.name Продукт from product p where p.name like '%мороженое%';

select p.name, p.expired_date as date
from product p
group by p.name, p.expired_date having p.expired_date < CURRENT_DATE;

select p.name as Продукт, p.price Цена
from product p
group by p.name, p.price
having  p.price = (select max(price)
                   from product);

select t.name Тип, count(t.name) Количество
from product p inner join type t on t.id = p.type_id
group by t.name;

select p.name Продукт, t.name Категория from product p inner join type t on t.id = p.type_id
group by p.name, t.name
having t.name = 'сыр' or t.name = 'молоко';

select t.name Тип, count(t.name) Количество
from product p inner join type t on t.id = p.type_id
group by t.name
having count(t.name) < 10;

select t.name Тип, p.name Продукты
from product p inner join type t on t.id = p.type_id
group by t.name, p.name;