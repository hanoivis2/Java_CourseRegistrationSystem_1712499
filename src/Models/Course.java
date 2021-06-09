package Models;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Course implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourseID id;
	private short maxAmountStudent;
	private Subject subject = new Subject();
	private Semester semester = new Semester();
	private Set<StudentAccount> students = new HashSet<StudentAccount>(0);
	
	public Course() {}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public Semester getSemester() {
		return semester;
	}


	public void setSemester(Semester semester) {
		this.semester = semester;
	}


	public CourseID getId() {
		return id;
	}


	public void setId(CourseID id) {
		this.id = id;
	}


	public short getMaxAmountStudent() {
		return maxAmountStudent;
	}


	public void setMaxAmountStudent(short maxAmountStudent) {
		this.maxAmountStudent = maxAmountStudent;
	}
	
	public Set<StudentAccount> getStudents() {
		return students;
	}


	public void setStudents(Set<StudentAccount> students) {
		this.students = students;
	}
	
	public static Comparator<Course> courseAscendingComparator = new Comparator<Course>() {

		public int compare(Course s1, Course s2) {
		   String courseSchoolYear1 = s1.getId().getSemesterSchoolYear();
		   String courseSchoolYear2 = s2.getId().getSemesterSchoolYear();
		   
		   if (courseSchoolYear1.equals(courseSchoolYear1)) {
			   return s1.getId().getSemesterName().compareTo(s2.getId().getSemesterName());
		   }
		   else {
			   return courseSchoolYear2.compareTo(courseSchoolYear1);
		   }
	
		   
		}
	};

	public static Comparator<Course> courseSemesterAscendingComparator = new Comparator<Course>() {

		public int compare(Course s1, Course s2) {
		   String semesterSchoolYear1 = s1.getSemester().getId().getSchoolYear();
		   String semesterSchoolYear2 = s2.getSemester().getId().getSchoolYear();
		   
		   if (semesterSchoolYear1.equals(semesterSchoolYear2)) {
			   return s1.getSemester().getId().getName().compareTo(s2.getSemester().getId().getName());
		   }
		   else {
			   return semesterSchoolYear1.compareTo(semesterSchoolYear2);
		   }
	
		   
		}
	};

}
