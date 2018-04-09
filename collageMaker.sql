DROP DATABASE IF EXISTS CollageMaker; -- super dangerous 
CREATE DATABASE CollageMaker;

USE CollageMaker;

CREATE TABLE Users (
	userID INT(11) PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(100) NOT NULL,
    pw VARCHAR(100) NOT NULL,
    salt VARCHAR(100) NOT NULL
);

INSERT INTO Users (userName, pw, salt)
	VALUES ('dan', '$2a$10$KoQi6QGvMRcXHbRFhqx5Le8zg5b4H08A5LTaW5dG9NVxf9VIT1Ssa', '$2a$10$KoQi6QGvMRcXHbRFhqx5Le'),
           ('bob', '$2a$10$QGokugimymrH4lve4LyHwuf9Y9RCYMwPUM87gNh6Xt/nSafqKHRlS
', '$2a$10$QGokugimymrH4lve4LyHwu');

CREATE TABLE Collages (
	collageID int(11) PRIMARY KEY AUTO_INCREMENT,
    collageString VARCHAR(200) NOT NULL,
    topic VARCHAR(200) NOT NULL
);

