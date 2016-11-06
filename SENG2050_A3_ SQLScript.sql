drop table Users;
drop table Issue;
drop table UserComment;

CREATE TABLE Users(
	username varchar(80),
    pass varchar(80),
    userType varchar(20),
    firstName varchar(100),
    lastName varChar(100),
    email varchar(100),
    contactNumber varchar(20),
    PRIMARY KEY (username)
);

CREATE TABLE Issue ( 
	issueID int(10),
    state varchar(80),
    category varchar(80),
    title varchar(100),
    description varchar(1000),
	location varchar(200),
	browser varchar(200),
	website varchar(200),
	internalAccess boolean,
	alternateBrowser boolean,
	computerRestart boolean,
	errorMessage varchar(500),
    resolutionDetails varchar(1000),
    reportDateTime varchar(100),
    resolvedDateTime varchar(100),
    username varchar(80),
    notification boolean,
    PRIMARY KEY (issueID),
    FOREIGN KEY (username) REFERENCES Users
);

CREATE TABLE UserComment(
	commentID int(10),
	submitionDateTime varchar(80),
    content varchar(1000),
    commentType varchar(80),
    username varchar(80),
    issueID int(10),
    PRIMARY KEY (commentID),
    FOREIGN KEY (username) REFERENCES Users,
    FOREIGN KEY (issueID) references Issue
);

INSERT INTO Users VALUES ('staff1', 'pass', 'staff', 'Dan', 'McK', 'dan@mail.com', '0445123456');
INSERT INTO Users VALUES ('student1', 'pass', 'student', 'Brendan', 'Rog', 'Bren@mail.com', '0445123457');
INSERT INTO Users VALUES ('staff2', 'pass', 'staff', 'Jack', 'New', 'jack@mail.com', '0445123458');
INSERT INTO Users VALUES ('student2', 'pass', 'student', 'James', 'Arth', 'jam@mail.com', '0445123445');
INSERT INTO Users VALUES ('staff3', 'pass', 'staff', 'Steve', 'Buscemi', 'stev@mail.com', '0445134456');
INSERT INTO Users VALUES ('student3', 'pass', 'student', 'Leo', 'Decap', 'leo@mail.com', '0125123457');
