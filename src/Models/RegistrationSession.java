package Models;

public class RegistrationSession {

	
	private String startDate;
	private String endDate;
	private String description;
	private String semesterName;
	private String semesterSchoolYear;
	
	public RegistrationSession(String startDate, String endDate, String description, String semesterName,
			String semesterSchoolYear) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.semesterName = semesterName;
		this.semesterSchoolYear = semesterSchoolYear;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
