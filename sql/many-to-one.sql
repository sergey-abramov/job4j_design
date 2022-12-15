create table room(
	id serial primary key,
	number int
);

create table students(
	id serial primary key,
	name varchar(255),
	room int references room(id)
);

insert into room(number) values(111);

insert into students(name, room) values('Sergey', 1);
insert into students(name, room) values('Ivan', 1);

select * from students;
