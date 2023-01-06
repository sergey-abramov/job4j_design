create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table car_model(
    id serial primary key,
    name varchar(255),
    bodies_id int references bodies(id),
    engines_id int references engines(id),
    transmissions_id int references transmissions(id)
);

insert into car_bodies(name) values ('Купе'), ('Седан'), ('Универсал'), ('Внедорожник'), ('Кроссовер'), (null);
insert into car_engines(name) values ('Дизель'), ('Бензин'), ('Гибрид'), ('Электро');
insert into car_transmissions(name) values ('Полный'), ('Передний'), ('Задний'), (null);

insert into car_model(name, bodies_id, engines_id, transmissions_id) values ('BMW', 1, 2, 1);
insert into car_model(name, bodies_id, engines_id, transmissions_id) values ('Volvo', 3, 3, 1);
insert into car_model(name, bodies_id, engines_id, transmissions_id) values ('VAZ', 2, 2, 2);
insert into car_model(name, bodies_id, engines_id, transmissions_id) values ('GAZ', 6, 2, 4);

select car.name as model, tr.name as transm, en.name as engine, bo.name as body
from car_model car
join car_transmissions tr on car.transmissions_id = tr.id
join car_engines en on car.engines_id = en.id
join car_bodies bo on car.bodies_id = bo.id;

select bo.name from car_bodies bo
left join car_model car on bo.id = car.bodies_id
where car.name is null;

select tr.name from car_transmissions tr
left join car_model car on tr.id = car.transmissions_id
where car.name is null;

select en.name from car_engines en
left join car_model car on car.engines_id = en.id
where car.name is null;