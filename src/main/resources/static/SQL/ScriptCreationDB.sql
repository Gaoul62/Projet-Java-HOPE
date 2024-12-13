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
	userID INT NOT NULL,
    CONSTRAINT FK_UserFeedback_StudentTool FOREIGN KEY (toolID) REFERENCES StudentTool(toolID) ON DELETE CASCADE
    CONSTRAINT FK_UserFeedback_UserApp FOREIGN KEY (userID) REFERENCES UserApp(userID) ON DELETE CASCADE
);


DROP TABLE IF EXISTS UserApp;

CREATE TABLE UserApp (
	userID INT PRIMARY KEY NOT NULL,
	role VARCHAR(10) NOT NULL,
	password VARCHAR(25) NOT NULL
);

DROP TABLE IF EXISTS ToolProposition;

CREATE TABLE ToolProposition (
	toolPropositionID INT PRIMARY KEY NOT NULL,
	validationStatus VARCHAR(25) NOT NULL,
	title VARCHAR(50),
    domainName VARCHAR(50),
    simpleDesc VARCHAR(50),
    detailedDesc VARCHAR(500),
    link VARCHAR(250),
    accessInstruction VARCHAR(500),
	userID INT NOT NULL,
	adminID INT NOT NULL,
	CONSTRAINT FK_ToolProposition_SimpleUser FOREIGN KEY (userID) REFERENCES UserApp(userID) ON DELETE CASCADE
	CONSTRAINT FK_ToolProposition_AdminUser FOREIGN KEY (adminID) REFERENCES UserApp(userID) ON DELETE CASCADE
);