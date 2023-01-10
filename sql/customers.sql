CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into customers(first_name, last_name, age, country)
values ('Desmont','Desmont1', 18, 'Rus');
insert into customers(first_name, last_name, age, country)
values ('Vladmir','Vova', 28, 'Eng');
insert into customers(first_name, last_name, age, country)
values ('Ivan','Vanya', 55, 'US');

select * from customers where age = (select min(age) from customers);

insert into orders(amount, customer_id) values (1, 2);
insert into orders(amount, customer_id) values (2, 2);
insert into orders(amount, customer_id) values (3, 1);

select * from customers where customers.id not in (select customer_id from orders);
