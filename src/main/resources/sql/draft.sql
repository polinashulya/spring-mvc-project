insert into users(name)
values ('test');
insert into users(email, password)
values ('test', 'test');

insert into users(name)
values ('test');

insert into countries(name)
values ('France');

insert into countries(name)
values ('Italy');

insert into users(email, name)
values ('email', 'polya');

insert into employees(birthDate, email, name, password, phonenumber, surname, hiredate)
values ('1999-02-08','email', 'polya', 'busya', '19292929', 'shulya','1999-02-08');

select *
from users
where id = 2;

select *
from users;

SELECT u.id, u.email, u.name, u.surname, u.birthdate, u.banned
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

SELECT u.id, u.email, u.name, u.surname, u.country_id, u.birthdate, u.banned
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false';

UPDATE users u
SET deleted= false
where u.id = 10;

select u.name, u.surname, u.email
from users u
where u.name like '%' + + '%';

SELECT u.id,
       u.email,
       u.name,
       u.surname,
       u.birthdate,
       u.banned,
       u.country_id,
       c.name
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.email LIKE '%%'
    OR u.name LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%')
group by u.id, u.email, u.name, u.surname, u.birthdate, u.banned, u.country_id, c.name
ORDER BY u.email ASC
LIMIT 5 OFFSET 0;

SELECT COUNT(*)
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.email LIKE '%%'
    OR u.name LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%');

SELECT COUNT(*) AS totalUsers
FROM users
WHERE country_id = 1
  AND (email LIKE '%%' OR name LIKE '%%' OR surname LIKE '%%');

SELECT COUNT(*) AS totalUsers
FROM users u
         join countries c on u.country_id = c.id
WHERE u.deleted = 'false'
  AND (u.email LIKE '%%'
    OR u.name LIKE '%%'
    OR u.surname LIKE '%%'
    OR c.name LIKE '%%')
order by u.id asc;


INSERT INTO clients (country_id, email, phoneNumber, password, name, surname, birthDate, banned, deleted, note)
VALUES (1, 'user@example.com', '+1234567890', 'hashedPassword', 'FirstName', 'LastName', '2000-01-01',  false, false, 'Заметка о клиенте');

