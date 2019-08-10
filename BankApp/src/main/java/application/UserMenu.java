package application;

import java.util.Scanner;

import application.Dao.UserDaoImpl;
import application.Domain.User;
import application.View.AdminLogin;
import application.View.EmployeeLogin;
import application.View.Login;
import org.apache.log4j.Logger;

public class UserMenu {

	static Scanner scan = new Scanner(System.in);
	private static final Logger log = Logger.getLogger("");

	public static void main(String[] args) {
		System.out.println("Welcome.");
		String option = "";
		User user = null;
		//display menu until user selects to exit
		while(!option.equals("e")) {
			System.out.println("Please select an option:");
			System.out.println("(c) Create an account.");
			System.out.println("(l) Log in.");
			System.out.println("(e) Exit.");
			option = scan.nextLine().toLowerCase();//accept upper and lower case letters
			switch(option) {
			case "c":
				System.out.println("\nCreate user option selected:");
				String userName="", password1="", password2="";
				
				System.out.println("\nEnter user name: ");
				userName = scan.nextLine().toLowerCase();
				//if passwords don't match allow user to try again
				do {
					System.out.println("\nEnter password: ");
					password1 = scan.nextLine();
					
					System.out.println("\nConfirm password: ");
					password2 = scan.nextLine();
					
					if(!password1.equals(password2))
						System.out.println("\nPasswords do not match.");
				}while(!password1.equals(password2));
					
					user = new User(userName, password1, "CUSTOMER");
					UserDaoImpl userDao = new UserDaoImpl();
					if(userDao.saveUser(user) ){
						log.info(userName + " created an account.");
					} else {
						log.error("Could not successfuly create user : " + user.getUserName() );
					}
				break;
				
			case "l":
				// Confirm login
				// Send the confirmed user to the appropriate view menu
				break;
				
			case "e":
				System.out.println("\nGoodbye.\n");
				break;
				
			default:
				System.out.println("Invalid selection.");
				break;
			}
		}
	}

}
