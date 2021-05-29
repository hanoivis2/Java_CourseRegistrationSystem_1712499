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
	FOREIGN KEY (subject_id) REFERENCES subject(id),
	FOREIGN KEY (semester_name, semester_school_year) REFERENCES semester(name, school_year),
	PRIMARY KEY (student_id, subject_id, subject_name, subject_credits, semester_name, semester_school_year)
);

INSERT INTO ministry_account(username, full_name, description, password) VALUES ('GV001', 'Lâm Quang Vũ', 'Giáo vụ khoa CNTT', '111111');
INSERT INTO ministry_account(username, full_name, description, password) VALUES ('GV002', 'Văn Chí Nam', 'Giáo vụ trường', '121212');
INSERT INTO ministry_account(username, full_name, description, password) VALUES ('GV003', 'Hồ Tuấn Thanh', 'Giáo vụ khoa CNTT', '333333');


INSERT INTO subject(id, name, credits) VALUES ('MH0001', 'Thị Giác Máy Tính', 4);
INSERT INTO subject(id, name, credits) VALUES ('MH0002', 'Nhập môn CNTT', 3);
INSERT INTO subject(id, name, credits) VALUES ('MH0003', 'Mạng máy tính', 3);
INSERT INTO subject(id, name, credits) VALUES ('MH0004', 'Xác suất thống kê', 4);
INSERT INTO subject(id, name, credits) VALUES ('MH0005', 'Toán tuyến tính', 4);
INSERT INTO subject(id, name, credits) VALUES ('MH0006', 'Khai thác dữ liệu và ứng dụng', 4);


INSERT INTO class(id, students_amount, male_amount, female_amount, description) VALUES ('17CTTN', 0, 0, 0, 'Lớp cử nhân tài năng khoa CNTT khoá 2017');
INSERT INTO class(id, students_amount, male_amount, female_amount, description) VALUES ('17CTT1', 0, 0, 0, 'Lớp 1 khoa CNTT khoá 2017');
INSERT INTO class(id, students_amount, male_amount, female_amount, description) VALUES ('17CTT2', 0, 0, 0, 'Lớp 2 khoa CNTT khoá 2017');
INSERT INTO class(id, students_amount, male_amount, female_amount, description) VALUES ('17CTT3', 0, 0, 0, 'Lớp 3 khoa CNTT khoá 2017');
INSERT INTO class(id, students_amount, male_amount, female_amount, description) VALUES ('17CTT4', 0, 0, 0, 'Lớp 4 khoa CNTT khoá 2017');


INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712499', 'Trần Gia Huy', '17/09/1999', 'TP HCM', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712488', 'Bùi Đỗ Huy', '25/08/1999', 'TP HCM', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712480', 'Huỳnh Đức Huy', '12/03/1999', 'Bến Tre', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712466', 'Nguyễn Hữu Huân', '24/08/1999', 'Khánh Hoà', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712506', 'Kỳ Tuấn Khang', '02/09/1999', 'Khánh Hoà', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712516', 'Huỳnh Thị Khánh Huyên', '21/12/1999', 'Đắk Lắk', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712501', 'Nguyễn Văn Kha', '14/03/1999', 'Đồn Tháp', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712502', 'Phạm Kinh Kha', '29/11/1999', 'TP HCM', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712503', 'Trần Nhựt Kha', '05/07/1999', 'Bến Tre', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712504', 'Trần Tuấn Kiệt', '14/05/1999', 'Bình Thuận', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712477', 'Lê Tấn Hưng', '03/07/1999', 'Ninh Thuận', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712478', 'Nguyễn Đông Hưng', '17/09/1999', 'Bình Định', '17CTT4', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712010', 'Lê Tấn Tài', '02/06/1999', 'Đà Nẵng', '17CTTN', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712020', 'Phạm Hoàng Đức', '04/08/1999', 'TP HCM', '17CTTN', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712110', 'Nguyễn Thành Nguyên', '32/12/1999', 'An Giang', '17CTT1', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712111', 'Hồng Như Ngọc', '13/01/1999', 'An Giang', '17CTT1', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712201', 'Nguyễn Phương Anh', '21/07/1999', 'Đồng Tháp', '17CTT2', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712202', 'Tôn Kim Ái', '05/09/1999', 'Đắk Lắk', '17CTT2', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712301', 'Nguyễn Quí Em', '18/03/1999', 'Vĩnh Long', '17CTT3', '111111');
INSERT INTO student_account(id, full_name, birthday, birthplace, class_id, password) VALUES ('1712302', 'Mai Linh Đồng', '25/06/1999', 'Gia Lai', '17CTT3', '111111');
