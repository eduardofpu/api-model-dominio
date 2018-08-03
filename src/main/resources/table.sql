/*CREATE SCHEMA IF NOT EXISTS "Apitable";
SET SEARCH_PATH TO 'Apitable';

CREATE TABLE PUBLIC.contato(
    id BIGSERIAL    NOT NULL CONSTRAINT contato_pkey PRIMARY KEY,
    name varchar(45) NOT NULL
	);
CREATE INDEX api_id_idx
  ON PUBLIC.contato (id);*/
