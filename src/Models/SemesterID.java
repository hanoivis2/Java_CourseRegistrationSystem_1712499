package Models;

import java.io.Serializable;

public class SemesterID implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String name;
	private String schoolYear;
	
	public SemesterID() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SemesterID other = (SemesterID) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (schoolYear == null) {
			if (other.schoolYear != null)
				return false;
		} else if (!schoolYear.equals(other.schoolYear))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + " / " + schoolYear;
	}
	
}
