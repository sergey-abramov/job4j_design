create table role(
	id serial primary key,
	name varchar(255)
);

create table users(
	id serial primary key,
	name varchar(255),
	role_id int references role(id)
);

create table rules(
	id serial primary key,
	name varchar(255)
);

create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

create table item(
	id serial primary key,
	name varchar(255),
	users_id int references user(id)
);

create table comments(
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);

create table category(
	id serial priimary key,
	name varchar(255),
	item_id int references item(id) unique
);

create table state(
	id serial primary key,
	status varchar(255)
	item_id int references item(id) unique
);