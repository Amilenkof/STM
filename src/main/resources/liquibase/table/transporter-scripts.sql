-- liquibase formatted sql

-- changeset aMilenkov:1

CREATE TABLE transporter
(
    id    bigserial primary key,
    name  varchar(255) not null,
    phone varchar(255) not null
);