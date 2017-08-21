/*  - Set session to app_owner (so we have all permissions) including editions.
    - Create edition view under ora$base (probably not best to do this) */
ALTER SESSION SET CURRENT_SCHEMA = app_owner;

/* - Create new edition,
   - Set to SESSION to use EDITION testANewEditionView */
CREATE EDITION ED_1;
ALTER SESSION SET EDITION = ED_1;

/* Overwrite base view to use new value(s). */
CREATE OR REPLACE EDITIONING VIEW staff_view AS
  SELECT staff_id, title, firstname, surname  FROM staff;

/* Not sure what this does yet. Gives view access? Trialing. */
GRANT USE ON EDITION ED_1 TO app_readonly;

/* Found it finally - forcing users to use a edition (although will I ever want to make it custom for a user?) */
-- may not need to do this; may just need to define default edition on start up of app.
--ALTER DATABASE DEFAULT EDITION = testANewEditionView;
