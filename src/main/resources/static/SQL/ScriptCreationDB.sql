DROP DATABASE IF EXISTS db_altn72_hope;

CREATE DATABASE db_altn72_hope;

USE db_altn72_hope;

DROP TABLE IF EXISTS StudentTool;

CREATE TABLE StudentTool (
    toolID INT PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    domainName VARCHAR(50) NOT NULL,
    simpleDesc VARCHAR(50) NOT NULL DEFAULT '?',
    detailedDesc VARCHAR(500) NOT NULL,
    link VARCHAR(250) NOT NULL,
    accessInstruction VARCHAR(500) NOT NULL
);

DROP TABLE IF EXISTS UserFeedback;

CREATE TABLE UserFeedback (
    feedbackID INT PRIMARY KEY NOT NULL,
    content VARCHAR(250) NOT NULL,
    toolID INT NOT NULL,
    CONSTRAINT FK_UserFeedback_StudentTool FOREIGN KEY (toolID) REFERENCES StudentTool(toolID) ON DELETE CASCADE
);