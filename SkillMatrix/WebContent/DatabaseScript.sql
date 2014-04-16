drop table EMPLOYEE_SKILL_TBL;
drop table EMPLOYEE_TBL;
drop table DOMAIN_TBL;
drop table SKILL_TBL;
drop table COMPETENCY_TBL;
drop table COMPETENCY_GROUP_TBL;
 
 
CREATE TABLE EMPLOYEE_TBL
  (
  EMPLOYEE_ID int (10) NOT NULL,
  FIRST_NAME VARCHAR (50),
  LAST_NAME VARCHAR (50),
  PHONE VARCHAR (12),
  EMAIL VARCHAR (50),
  SDLC_EMPLOYEE VARCHAR(1),
  EMPLOYEE_ROLE VARCHAR(1),
  EMPLOYEE_TYPE VARCHAR(1),
  EMPLOYEE_HIRE_DATE date,
  EMPLOYEE_PROJECT_END_DATE date,
  IN_PROJECT VARCHAR(1),
  WORK_LOCATION VARCHAR(100),
  LAST_UPDATED_USER_ID VARCHAR (10),
  LAST_UPDATED_TS TIMESTAMP,
  PRIMARY KEY (EMPLOYEE_ID)
);
 
CREATE TABLE COMPETENCY_GROUP_TBL
  (
  COMPETENCY_GROUP_ID int (10) NOT NULL,
  COMPETENCY_GROUP_NAME VARCHAR (50),
  ACTIVE_FLAG VARCHAR(1),
  PRIMARY KEY (COMPETENCY_GROUP_ID)
);
 
CREATE TABLE COMPETENCY_TBL
  (
  COMPETENCY_ID int (10) NOT NULL,
  COMPETENCY_GROUP_ID int (10) NOT NULL,
  COMPETENCY_NAME VARCHAR (50),
  ACTIVE_FLAG VARCHAR(1),
  PRIMARY KEY (COMPETENCY_ID)
);
 
CREATE TABLE SKILL_TBL
  (
  SKILL_ID int (10) NOT NULL,
  COMPETENCY_ID int (10) NOT NULL,
  SKILL_NAME VARCHAR (50),
  ACTIVE_FLAG VARCHAR(1),
  PRIMARY KEY (SKILL_ID)
);
 
CREATE TABLE DOMAIN_TBL
  (
  DOMAIN_ID int (2) NOT NULL,
  DOMAIN_NAME VARCHAR (50),
  ACTIVE_FLAG VARCHAR(1),
  PRIMARY KEY (DOMAIN_ID)
);
 
CREATE TABLE EMPLOYEE_SKILL_TBL
  (
  EMPLOYEE_ID int (10) NOT NULL,
  SKILL_ID int (10) NOT NULL,
  DOMAIN_ID int (2),
  PROFIENCY int (2),
 EXPERIENCE int(5),
  RECENCY int(5),
  COMMENT VARCHAR(100),
  LAST_UPDATED_TS TIMESTAMP,
  PRIMARY KEY (EMPLOYEE_ID,SKILL_ID)
);
 
ALTER TABLE SKILL_TBL 
  ADD CONSTRAINT FOREIGN KEY (COMPETENCY_ID) REFERENCES COMPETENCY_TBL(COMPETENCY_ID);
  
ALTER TABLE COMPETENCY_TBL
  ADD CONSTRAINT FOREIGN KEY (COMPETENCY_GROUP_ID) REFERENCES COMPETENCY_GROUP_TBL(COMPETENCY_GROUP_ID);
 
ALTER TABLE EMPLOYEE_SKILL_TBL
  ADD CONSTRAINT FOREIGN KEY (SKILL_ID) REFERENCES SKILL_TBL(SKILL_ID);
 
ALTER TABLE EMPLOYEE_SKILL_TBL
  ADD CONSTRAINT FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE_TBL(EMPLOYEE_ID);
 
ALTER TABLE EMPLOYEE_SKILL_TBL
  ADD CONSTRAINT FOREIGN KEY (DOMAIN_ID) REFERENCES DOMAIN_TBL(DOMAIN_ID);
 
 
 INSERT INTO EMPLOYEE_TBL (
  EMPLOYEE_ID,
  FIRST_NAME,
  LAST_NAME,
  PHONE,
  EMAIL,
  SDLC_EMPLOYEE,
  EMPLOYEE_ROLE,
  EMPLOYEE_TYPE,
  EMPLOYEE_HIRE_DATE,
  EMPLOYEE_PROJECT_END_DATE,
  IN_PROJECT,
  WORK_LOCATION)
 VALUES
 (
 1,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 3,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL
 
 );
 
 
 