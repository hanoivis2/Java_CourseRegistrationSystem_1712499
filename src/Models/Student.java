package Models;

import java.io.Serializable;
import java.util.Comparator;

public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private float grade;
	private String avatar;
	private String address;
	private String notes;
	
	
	public Student() {}
	
	public Student(int id, String name, float grade, String avatar, String address, String notes) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.avatar = avatar;
		this.address = address;
		this.notes = notes;
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
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	

    public static Comparator<Student> studentIdAscendingComparator = new Comparator<Student>() {

		public int compare(Student s1, Student s2) {
		   int studentId1 = s1.getId();
		   int studentId2 = s2.getId();
	
		   return studentId1 - studentId2;
		}
	};
	
	public static Comparator<Student> studentIdDescendingComparator = new Comparator<Student>() {

		public int compare(Student s1, Student s2) {
		   int studentId1 = s1.getId();
		   int studentId2 = s2.getId();
	
		   return studentId2 - studentId1;
		}
	};
	
	public static Comparator<Student> studentGradeAscendingComparator = new Comparator<Student>() {

		public int compare(Student s1, Student s2) {
		   float studentGrade1 = s1.getGrade();
		   float studentGrade2 = s2.getGrade();
	
		   if (studentGrade1 <= studentGrade2) return -1;
		   if (studentGrade1 > studentGrade2) return 1;
		   return 0;
		   
		}
	};

	public static Comparator<Student> studentGradeDescendingComparator = new Comparator<Student>() {

		public int compare(Student s1, Student s2) {
		   float studentGrade1 = s1.getGrade();
		   float studentGrade2 = s2.getGrade();
	
		   if (studentGrade1 <= studentGrade2) return 1;
		   if (studentGrade1 > studentGrade2) return -1;
		   return 0;
		}
	};

	@Override
	public String toString() {
		return id + ". " + name + ", GPA:" + grade + ", avatarURL:" + avatar + ", address:"
				+ address + ", notes:" + notes;
	}
	
}

