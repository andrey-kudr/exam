CREATE SCHEMA IF NOT EXISTS SA;
SET SCHEMA SA;
CREATE TABLE IF NOT EXISTS PERSON (ID INT PRIMARY KEY, NAME VARCHAR2(255 CHAR), AGE NUMBER, IQ NUMBER, BIRTHDAY TIMESTAMP);
CREATE TABLE IF NOT EXISTS EMPTY (NAME VARCHAR2(255 CHAR), VALUE NUMBER);
CREATE TABLE IF NOT EXISTS TYPES (ID INT PRIMARY KEY, TIMESTAMP_TYPE TIMESTAMP, DATETIME_TYPE SMALLDATETIME, DATE_TYPE DATE);