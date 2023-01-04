create table reg_num(
    id serial primary key,
    number int,
    letters varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    collor varchar(255),
    reg_num_id int references reg_num(id) unique
);

insert into reg_num(number, letters) values (021, 'XXX');
insert into reg_num(number, letters) values (121, 'AAA');
insert into reg_num(number, letters) values (152, 'AMA');
insert into reg_num(number, letters) values (234, 'KMA');

insert into car(name, collor, reg_num_id) values (VAZ, blue, 2);
insert into car(name, collor, reg_num_id) values (Mers, black, 1);
insert into car(name, collor, reg_num_id) values (VAZ, red, 3);
insert into car(name, collor) values (VAZ, green);

select c.name, reg.number, reg.letters
from car c join reg_num reg on c.reg_num_id = reg.id;

select c.name марка, reg.number номер, reg.letters буквы
from car c join reg_num reg on c.reg_num_id = reg.id;

select c.name as "Марка авто", reg.number номер, reg.letters буквы
from car c join reg_num reg on c.reg_num_id = reg.id;