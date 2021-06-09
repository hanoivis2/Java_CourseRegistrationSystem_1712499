CREATE TABLE ministry_account (
	username VARCHAR(20) NOT NULL,
	full_name VARCHAR(50) NOT NULL,
	description VARCHAR(200),
	password VARCHAR(20),
	PRIMARY KEY (username)
);

CREATE TABLE subject (
	id VARCHAR(10) NOT NULL,
	name VARCHAR(150) NOT NULL,
	credits SMALLINT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE semester (
	name VARCHAR(5) NOT NULL,
	school_year VARCHAR(12) NOT NULL,
	start_date VARCHAR(20),
	end_date VARCHAR(20),
	description VARCHAR(100),
	is_current_semester SMALLINT,
	PRIMARY KEY (name, school_year)
);

CREATE TABLE class (
	id VARCHAR(10) NOT NULL,
	students_amount SMALLINT,
	male_amount SMALLINT,
	female_amount SMALLINT,
	description VARCHAR(100),
	PRIMARY KEY (id)
);

CREATE TABLE student_account (
	id VARCHAR(20) NOT NULL,
	full_name VARCHAR(50) NOT NULL,
	birthday VARCHAR(20),
	birthplace VARCHAR(50),
	class_id VARCHAR(10),
	password VARCHAR(20),
	gender SMALLINT,
	FOREIGN KEY (class_id) REFERENCES class(id),
	PRIMARY KEY (id)
);

CREATE TABLE registration_session (
	start_date VARCHAR(20) NOT NULL,
	end_date VARCHAR(20) NOT NULL,
	description VARCHAR(100),
	semester_name VARCHAR(5) NOT NULL,
	semester_school_year VARCHAR(12) NOT NULL,
	FOREIGN KEY (semester_name, semester_school_year) REFERENCES semester(name, school_year),
	PRIMARY KEY (start_date, end_date)
);

CREATE TABLE course (
	subject_id VARCHAR(10) NOT NULL,
	semester_name VARCHAR(5) NOT NULL,
	semester_school_year VARCHAR(12) NOT NULL,
	theory_teacher_name VARCHAR(70),
	room_name VARCHAR(10),
	day_in_week VARCHAR(15),
	shift SMALLINT,
	maximum_amount SMALLINT,
	FOREIGN KEY (subject_id) REFERENCES subject(id),
	FOREIGN KEY (semester_name, semester_school_year) REFERENCES semester(name, school_year),
	PRIMARY KEY (subject_id, semester_name, semester_school_year)
);

CREATE TABLE subject_in_class (
	subject_id VARCHAR(10) NOT NULL,
	class_id VARCHAR(10) NOT NULL,
	semester_name VARCHAR(5) NOT NULL,
	semester_school_year VARCHAR(12) NOT NULL,
	FOREIGN KEY (subject_id) REFERENCES subject(id),
	FOREIGN KEY (semester_name, semester_school_year) REFERENCES semester(name, school_year),
	FOREIGN KEY (class_id) REFERENCES class(id)
);

CREATE TABLE student_register_course (
	student_id VARCHAR(10) NOT NULL,
	subject_id VARCHAR(10) NOT NULL,
	subject_name VARCHAR(150) NOT NULL,
	subject_credits SMALLINT NOT NULL,
	semester_name VARCHAR(5) NOT NULL,
	semester_school_year VARCHAR(12) NOT NULL,
	create_date VARCHAR(20),
	FOREIGN KEY (student_id) REFERENCES student_account(id),
	FOREIGN KEY (subject_id, semester_name, semester_school_year) REFERENCES course(subject_id, semester_name, semester_school_year),
	PRIMARY KEY (student_id, subject_id, subject_name, subject_credits, semester_name, semester_school_year)
);

INSERT INTO ministry_account(username, full_name, description, password) VALUES ('GV001', 'Lâm Quang Vũ', 'Giáo vụ khoa CNTT', '111111');
