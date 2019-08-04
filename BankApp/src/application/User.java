package application;

public class User {
	private static String userName;
	private static String password;
	private static String role;
	
	public User() {
		super();
	}

	public static String getUserName() {
		return userName;
	}
	public static void setUserName(String userName) {
		User.userName = userName;
	}
	
	public static String getRole() {
		return role;
	}

	public static void setRole(String role) {
		User.role = role;
	}

	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		User.password = password;
	}

	public static void store() {
		FileEdit.saveUser(userName, password, role);
	}
	
}
