package Models;

import java.util.Comparator;

public class RegistrationSession {

	
	private RegistrationSessionID id;
	private String description;
	private String semesterName;
	private String semesterSchoolYear;
	private Semester semester = new Semester();
	
	public RegistrationSession() {}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public String getSemesterSchoolYear() {
		return semesterSchoolYear;
	}

	public void setSemesterSchoolYear(String semesterSchoolYear) {
		this.semesterSchoolYear = semesterSchoolYear;
	}


	public RegistrationSessionID getId() {
		return id;
	}


	public void setId(RegistrationSessionID id) {
		this.id = id;
	}
	
	public Semester getSemester() {
		return semester;
	}


	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	public static Comparator<RegistrationSession> registrationSessionAscendingComparator = new Comparator<RegistrationSession>() {

		public int compare(RegistrationSession s1, RegistrationSession s2) {
			
			   String semesterSchoolYear1 = s1.getSemesterSchoolYear();
			   String semesterSchoolYear2 = s2.getSemesterSchoolYear();
			   
			   if (semesterSchoolYear1.equals(semesterSchoolYear2)) {
				   return s1.getSemesterName().compareTo(s2.getSemesterName());
			   }
			   else {
				   return semesterSchoolYear1.compareTo(semesterSchoolYear2);
			   }
		}
	};

	
}
