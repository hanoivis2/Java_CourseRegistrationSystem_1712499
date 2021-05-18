package Models;

public class Course {
	
	
	private String courseID;
	private String courseName;
	private int credits;
	private String theoryTeacherName;
	private String roomName;
	private String dayInWeek;
	private int shift;
	private int maxAmountStudent;
	
	public Course(String courseID, String courseName, int credits, String theoryTeacherName, String roomName,
			String dayInWeek, int shift, int maxAmountStudent) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.credits = credits;
		this.theoryTeacherName = theoryTeacherName;
		this.roomName = roomName;
		this.dayInWeek = dayInWeek;
		this.shift = shift;
		this.maxAmountStudent = maxAmountStudent;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
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
