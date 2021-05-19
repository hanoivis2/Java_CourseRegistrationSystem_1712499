package Models;

public class Semester {

	private String semesterName;
	private String schoolYear;
	private String startDate;
	private String endDate;
	
	public Semester(String semesterName, String schoolYear, String startDate, String endDate) {
		super();
		this.semesterName = semesterName;
		this.schoolYear = schoolYear;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
