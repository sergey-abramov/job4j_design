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

insert into devices(name, price) values ('Phillips', 2600);
insert into devices(name, price) values ('Sony', 5500);
insert into devices(name, price) values ('Samsung', 7000);
insert into devices(name, price) values ('Apple', 10000);

insert into people(name) values ('Piter'), ('Vladimir'), ('Ivan'), ('Sergey');

insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (1, 4);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 4);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (4, 2);

select avg(price) from devices;

select dp.people_id, avg(d.price)
from devices_people as dp
join devices d on dp.device_id = d.id group by dp.people_id;

select dp.people_id, avg(d.price)
from devices_people as dp
join devices d on dp.device_id = d.id group by dp.people_id
having avg(d.price) > 5000;