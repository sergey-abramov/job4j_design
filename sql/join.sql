create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name) values ('Departament 1');
insert into departments(name) values ('Departament 2');
insert into departments(name) values ('Departament 3');
insert into departments(name) values ('Departament 4');
insert into departments(name) values ('Departament 5');

insert into employees(name, departments_id) values ('Employees 1', 1);
insert into employees(name, departments_id) values ('Employees 2', 1);
insert into employees(name, departments_id) values ('Employees 3', 1);
insert into employees(name, departments_id) values ('Employees 4', 2);
insert into employees(name, departments_id) values ('Employees 5', 2);
insert into employees(name, departments_id) values ('Employees 6', 3);
insert into employees(name, departments_id) values ('Employees 7', 3);
insert into employees(name, departments_id) values ('Employees 8', null);
insert into employees(name, departments_id) values ('Employees 9', 4);

select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on d.id = e.departments_id;
select * from employees e full join departments d on e.departments_id = d.id;
select * from employees e cross join departments d;

select * from departments d left join employees e on e.departments_id = d.id where e.id is null;

select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on d.id = e.departments_id;
