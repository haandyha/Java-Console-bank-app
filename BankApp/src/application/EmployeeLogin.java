package application;

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
				System.out.println("All accounts:");
				ArrayList<String> accounts = FileRead.getAllAccounts();
				for(String a: accounts)
					System.out.println(a);
				break;
			
			case "b":
				System.out.println("Account balance menu:");
				System.out.println("enter account number:");
				input = scan.nextLine();
				System.out.println(FileRead.getAccountBalance(input));
				break;
				
			case "a":
				System.out.println("Account approval menu:");
				System.out.print("Enter account number: ");
				input = scan.nextLine();
				success = FileEdit.changeAccountStatus(input, 'a');
				FileEdit.createAccount(input);
				if(success)
					System.out.println("Account approved.");
				break;
				
			case "d":
				System.out.println("Account denial menu:");
				System.out.print("Enter account number: ");
				input = scan.nextLine();
				success = FileEdit.changeAccountStatus(input, 'd');
				if(success)
					System.out.println("Account Denied.");
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
