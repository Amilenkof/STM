-- liquibase formatted sql

-- changeset aMilenkov:1

CREATE TABLE direction
(
    id    bigserial primary key,
    departure_point varchar(255) not null,
    destination_point varchar(255) not null,
    transporter_id bigint not null,
    duration bigint not null,
    FOREIGN KEY (transporter_id) REFERENCES transporter (id)
);