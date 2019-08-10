package application.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	private String userName;
	private String password;
	private String role;
	
	public User() {
		super();
	}

	public User(String userName, String password, String role, ArrayList<Integer> accounts) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public User(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
