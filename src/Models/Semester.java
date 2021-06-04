package Models;

import java.io.Serializable;

public class Semester implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String schoolYear;
	private String startDate;
	private String endDate;
	private String description;
	private int isCurrentSemester;
	
	public Semester() {}
	
	public Semester(String name, String schoolYear, String startDate, String endDate, String description,
			int isCurrentSemester) {
		super();
		this.name = name;
		this.schoolYear = schoolYear;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.isCurrentSemester = isCurrentSemester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsCurrentSemester() {
		return isCurrentSemester;
	}

	public void setIsCurrentSemester(int isCurrentSemester) {
		this.isCurrentSemester = isCurrentSemester;
	}
}
