package application.View;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeLogin {
	static Scanner scan = new Scanner(System.in);
	
	//employee account method
	public static void userAccount(String userName) {
		System.out.println("\nWelcome " + userName);
		String option = "";
		String input = "";
		boolean success = false;
		
		while(!"l".equals(option)) {
			System.out.println("Employee menu:");
			System.out.println("(v) View all accounts.");
			System.out.println("(b) View account balance.");
			System.out.println("(a) Approve pending account.");
			System.out.println("(d) Deny pending account.");
			System.out.println("(l) Log out.");
			option = scan.nextLine().toLowerCase();
			switch(option) {
			case "v":
				System.out.println("(v) View all accounts.");
				break;
			
			case "b":
				System.out.println("(b) View account balance.");
				break;
				
			case "a":
				System.out.println("(a) Approve account application.");
				break;
				
			case "d":
				System.out.println("(d) Deny account application.");

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
