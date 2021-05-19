package Models;

public class Class {

	
	private String id;
	private int totalStudents;
	private int totalMale;
	private int totalFemale;
	private String description;
	
	public Class(String id, int totalStudents, int totalMale, int totalFemale, String description) {
		super();
		this.id = id;
		this.totalStudents = totalStudents;
		this.totalMale = totalMale;
		this.totalFemale = totalFemale;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}

	public int getTotalMale() {
		return totalMale;
	}

	public void setTotalMale(int totalMale) {
		this.totalMale = totalMale;
	}

	public int getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(int totalFemale) {
		this.totalFemale = totalFemale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
