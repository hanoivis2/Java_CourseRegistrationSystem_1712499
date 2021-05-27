package Models;

public class StudentAccount {

	private String id;
	private String fullName;
	private String birthday;
	private String birthplace;
	private String classId;
	
	public StudentAccount(String id, String fullName, String birthday, String birthplace, String classId) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.birthday = birthday;
		this.birthplace = birthplace;
		this.classId = classId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
}
