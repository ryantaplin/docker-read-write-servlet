/* messing around with roles, add readonly and update */
CREATE ROLE staff_readonly
IDENTIFIED BY icansee;

CREATE ROLE staff_update
IDENTIFIED BY icandomore;

GRANT select ON staff TO staff_readonly;
GRANT select, insert, update ON staff TO staff_update;

/* Create new user and assign them role (readonly) */
CREATE USER app_readonly
  IDENTIFIED BY basicAuth
  DEFAULT TABLESPACE tablespace_01
  QUOTA 20M on tablespace_01;
  
GRANT staff_readonly TO app_readonly;

/* Stupidly need to reference app_owner when I want to view table/view. */
ALTER SESSION SET CURRENT_SCHEMA = app_readonly;

--SELECT * FROM app_owner.staff;
--SELECT * FROM app_owner.staff_view;

