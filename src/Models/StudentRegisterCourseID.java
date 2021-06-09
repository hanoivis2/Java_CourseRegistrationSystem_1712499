package Models;

import java.io.Serializable;

public class StudentRegisterCourseID implements Serializable {

	private static final long serialVersionUID = 1L;
	private String studentId;
	private String subjectId;
	private String subjectName;
	private short subjectCredits;
	private String semesterName;
	private String semesterSchoolYear;
	private String theoryTeacherName;
	private String roomName;
	private String dayInWeek;
	private short shift;
	
	public StudentRegisterCourseID() {}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public String getSemesterSchoolYear() {
		return semesterSchoolYear;
	}

	public void setSemesterSchoolYear(String semesterSchoolYear) {
		this.semesterSchoolYear = semesterSchoolYear;
	}

	public short getSubjectCredits() {
		return subjectCredits;
	}

	public void setSubjectCredits(short subjectCredits) {
		this.subjectCredits = subjectCredits;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getTheoryTeacherName() {
		return theoryTeacherName;
	}

	public void setTheoryTeacherName(String theoryTeacherName) {
		this.theoryTeacherName = theoryTeacherName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getDayInWeek() {
		return dayInWeek;
	}

	public void setDayInWeek(String dayInWeek) {
		this.dayInWeek = dayInWeek;
	}

	public short getShift() {
		return shift;
	}

	public void setShift(short shift) {
		this.shift = shift;
	}
	
	
}
