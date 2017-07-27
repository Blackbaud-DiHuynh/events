--liquibase formatted sql

--changeset blackbaud:1
create table dynamic_rule(
  id int constraint dynamic_rule_pk primary key,
  ticket_id int,
  inventory_threshold int,
  type VARCHAR,
  price_change NUMERIC(10, 2)
)

--changeset blackbaud:2
create sequence dynamic_rule_seq
