package Models;

public class StudentRegisterCourse {

	
	private String studentId;
	private String subjectId;
	private String subjectName;
	private int subjectCredits;
	private String semesterName;
	private String semesterSchoolYear;
	private String createDate;
	
	public StudentRegisterCourse(String studentId, String subjectId, String subjectName, int subjectCredits,
			String semesterName, String semesterSchoolYear, String createDate) {
		super();
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectCredits = subjectCredits;
		this.semesterName = semesterName;
		this.semesterSchoolYear = semesterSchoolYear;
		this.createDate = createDate;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

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

	public int getSubjectCredits() {
		return subjectCredits;
	}

	public void setSubjectCredits(int subjectCredits) {
		this.subjectCredits = subjectCredits;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
