insert into users(name)
values ('test');
insert into users(login, password)
values ('test', 'test');

insert into users(name)
values ('test');

insert into countries(name)
values ('France');

insert into countries(name)
values ('Italy');

insert into users(login, name)
values ('login', 'polya');

insert into users(login, name, surname, birthdate)
values ('login', 'polya', 'busya', '1999-02-08');

select *
from users
where id = 2;

select *
from users;

SELECT u.id, u.login, u.name, u.surname, u.birthdate, u.banned
FROM users u
ORDER BY id ASC;


select id, name
from users
where id = 6;
UPDATE users
SET banned= false
where id = 6;
-- comment

select *
from users
where name like 'P%'
order by id DESC;

ALTER TABLE users
    ADD COLUMN country_id INT;

ALTER TABLE users
    ADD FOREIGN KEY (country_id) REFERENCES countries (id);

create table countries
(
    id   serial,
    name character(20) NOT NULL UNIQUE
);

select *
from countries;

insert into countries(name)
values ('Spain');



select *
from users u
         join countries c on u.country_id = c.id
where u.id = 1;

SELECT u.id, u.login, u.name, u.surname, u.country_id, u.birthdate, u.banned
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false';

UPDATE users u
SET deleted= false
where u.id = 10;

select u.name, u.surname, u.login
from users u
where u.name like '%' + + '%';

SELECT u.id,
       u.login,
       u.name,
       u.surname,
       u.birthdate,
       u.banned,
       u.country_id,
       c.name
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.login LIKE '%%'
    OR u.name LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%')
group by u.id, u.login, u.name, u.surname, u.birthdate, u.banned, u.country_id, c.name
ORDER BY u.login ASC
LIMIT 5 OFFSET 0;

SELECT COUNT(*)
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.login LIKE '%%'
    OR u.name LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%');

SELECT COUNT(*) AS totalUsers
FROM users
WHERE country_id = 1
  AND (login LIKE '%%' OR name LIKE '%%' OR surname LIKE '%%');

SELECT COUNT(*) AS totalUsers
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.login LIKE '%%'
    OR u.name LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%')
order by u.id asc;


insert into clients()
values ('login', 'polya', 'busya', '1999-02-08');
