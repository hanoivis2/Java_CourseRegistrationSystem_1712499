package Models;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class StudentAccount {

	

	private String id;
	private String fullName;
	private String birthday;
	private String birthplace;
	private String classId;
	private short gender;
	private String password;
	private Class mainClass = new Class();
	private Set<Course> courses = new HashSet<Course>(0);
	
	public StudentAccount() {}
	

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


	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	public short getGender() {
		return gender;
	}


	public void setGender(short gender) {
		this.gender = gender;
	}
	
	public Set<Course> getCourses() {
		return courses;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public static Comparator<StudentAccount> studentIdAscendingComparator = new Comparator<StudentAccount>() {

		public int compare(StudentAccount s1, StudentAccount s2) {
		   String classId1 = s1.getId();
		   String classId2 = s2.getId();
	
		   return classId1.compareTo(classId2);
		}
	};

	

	
}
