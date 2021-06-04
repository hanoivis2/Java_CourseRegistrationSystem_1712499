package Models;

import java.util.HashSet;
import java.util.Set;

public class Subject {

	private String id;
	private String name;
	private short credits;
	private Set<Course> courses = new HashSet<Course>(0);
	
	public Subject() {} 
	
	public Subject(String id, String name, short credits) {
		super();
		this.id = id;
		this.name = name;
		this.credits = credits;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public short getCredits() {
		return credits;
	}

	public void setCredits(short credits) {
		this.credits = credits;
	}
	
}
