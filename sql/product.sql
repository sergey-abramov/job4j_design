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

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('ШОКОЛАД');

insert into product(name, type_id, expired_date, price)
values ('Сыр моцарелла', 1, '2023-01-02', 180);
insert into product(name, type_id, expired_date, price)
values ('Сыр моцарелла', 1, '2023-01-06', 180);
insert into product(name, type_id, expired_date, price)
values ('Сыр плавленный', 1, '2023-01-05', 80);
insert into product(name, type_id, expired_date, price)
values ('Простоквашино', 2, '2023-01-10', 120);
insert into product(name, type_id, expired_date, price)
values ('Наша корова', 2, '2023-01-10', 100);
insert into product(name, type_id, expired_date, price)
values ('Шоколадное мороженое', 3, '2023-01-10', 230);
insert into product(name, type_id, expired_date, price)
values ('Степ', 3, '2023-01-10', 30);
insert into product(name, type_id, expired_date, price)
values ('Марс', 3, '2023-01-10', 50);

select p.name, p.price, t.name
from product p join type t on p.type_id = t.id
where t.name like '%СЫР%';

select * from product where name like '%мороженое%';

select * from product where expired_date < current_date;

select p.name, p.price, t.name
from product p join type t on p.type_id = t.id
where t.name like '%СЫР%' or t.name like '%МОЛОКО%';

select p.name, t.name
from product p join type t
on p.type_id = t.id;

select * from product where price = (select max(price) from product);

select t.name, count(t.id)
from product p join type t
on p.type_id = t.id group by t.name;

select t.name, count(t.id)
from product p join type t
on p.type_id = t.id
group by t.name having count(t.id) < 10;