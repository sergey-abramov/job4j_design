create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Piter', 'M');
insert into teens(name, gender) values ('Sasha', 'Ж');

select t1.name, t2.name, t1.gender, t2.gender from teens t1 cross join teens t2;