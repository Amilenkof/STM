-- liquibase formatted sql

-- changeset aMilenkov:1

CREATE TABLE ticket
(
    id    bigserial primary key,
    direction_id bigint not null,
    dateTime timestamp not null,
    seat varchar(5) not null,
    price decimal (12,2) not null,

    FOREIGN KEY (direction_id) REFERENCES direction (id)

);

-- changeset aMilenkov:2
Drop table ticket;

CREATE TABLE ticket
(
    id    bigserial primary key,
    direction_id bigint not null,
    dateTime timestamp not null,
    seat varchar(5) not null,
    price decimal (12,2) not null,
    user_id bigint,
    status varchar(4),

    FOREIGN KEY (direction_id) REFERENCES direction (id),
    UNIQUE (direction_id,seat)

);
--changeset aMilenkov:3
ALTER TABLE ticket
    ALTER user_id SET DEFAULT NULL,
    ALTER status SET DEFAULT 'FREE';
