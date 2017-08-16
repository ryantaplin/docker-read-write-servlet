/* Not sure what this table space stuff is but I was told to do it. */
CREATE TABLESPACE tablespace_01
  DATAFILE 'tablespace_01.dat'
  SIZE 20M
  ONLINE;

/* Create our main big beef user and grant all permissions. */
CREATE USER app_owner
  IDENTIFIED BY pa$$word
  DEFAULT TABLESPACE tablespace_01 
  QUOTA 20M on tablespace_01;
  
GRANT CREATE session TO app_owner;
GRANT CREATE table TO app_owner;
GRANT CREATE view TO app_owner;
GRANT CREATE any trigger TO app_owner;
GRANT CREATE any procedure TO app_owner;
GRANT CREATE sequence TO app_owner;
GRANT CREATE synonym TO app_owner;

/* Some stuff for editions - enabling editions allows user to create many editioned objects */
GRANT CREATE ANY EDITION, DROP ANY EDITION to app_owner;
ALTER USER app_owner ENABLE EDITIONS;

/* Create our basic staff table. */
CREATE TABLE staff (
  staff_id   NUMBER(10) NOT NULL,
  title      VARCHAR2(10) NOT NULL,
  firstname  VARCHAR2(25) NOT NULL,
  surname    VARCHAR2(25) NOT NULL,
  CONSTRAINT staff_pk PRIMARY KEY (staff_id)
);

/* Oracles answer to auto-increment. Will need to call this trigger in code and insert into table. */
CREATE SEQUENCE seq_staff_id
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
CACHE 10;

/* No longer need to call this trigger in code; created oracle trigger to handle this */
CREATE OR REPLACE TRIGGER BI_STAFF
BEFORE INSERT ON staff
FOR EACH ROW
BEGIN
  :new.staff_id := seq_staff_id.nextval;
END;

/* Create initial view to remove the id - allows us to enter into DB without providing a random integer. */
CREATE OR REPLACE EDITIONING VIEW staff_view AS
  SELECT title, firstname, surname  FROM staff;

/* Insert values into database. */
INSERT INTO staff_view (TITLE, FIRSTNAME, SURNAME) VALUES ('Mr', 'Ryan', 'Taplin');
INSERT INTO staff_view (TITLE, FIRSTNAME, SURNAME) VALUES ('Mr', 'John', 'Smith');
INSERT INTO staff_view (TITLE, FIRSTNAME, SURNAME) VALUES ('Miss', 'Arya', 'Stark');

