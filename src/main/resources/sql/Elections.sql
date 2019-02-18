create TABLE Election (
    id INTEGER not null,
    name VARCHAR(255) not null,
    startDate DATE not null,
    endDate DATE not null,
    PRIMARY KEY (id)
);

create table Voter
(
	id INTEGER PRIMARY KEY auto_increment not null,
	student_id INTEGER not null,
	election_id INTEGER not null,
	FOREIGN KEY (election_id) references Election (id)
);

create TABLE Candidate
(
  id INTEGER PRIMARY KEY auto_increment not NULL ,
  student_id INTEGER not null,
  email VARCHAR(255),
  lastName VARCHAR(255),
  firstName VARCHAR(255),
  election_id INTEGER not null,
  FOREIGN KEY (election_id) references Election (id)
)


