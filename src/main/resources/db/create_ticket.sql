--liquibase formatted sql

--changeset blackbaud:1
create table ticket (
  id int constraint ticket_pk primary key,
  base_price NUMERIC(10, 2),
  event_id int
)
--rollback drop table ticket

--changeset blackbaud:2
create sequence ticket_seq
--rollback drop sequence ticket_seq