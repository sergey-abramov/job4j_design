create table human (
    id serial primary key,
    name varchar(50)
);

create table cars (
    id serial primary key,
    name varchar(50)
);

create table diler (
    id serial primary key,
    name varchar(200),
    cars_id integer references cars(id)
);

insert into human (name) values ('Иван Иванов');
insert into human (name) values ('Петр Петров');

insert into cars (name) values ('Mercedes');
insert into cars (name) values ('BMW');

insert into diler (name, cars_id) values ('Alyans', 1);
insert into diler (name, cars_id) values ('VMPAvto', 1);
insert into diler (name, cars_id) values ('Sindicat', 1);
insert into diler (name, cars_id) values ('Official', 2);
insert into diler (name, cars_id) values ('AvtoMoto', 2);

create table contracts (
    id serial primary key,
    active boolean default true,
    diler_id integer references diler(id),
    human_id integer references human(id)
);

insert into contracts (diler_id, human_id) values (1, 1);
insert into contracts (diler_id, human_id) values (3, 1);
insert into contracts (diler_id, human_id) values (5, 2);
insert into contracts (diler_id, human_id) values (4, 1);
insert into contracts (diler_id, human_id) values (2, 2);

create view contracts_human_
    as select p.name as human, count(c.name), c.name as cars from human as p
         join contracts ct on p.id = ct.human_id
         join diler d on ct.diler_id = d.id
         join cars c on d.cars_id = c.id
         group by (p.name, c.name)

select * from contracts_human_;