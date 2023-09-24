-- liquibase formatted sql

-- changeset aMilenkov:1

CREATE TABLE users
(
    id       bigserial primary key,
    name     varchar(255) not null,
    password varchar(255) not null
);

--changeset aMilenkov:2
DROP TABLE users;
CREATE TABLE users
(
    id       bigserial primary key,
    login    varchar(255) not null,
    name     varchar(255) not null,
    password varchar(255) not null
);
--changeset aMilenkov:3
ALTER TABLE users
    ADD CONSTRAINT unique_login UNIQUE (login);
