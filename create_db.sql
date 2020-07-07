CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users (username, password, role) values ('keith', '{noop}keithpw', 'ROLE_ADMIN');
INSERT INTO users (username, password, role) values ('admin', '{noop}adminpw', 'ROLE_ADMIN');
INSERT INTO users (username, password, role) values ('andrew', '{noop}andrewpw', 'ROLE_ADMIN');

INSERT INTO users (username, password, role) values ('kenny', '{noop}kennypw', 'ROLE_USER');
INSERT INTO users (username, password, role) values ('benChan', '{noop}benChanpw', 'ROLE_USER');
INSERT INTO users (username, password, role) values ('LilyTse', '{noop}LilyTsepw', 'ROLE_USER');
INSERT INTO users (username, password, role) values ('user', '{noop}userpw', 'ROLE_USER');

create table poll (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question varchar(255) NOT NULL,
    ans_a varchar(255) NOT NULL,
    ans_b varchar(255) NOT NULL,
    ans_c varchar(255) NOT NULL,
    ans_d varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO poll (question, ans_a, ans_b, ans_c, ans_d) values ('What is the most popular programming language in 90s?', 'Java', 'C++', 'PHP', 'Python');
INSERT INTO poll (question, ans_a, ans_b, ans_c, ans_d) values ('Which programming language is OO?', 'Java', 'C++', 'All of above', 'No one in choice');
INSERT INTO poll (question, ans_a, ans_b, ans_c, ans_d) values ('What is the function for Tomcat', 'be a Web Containers', 'display the webpage', 'handle SQL command', 'firewall');

create table poll_result(
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    user_result  varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO poll_result(question, username, user_result) values ('What is the most popular programming language in 90s?', 'kenny', 'Java');
INSERT INTO poll_result(question, username, user_result) values ('What is the most popular programming language in 90s?', 'benChan', 'Java');
INSERT INTO poll_result(question, username, user_result) values ('Which programming language is OO?', 'user', 'All of above');
INSERT INTO poll_result(question, username, user_result) values ('Which programming language is OO?', 'LilyTse', 'No one in choice');
INSERT INTO poll_result(question, username, user_result) values ('Which programming language is OO?', 'kenny', 'No one in choice');
INSERT INTO poll_result(question, username, user_result) values ('What is the function for Tomcat', 'be a Web Containers?', 'benChan', 'be a Web Containers');

create table post (
    postid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    posttype VARCHAR(255) NOT NULL,
    topic VARCHAR(255) NOT NULL,
    creater VARCHAR(50) NOT NULL,
    createdDate VARCHAR(255) NOT NULL,
    body VARCHAR(255) NOT NULL,
    PRIMARY KEY (postid)
);

INSERT INTO post (posttype, topic, creater, createdDate, body) values ('lecture', 'Lecture 1: What is JavaEE', 'keith', '26/04/2020 16:40:46', 'this lecture will talk about JavaEE');
INSERT INTO post (posttype, topic, creater, createdDate, body) values ('lecture', 'Lecture 2: What is AI', 'andrew', '27/04/2020 19:45:01', 'this lecture will talk about AI');
INSERT INTO post (posttype, topic, creater, createdDate, body) values ('lab', 'Lab 1: intro to JavEE', 'keith', '29/04/2020 20:03:04', 'lab 1 of Java');
INSERT INTO post (posttype, topic, creater, createdDate, body) values ('other', 'I have some question in Lecture 1', 'kenny', '01/05/2020 17:43:11', 'There are some error output when I install Netbeans, Error code:0012');
INSERT INTO post (posttype, topic, creater, createdDate, body) values ('other', 'You can ask any question about the lecture in here', 'keith', '03/05/2020 00:38:07', 'If you have any question, please let me know');

CREATE TABLE attachment (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    postid INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (postid) REFERENCES post(postid)
);

create table comment (
    commentid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    postid int NOT NULL,
    creater VARCHAR(50) NOT NULL,
    comment VARCHAR(255) NOT NULL,
    createdDate VARCHAR(255) NOT NULL,
    PRIMARY KEY (commentid),
    FOREIGN KEY (postid) REFERENCES post(postid)
);

