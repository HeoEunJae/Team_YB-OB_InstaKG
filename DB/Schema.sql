# DB 생성
DROP DATABASE IF EXISTS sbbb;
CREATE DATABASE sbbb;
USE sbbb;

CREATE TABLE Question (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    modify_date DATETIME DEFAULT NOW(),
    reply_like TINYINT DEFAULT 0,
    on_off TINYINT DEFAULT 0,
    answer_id INT(11) UNSIGNED,
    `email` VARCHAR(40) NOT NULL
);


CREATE TABLE Answer (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    modify_date DATETIME DEFAULT NOW(),
    question_id INT(11) UNSIGNED NOT NULL,
    reply_like VARCHAR(10) NOT NULL
);

CREATE TABLE answer_comment (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    modify_date DATETIME DEFAULT NOW(),
    question_id INT(11) UNSIGNED NOT NULL,
    answer_id INT(11) UNSIGNED NOT NULL,
    reply_like VARCHAR(10) NOT NULL
);

CREATE TABLE files(
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `filename` TEXT,
    question_id BIGINT UNSIGNED NOT NULL
);

SELECT * FROM `user`;
SELECT * FROM Question;
SELECT * FROM Answer;
SELECT * FROM answer_comment;
SELECT * FROM files;