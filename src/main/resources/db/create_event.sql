--liquibase formatted sql

--changeset blackbaud:1
create table event (
  id int constraint event_pk primary key,
  date timestamp,
  time timestamp,
  location varchar(250),
  capacity int,
  ticket_id int,
  name varchar(250)
);
--rollback drop table event

--changeset blackbaud:2
create sequence event_seq
--rollback drop sequence node_seq
