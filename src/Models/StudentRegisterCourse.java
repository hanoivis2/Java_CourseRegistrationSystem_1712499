package Models;

public class StudentRegisterCourse {
	
	private StudentRegisterCourseID id;
	private String createDate;
	
	public StudentRegisterCourse() {}
	
	

	public StudentRegisterCourseID getId() {
		return id;
	}

	public void setId(StudentRegisterCourseID id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}



	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
