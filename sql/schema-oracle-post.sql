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
SELECT * FROM app_owner.staff;
SELECT * FROM app_owner.staff_view;

/* ------------------------------------------- */
/* ------------------------------------------- */
/* ------------------------------------------- */


/*  - Set session to app_owner (so we have all permissions) including editions. 
    - Create edition view under ora$base (probably not best to do this) */
ALTER SESSION SET CURRENT_SCHEMA = app_owner;

ALTER SESSION SET EDITION = ora$base;
CREATE EDITIONING VIEW staff_view AS
  SELECT staff_id, title, firstname, surname  FROM staff;

/* - Create new edition, 
   - Set to SESSION to use EDITION testANewEditionView */
CREATE EDITION testANewEditionView;
ALTER SESSION SET EDITION = testANewEditionView;

/* Overwrite base view to use new value(s). */
CREATE OR REPLACE EDITIONING VIEW staff_view AS
  SELECT title, firstname, surname  FROM staff;

/* Not sure what this does yet. Gives view access? Trialing. */
GRANT USE ON EDITION testANewEditionView TO app_readonly;

/* Found it finally - forcing users to use a edition (although will I ever want to make it custom for a user?) */
ALTER DATABASE DEFAULT EDITION = testANewEditionView;
