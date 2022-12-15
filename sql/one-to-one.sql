create table people(
	id serial primary key,
	name varchar(255)
);

create table email_adress(
	id serial primary key,
	name varchar(255)
);

create table people_email_adress(
	id serial primary key,
	people_id int references people(id) unique,
	email_adress_id int references email_adress(id) unique
);

insert into people(name) values('Sergey');
insert into people(name) values('Ivan');

insert into email_adress(name) values('example.com');
insert into email_adress(name) values('wrap.com');

insert into people_email_adress(people_id, email_adress_id) values(1, 2);
insert into people_email_adress(people_id, email_adress_id) values(2, 1);

select * from people_email_adress;
