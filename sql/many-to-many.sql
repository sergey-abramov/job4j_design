create table people(
	id serial primary key,
	name varchar(255)
);

create table lesson(
	id serial primary key,
	name varchar(255)
);

create table people_lesson(
	id serial primary key,
	people_id int references people(id),
	lesson_id int references lesson(id)
);

insert into people(name) values('Sergey');
insert into people(name) values('Ivan');

insert into lesson(name) values('Maths');
insert into lesson(name) values('Russian Lang');

insert into people_lesson(people_id, lesson_id) values(1, 2);
insert into people_lesson(people_id, lesson_id) values(2, 2);

select * from people_lesson;
