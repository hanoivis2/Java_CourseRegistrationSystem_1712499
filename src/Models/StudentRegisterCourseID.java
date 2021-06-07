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
	
	
}
