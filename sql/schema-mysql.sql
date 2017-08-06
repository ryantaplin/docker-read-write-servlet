CREATE DATABASE IF NOT EXISTS sky;

USE sky;

CREATE TABLE IF NOT EXISTS staff (
  id              INT unsigned NOT NULL AUTO_INCREMENT,
  title           VARCHAR(150) NOT NULL,
  firstname       TEXT,
  surname         TEXT,
  PRIMARY KEY     (id));