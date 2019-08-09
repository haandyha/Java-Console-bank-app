package application.Domain;

import java.util.ArrayList;

public class User {
	private String userName;
	private String password;
	private String role;
	private ArrayList<Integer> accounts;
	
	public User() {
		super();
	}

	public User(String userName, String password, String role, ArrayList<Integer> accounts) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.accounts = accounts;
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

	public ArrayList<Integer> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Integer> accounts) {
		this.accounts = accounts;
	}
	
}
