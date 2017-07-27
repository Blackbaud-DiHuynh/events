--liquibase formatted sql

--changeset blackbaud:1
create table event (
  id int constraint event_pk primary key,
  date date,
  time timestamp,
  location varchar(250),
  capacity int,
  ticket_id int

)
--rollback drop table event