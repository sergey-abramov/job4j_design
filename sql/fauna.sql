create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna(name, avg_age, discovery_date)
values ('Bear', 10950, date '1758-01-02');
insert into fauna(name, avg_age, discovery_date)
values ('Cat', 5110,  date '1758-01-02');
insert into fauna(name, avg_age, discovery_date)
values ('Dog', 4745,  date '1758-01-02');
insert into fauna(name, avg_age, discovery_date)
values ('Tiger', 3650,  date '1758-01-02');
insert into fauna(name, avg_age, discovery_date)
values ('Fish', 5475,  date '1758-01-02');
insert into fauna(name, avg_age, discovery_date)
values ('null', 10950, null);
select * from fauna where name like '%Fish%';
select * from fauna where discovery_date is null;
select * from fauna where avg_age >= 10000 and avg_age < 21000;
select * from fauna where discovery_date < '1950-01-01';