package Models;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Semester implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SemesterID id;
	private String startDate;
	private String endDate;
	private String description;
	private short isCurrentSemester;
	private Set<Course> courses = new HashSet<Course>(0);
	
	public Semester() {}
	

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

	public short getIsCurrentSemester() {
		return isCurrentSemester;
	}

	public void setIsCurrentSemester(short isCurrentSemester) {
		this.isCurrentSemester = isCurrentSemester;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public SemesterID getId() {
		return id;
	}


	public void setId(SemesterID id) {
		this.id = id;
	}

	public static Comparator<Semester> semesterAscendingComparator = new Comparator<Semester>() {

		public int compare(Semester s1, Semester s2) {
		   String semesterSchoolYear1 = s1.getId().getSchoolYear();
		   String semesterSchoolYear2 = s2.getId().getSchoolYear();
		   
		   if (semesterSchoolYear1.equals(semesterSchoolYear2)) {
			   return s1.getId().getName().compareTo(s2.getId().getName());
		   }
		   else {
			   return semesterSchoolYear1.compareTo(semesterSchoolYear2);
		   }
	
		   
		}
	};

	

}
