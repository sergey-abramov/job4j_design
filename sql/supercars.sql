create table supercars(
	id serial primary key,
	name varchar(255),
	area text,
	drive bool
);
insert into supercars(name, area, drive) values('Lamborgini', 'Italy', true);
update supercars set name = 'Ferarry';
delete from supercars;