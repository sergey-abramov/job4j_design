insert into category(name) values ('Category 1');
insert into category(name) values ('Category 2');

insert into state(status) values ('Filed');
insert into state(status) values ('Filed');

insert into role(name) values ('Hero');
insert into role(name) values ('AntiHero');

insert into users(name, role_id) values ('Ivan', 1);
insert into users(name, role_id) values ('Vladimir', 1);

insert into rules(name) values ('Ivan');
insert into rules(name) values ('Ivan');

insert into role_rules(role_id, rules_id) values (1, 2);

insert into item(name, caregory_id, state_id, users_id) values ('Item 1', 1, 1, 1);
insert into item(name, caregory_id, state_id, users_id) values ('Item 2', 1, 1, 1);

insert into comments(name, item_id) values ('Comment 2', 1);
insert into comments(name, item_id) values ('Comment 1', 1);

insert into attachs(name, item_id) values ('File.txt', 1);
insert into attachs(name, item_id) values ('File2.txt', 1);
