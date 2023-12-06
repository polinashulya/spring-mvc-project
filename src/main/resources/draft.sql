insert into users(firstname)
values ('test');
insert into users(login, password)
values ('test', 'test');

insert into users(firstname)
values ('test');


insert into users(login, firstname)
values ('login', 'polya');

insert into users(login, firstname, surname, birth_date)
values ('login', 'polya', 'busya', '1999-02-08');

select *
from users
where id = 2;

select *
from users;

SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned
FROM users u
ORDER BY id ASC;


select id, firstname
from users
where id = 6;
UPDATE users
SET banned= false
where id = 6;
-- comment

select *
from users
where firstname like 'P%'
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

SELECT u.id, u.login, u.firstname, u.surname, u.country_id, u.birth_date, u.banned
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false';

UPDATE users u
SET deleted= false
where u.id = 10

select u.firstname, u.surname, u.login
from users u
where u.firstname like '%' + + '%'

SELECT u.id,
       u.login,
       u.firstname,
       u.surname,
       u.birth_date,
       u.banned,
       u.country_id,
       c.name
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.login LIKE '%%'
    OR u.firstname LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%')
group by u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name
ORDER BY u.login ASC
LIMIT 5 OFFSET 0;

SELECT COUNT(*)
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.login LIKE '%%'
    OR u.firstname LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%');

SELECT COUNT(*) AS totalUsers
FROM users
WHERE country_id = 1
  AND (login LIKE '%%' OR firstname LIKE '%%' OR surname LIKE '%%');

SELECT COUNT(*) AS totalUsers
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.login LIKE '%%'
    OR u.firstname LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%')
order by u.id asc;



