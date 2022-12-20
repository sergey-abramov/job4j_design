create table comments(
	id serial primary key,
	name varchar(255)
);

create table attachs(
	id serial primary key,
	name varchar(255)
);

create table item(
	id serial primary key,
	name varchar(255),
	comments_id int references comments(id),
	attachs_id int references attachs(id)
);

create table category(
	id serial priimary key,
	name varchar(255),
	item_id int references item(id)
);

create table state(
	id serial primary key,
	status varchar(255)
	item_id int references item(id)
);

create table users(
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);

create table role(
	id serial primary key,
	name varchar(255),
	users_id int references users(id)
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
