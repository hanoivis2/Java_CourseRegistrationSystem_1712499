package Models;

import java.io.Serializable;

public class Student implements Serializable {
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String birthday;
	private String birthplace;
	
	
	public Student() {}
	
	public Student(int id, String name, String birthday, String birthplace) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.birthplace = birthplace;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

    
	
}

