--liquibase formatted sql

--changeset blackbaud:1
create table transaction (
  id int constraint transaction_pk primary key,
  ticket_id int,
  quantity int,
  unit_price NUMERIC(10, 2)
)
--rollback drop table transaction

--changeset blackbaud:2
create sequence transaction_seq
--rollback drop sequence transaction_seq