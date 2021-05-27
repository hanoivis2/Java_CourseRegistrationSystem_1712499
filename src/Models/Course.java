package Models;

public class Course {
	
	private String subjectId;
	private String semesterName;
	private String semesterSchoolYear;
	private String theoryTeacherName;
	private String roomName;
	private String dayInWeek;
	private int shift;
	private int maxAmountStudent;
	
	public Course(String subjectId, String semesterName, String semesterSchoolYear, String theoryTeacherName,
			String roomName, String dayInWeek, int shift, int maxAmountStudent) {
		super();
		this.subjectId = subjectId;
		this.semesterName = semesterName;
		this.semesterSchoolYear = semesterSchoolYear;
		this.theoryTeacherName = theoryTeacherName;
		this.roomName = roomName;
		this.dayInWeek = dayInWeek;
		this.shift = shift;
		this.maxAmountStudent = maxAmountStudent;
	}
	

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public int getMaxAmountStudent() {
		return maxAmountStudent;
	}

	public void setMaxAmountStudent(int maxAmountStudent) {
		this.maxAmountStudent = maxAmountStudent;
	}
}
