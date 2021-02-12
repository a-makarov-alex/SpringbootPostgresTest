CREATE DATABASE seconddb;

USE seconddb;

CREATE TABLE secondtable (name character varying(80) , age integer, id integer NOT NULL DEFAULT);

INSERT INTO secondtable VALUES ('first', 22, 1), ('second', 23, 2);