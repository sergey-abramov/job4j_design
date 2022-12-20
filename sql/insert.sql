insert into item(name, comments_id, attachs_id) values ('Item 1', 1, 2);
insert into item(name, comments_id, attachs_id) values ('Item 2', 2, 1);

insert into comments(name) values ('Comment 2');
insert into comments(name) values ('Comment 1');

insert into attachs(name) values ('File.txt');
insert into attachs(name) values ('File2.txt');

insert into category(name, item_id) values ('Category 1', 1);
insert into category(name, item_id) values ('Category 2', 1);

insert into state(status, item_id) values ('Filed', 1);
insert into state(status, item_id) values ('Filed', 3);

insert into role(name, users_id) values ('Hero', 1);
insert into role(name, users_id) values ('AntiHero', 2);

insert into users(name, item_id) values ('Ivan', 1);
insert into users(name, item_id) values ('Vladimir', 2);

insert into rules(name) values ('Ivan');
insert into rules(name) values ('Ivan');

insert into role_rules(role_id, rules_id) values (1, 2);