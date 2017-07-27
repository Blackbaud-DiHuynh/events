--liquibase formatted sql

--changeset blackbaud:1
create table dynamic_pricing(
  id int constraint dynamic_pricing_pk primary key,
  ticket_id int,
  remaining_inventory int,
  type VARCHAR,
  price_change NUMERIC(10, 2)
)

--changeset blackbaud:2
create sequence dynamic_pricing_seq
