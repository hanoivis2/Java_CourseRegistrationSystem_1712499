package Models;

import java.io.Serializable;

public class CourseID implements Serializable {

	private static final long serialVersionUID = 1L;
	private String subjectId;
	private String semesterName;
	private String semesterSchoolYear;
	
	public CourseID() {}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseID other = (CourseID) obj;
		if (semesterName == null) {
			if (other.semesterName != null)
				return false;
		} else if (!semesterName.equals(other.semesterName))
			return false;
		if (semesterSchoolYear == null) {
			if (other.semesterSchoolYear != null)
				return false;
		} else if (!semesterSchoolYear.equals(other.semesterSchoolYear))
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		return true;
	}

}
