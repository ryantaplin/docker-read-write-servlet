CREATE TABLESPACE tablespace_01
  DATAFILE 'tablespace_01.dat'
  SIZE 20M
  ONLINE;

CREATE USER app.owner
  IDENTIFIED BY pa$$word
  DEFAULT TABLESPACE tablespace_01
  TEMPORARY TABLESPACE tablespace_01
  QUOTA 20M on tablespace_01;;

GRANT CREATE session TO app.owner;
GRANT CREATE table TO app.owner;
GRANT CREATE view TO app.owner;
GRANT CREATE any trigger TO app.owner;
GRANT CREATE any procedure TO app.owner;
GRANT CREATE sequence TO app.owner;
GRANT CREATE synonym TO app.owner;

ALTER SESSION SET CURRENT_SCHEMA = app.owner;

CREATE TABLE staff (
  staff_id   NUMBER(10) NOT NULL,
  title      VARCHAR2(10) NOT NULL,
  firstname  VARCHAR2(25) NOT NULL,
  surname    VARCHAR2(25) NOT NULL,
  CONSTRAINT staff_pk PRIMARY KEY (id)
);

CREATE SEQUENCE seq_staff_id
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
CACHE 10;
