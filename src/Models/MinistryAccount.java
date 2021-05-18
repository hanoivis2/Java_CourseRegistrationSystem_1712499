package Models;

public class MinistryAccount {
	
	
	private String username;
	private String fullname;
	private String description;
	
	public MinistryAccount(String username, String fullname, String description) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
