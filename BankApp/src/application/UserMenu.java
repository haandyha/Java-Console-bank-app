package application;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class UserMenu {

	static Scanner scan = new Scanner(System.in);
	private static final Logger log = Logger.getLogger("");
	
	public static void main(String[] args) {
		System.out.println("Welcome.");
		//log.debug("log test");
		String option = "";
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
				
					User.setUserName(userName);
					User.setPassword(password1);
					User.setRole("CUSTOMER");
					User.store();
					log.info(userName + " created an account.");
				break;
				
			case "l":
				System.out.println("\nLog in option selected.");
				System.out.println("\nEnter user name: ");
				userName = scan.nextLine().toLowerCase();
				//check to see user exists
				if(FileEdit.doesUserExist(userName)) {
					System.out.println("\nEnter password: ");
					password1 = scan.nextLine();
					//ensure correct password is associated with each user
					if(FileEdit.confirmLogin(userName, password1)) {
						log.info(userName + " logged in.");
						Login.UserAccount(userName);
					}
					else {
						System.out.println("\nIncorrect password.");
						log.info("Failed login attempt.");
					}
				}
				else {
					System.out.println("\nUser does not exist.");
					log.info("Invalid user name entered.");
				}
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
