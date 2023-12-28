create table users
(
    id         serial,
    login      character(20) NOT NULL UNIQUE,
    password   character(20) NOT NULL,
    name  character(20) NOT NULL,
    surname    character(20) NOT NULL,
    country_id int,
    birth_date date,
    banned     bool,
    deleted    bool
);

create table countries
(
    id   serial,
    name character(20) NOT NULL UNIQUE
);

ALTER TABLE users
    ADD FOREIGN KEY (country_id) REFERENCES countries (id);