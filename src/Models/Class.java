package Models;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Class {

	private String id;
	private short totalStudents;
	private short totalMale;
	private short totalFemale;
	private String description;
	private Set<StudentAccount> students = new HashSet<StudentAccount>(0);
	
	public Class() {};
	
	public Class(String id, short totalStudents, short totalMale, short totalFemale, String description,
			Set<StudentAccount> students) {
		super();
		this.id = id;
		this.totalStudents = totalStudents;
		this.totalMale = totalMale;
		this.totalFemale = totalFemale;
		this.description = description;
		this.students = students;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public short getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(short totalStudents) {
		this.totalStudents = totalStudents;
	}

	public short getTotalMale() {
		return totalMale;
	}

	public void setTotalMale(short totalMale) {
		this.totalMale = totalMale;
	}

	public short getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(short totalFemale) {
		this.totalFemale = totalFemale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<StudentAccount> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentAccount> students) {
		this.students = students;
	}
	
	public static Comparator<Class> classIdAscendingComparator = new Comparator<Class>() {

		public int compare(Class s1, Class s2) {
		   String classId1 = s1.getId();
		   String classId2 = s2.getId();
	
		   return classId1.compareTo(classId2);
		}
	};
	
}
