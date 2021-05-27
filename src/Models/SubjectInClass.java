package Models;

public class SubjectInClass {

	
	private String subjectId;
	private String classId;
	private String semesterName;
	private String semesterSchoolYear;
	
	
	public SubjectInClass(String subjectId, String classId, String semesterName, String semesterSchoolYear) {
		super();
		this.subjectId = subjectId;
		this.classId = classId;
		this.semesterName = semesterName;
		this.semesterSchoolYear = semesterSchoolYear;
	}

	public String getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
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
}
