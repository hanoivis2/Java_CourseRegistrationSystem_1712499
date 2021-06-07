package Models;

import java.util.Comparator;

public class MinistryAccount {
	
	
	private String username;
	private String fullname;
	private String description;
	private String password;
	
	public MinistryAccount() {}

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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static Comparator<MinistryAccount> ministryAccountAscendingComparator = new Comparator<MinistryAccount>() {

		public int compare(MinistryAccount s1, MinistryAccount s2) {
		   String username1 = s1.getUsername();
		   String username2 = s2.getUsername();
	
		   return username1.compareTo(username2);
		}
	};

	
}
