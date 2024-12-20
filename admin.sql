drop database if exists chat_system;
CREATE DATABASE IF NOT EXISTS chat_system;
USE chat_system;

CREATE TABLE USER_ACCOUNT (
    ID INT AUTO_INCREMENT,
    USERNAME VARCHAR(255),
    `PASSWORD` VARCHAR(255),
    FULLNAME VARCHAR(255),
    `ADDRESS` VARCHAR(255),
    DATE_OF_BIRTH DATE,
    GENDER VARCHAR(3),
    EMAIL VARCHAR(255),
    ON_OFF BOOLEAN,
    CREATED_AT TIMESTAMP,
    BANNED BOOLEAN,
    IS_ADMIN BOOLEAN,
    PRIMARY KEY(ID)
);

CREATE TABLE USER_FRIEND (
    ID INT,
    FRIEND_ID INT,
	PRIMARY KEY(ID, FRIEND_ID),
    FOREIGN KEY(ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
    FOREIGN KEY(FRIEND_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE
);

CREATE TABLE FRIEND_REQUEST (
    FROM_ID INT,
    TO_ID INT,
    `STATUS` VARCHAR(255),
    PRIMARY KEY(FROM_ID, TO_ID),
    FOREIGN KEY(FROM_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
    FOREIGN KEY(TO_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE
);

CREATE TABLE USER_BLOCK (
    USER_ID INT, 
    BLOCK_ID INT,
    PRIMARY KEY(USER_ID, BLOCK_ID),
    FOREIGN KEY(USER_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
    FOREIGN KEY(BLOCK_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE
);


CREATE TABLE LOGIN_HISTORY (
    LOGIN_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID INT,
    LOGIN_TIME TIMESTAMP,
    FOREIGN KEY(USER_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE
);

CREATE TABLE MESSAGE_USER (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    FROM_USER INT,
    TO_USER INT,
    SEND_AT TIMESTAMP,
    CONTENT TEXT,
    VISIBLE_ONLY INT,
    FOREIGN KEY(FROM_USER) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
    FOREIGN KEY(TO_USER) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE
);

CREATE TABLE GROUPCHAT (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    GROUP_NAME VARCHAR(255),
    CREATED_AT TIMESTAMP
);

CREATE TABLE GROUPCHAT_MEMBER (
    GROUPCHAT_ID INT,
    MEMBER_ID INT,
    POSITION VARCHAR(20),
    PRIMARY KEY(GROUPCHAT_ID, MEMBER_ID),
    FOREIGN KEY(GROUPCHAT_ID) REFERENCES GROUPCHAT(ID),
    FOREIGN KEY(MEMBER_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE
);

CREATE TABLE MESSAGE_GROUP (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    FROM_USER INT,
    TO_GROUP INT,
    TIME_SEND TIMESTAMP,
    CONTENT TEXT,
    FOREIGN KEY(FROM_USER) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
    FOREIGN KEY(TO_GROUP) REFERENCES GROUPCHAT(ID)
);

CREATE TABLE REPORT_SPAM (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    REPORTER_ID INT, 
    REPORTED_ID INT,
    CONTENT TEXT,
    REPORT_AT TIMESTAMP,
    FOREIGN KEY(REPORTER_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
    FOREIGN KEY(REPORTED_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE
);

INSERT INTO USER_ACCOUNT (USERNAME, PASSWORD, FULLNAME, ADDRESS, DATE_OF_BIRTH, GENDER, EMAIL, ON_OFF, CREATED_AT, BANNED, IS_ADMIN)
VALUES 
('john_doe', 'password123', 'John Doe', '123 Main St', '1990-01-01', 'M', 'john.doe@example.com', TRUE, CURRENT_TIMESTAMP, FALSE, TRUE),
('jane_smith', 'password456', 'Jane Smith', '456 Elm St', '1992-02-14', 'F', 'jane.smith@example.com', TRUE, CURRENT_TIMESTAMP, FALSE, TRUE),
('alex_jones', 'password789', 'Alex Jones', '789 Oak St', '1988-05-23', 'M', 'alex.jones@example.com', TRUE, CURRENT_TIMESTAMP, TRUE, TRUE),
('gankerV', '123', 'Nguyễn Văn Xanh', '123 Nhà Trắng Quận 1', '2004-01-01', 'M', 'nvxanh75@gmail.com', TRUE, '2024-01-01 12:05:37.0', TRUE, TRUE);


INSERT INTO USER_FRIEND (ID, FRIEND_ID)
VALUES
(1, 2),
(1, 3),
(2, 1),
(2, 3),
(3, 1),
(3, 2);


INSERT INTO FRIEND_REQUEST (FROM_ID, TO_ID, STATUS)
VALUES
(1, 2, 'Pending'),
(2, 3, 'Accepted'),
(3, 1, 'Rejected');


INSERT INTO USER_BLOCK (USER_ID, BLOCK_ID)
VALUES
(1, 3),
(2, 1),
(3, 2);


INSERT INTO LOGIN_HISTORY (USER_ID, LOGIN_TIME)
VALUES
(1, CURRENT_TIMESTAMP),
(2, CURRENT_TIMESTAMP),
(3, CURRENT_TIMESTAMP),
(4, '2024-12-10 12:05:37.0'),
(4, '2024-05-01 12:05:37.0');


INSERT INTO MESSAGE_USER (FROM_USER, TO_USER, SEND_AT, CONTENT, VISIBLE_ONLY)
VALUES
(1, 2, CURRENT_TIMESTAMP, 'Hey, how are you?', 0),
(2, 1, CURRENT_TIMESTAMP, 'I am fine, thanks!', 0),
(3, 1, CURRENT_TIMESTAMP, 'Hello, need help?', 0),
(1, 4, '2024-12-31 12:05:37.0', 'Chào Xanh!', 0);


INSERT INTO GROUPCHAT (GROUP_NAME, CREATED_AT)
VALUES
('Group 1', CURRENT_TIMESTAMP),
('Group 2', CURRENT_TIMESTAMP),
('Group 3', CURRENT_TIMESTAMP);


INSERT INTO GROUPCHAT_MEMBER (GROUPCHAT_ID, MEMBER_ID, POSITION)
VALUES
(1, 1, 'Admin'),
(1, 2, 'Member'),
(1, 3, 'Member'),
(2, 3, 'Admin');


INSERT INTO MESSAGE_GROUP (FROM_USER, TO_GROUP, TIME_SEND, CONTENT)
VALUES
(1, 1, CURRENT_TIMESTAMP, 'Hello Group 1!'),
(2, 1, CURRENT_TIMESTAMP, 'Hi, everyone!'),
(3, 1, CURRENT_TIMESTAMP, 'Whos admin'),
(3, 2, CURRENT_TIMESTAMP, 'Hello Group 2!');


INSERT INTO REPORT_SPAM (REPORTER_ID, REPORTED_ID, CONTENT, REPORT_AT)
VALUES
(1, 3, 'Inappropriate content', CURRENT_TIMESTAMP),
(2, 1, 'Spam message', CURRENT_TIMESTAMP),
(3, 2, 'Offensive language', CURRENT_TIMESTAMP);
