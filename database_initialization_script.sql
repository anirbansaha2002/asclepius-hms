create database hospital_management_system;
use hospital_management_system;

CREATE TABLE user_credentials (
  username varchar(50) NOT NULL,
  pass varchar(50) DEFAULT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE patient_info (
  uid int NOT NULL,
  name varchar(50) NOT NULL,
  address varchar(100) NOT NULL,
  phno varchar(10) DEFAULT NULL,
  gender varchar(6) NOT NULL,
  bloodgroup varchar(3) NOT NULL,
  PRIMARY KEY (uid)
);

CREATE TABLE doctor_info (
  uid int NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  gender varchar(6) NOT NULL,
  phno varchar(10) DEFAULT NULL,
  specialization varchar(20) NOT NULL,
  PRIMARY KEY (uid)
);

CREATE TABLE employee_info (
  uid int NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  gender varchar(6) NOT NULL,
  department varchar(50) NOT NULL,
  dateOfJoining varchar(20) NOT NULL,
  salary int NOT NULL,
  PRIMARY KEY (uid)
);

CREATE TABLE appointments (
  appointmentID int NOT NULL,
  uid int NOT NULL,
  doctor varchar(50) NOT NULL,
  appointmentDate varchar(20) NOT NULL,
  amount int NOT NULL,
  PRIMARY KEY (appointmentID),
  KEY uid (uid),
  CONSTRAINT appointments_ibfk_1 FOREIGN KEY (uid) REFERENCES patient_info (uid)
);






