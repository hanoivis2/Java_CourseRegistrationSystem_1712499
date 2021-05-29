package Models;

public class StudentAccount {

	

	private String id;
	private String fullName;
	private String birthday;
	private String birthplace;
	private Class mainClass = new Class();
	private String password;
	
	public StudentAccount() {}
	
	public StudentAccount(String id, String fullName, String birthday, String birthplace, Class mainClass,
			String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.birthday = birthday;
		this.birthplace = birthplace;
		this.mainClass = mainClass;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Class getMainClass() {
		return mainClass;
	}

	public void setMainClass(Class mainClass) {
		this.mainClass = mainClass;
	}
}
