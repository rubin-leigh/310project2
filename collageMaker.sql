DROP DATABASE IF EXISTS CollageMaker; -- super dangerous 
CREATE DATABASE CollageMaker;

USE CollageMaker;

CREATE TABLE Users (
	userID INT(11) PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(100) NOT NULL,
    pw VARCHAR(100) NOT NULL
);

INSERT INTO Users (userName, pw)
	VALUES ('dan', 'password'),
           ('bob', 'test');

CREATE TABLE Collages (
	collageID int(11) PRIMARY KEY AUTO_INCREMENT,
    collageString VARCHAR(200) NOT NULL,
    topic VARCHAR(200) NOT NULL
);

