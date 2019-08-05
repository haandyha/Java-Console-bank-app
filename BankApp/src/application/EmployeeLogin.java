package application;

import java.util.Scanner;

public class EmployeeLogin {
	static Scanner scan = new Scanner(System.in);
	
	public static void userAcount(String userName) {
		System.out.println("\nWelcome " + userName);
		String option = "";
		String input = "";
		
		while(!"l".equals(option)) {
			System.out.println("Employee menu:");
			System.out.println("(v) View all accounts.");
			System.out.println("(a) Approve pending account.");
			System.out.println("(d) Deny pending account.");
			System.out.println("(l) Log out.");
			option = scan.nextLine().toLowerCase();
			switch(option) {
			case "v": 
				System.out.println("All accounts:");
				FileRead.getAllAccounts();
				break;
				
			case "a":
				System.out.println("Account approval menu:");
				System.out.print("Enter account number: ");
				input = scan.nextLine();
				FileEdit.changeAccountStatus(input, 'a');
				break;
				
			case "d":
				System.out.println("Account denial menu:");
				System.out.print("Enter account number: ");
				input = scan.nextLine();
				FileEdit.changeAccountStatus(input, 'd');
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
