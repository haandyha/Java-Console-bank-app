package application;

import java.util.Scanner;

public class EmployeeLogin {
	static Scanner scan = new Scanner(System.in);
	
	public static void empAcount(String userName) {
		System.out.println("\nWelcome " + userName);
		String option = "";
		
		while(!"l".equals(option)) {
			System.out.println("Employee menu:");
			System.out.println("(a) View all accounts.");
			System.out.println("(s) Select account.");
			System.out.println("(u) Select user.");
			System.out.println("(l) Log out.");
			option = scan.nextLine().toLowerCase();
			switch(option) {
			case "a": 
				System.out.println("All accounts:");
				//call getAllAcounts method here
				break;
				
			case "s":
				System.out.print("Enter account number: ");
				//call getAccountByAcctNum method here
				break;
				
			case "u":
				System.out.print("Enter user name: ");
				//call getAccountsAsUser method here
				break;
				
			case "l":
				System.out.println("Successfully logged out.");
				break;
				
			default: 
				System.out.println("Invalid selection.");
				break;
			}
		}
	}
}
