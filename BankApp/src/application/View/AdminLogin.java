package application.View;

import application.Dao.FileEdit;
import application.Dao.FileRead;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminLogin {
	static Scanner scan = new Scanner(System.in);
	
	//admin account menu
	public static void userAccount(String userName) {
		System.out.println("Welcome " + userName);
		String option = "";
		String input = "";
		String acct = "";
		String amount = "";
		boolean success = false;
		
		while(!"l".equals(option)) {
			System.out.println("Administrator menu: ");
			System.out.println("(v) View all accounts.");
			System.out.println("(b) view account balance.");
			System.out.println("(c) Cancel account.");
			System.out.println("(e) Deposit.");
			System.out.println("(w) Withdraw.");
			System.out.println("(t) transfer.");
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
				System.out.println("Enter account number:");
				input = scan.nextLine();
				System.out.println(FileRead.getAccountBalance(input));
				break;
				
			case "c":
				System.out.println("Cancel account menu:");
				System.out.println("Enter account number:");
				input = scan.nextLine();
				success = FileEdit.changeAccountStatus(input, 'c');
				if(success)
					System.out.println("Account canceled.");
				break;
				
			case "e":
				System.out.println("Deposit menu:");
				System.out.println("Enter account number to deposit into:");
				acct = scan.nextLine();
				System.out.println("Enter amount:");
				amount = scan.nextLine();
				//call deposit method here
				break;
				
			case "w":
				System.out.println("Withdraw menu:");
				System.out.println("Enter account number to withdraw from:");
				acct = scan.nextLine();
				System.out.println("Enter amount:");
				amount = scan.nextLine();
				//call withdraw method here
				break;
				
			case "t":
				System.out.println("Transfer menu:");
				System.out.println("Enter account number to withdraw from:");
				String from = scan.nextLine();
				System.out.println("Enter account number to deposit into:");
				String to = scan.nextLine();
				System.out.println("Enter amount:");
				amount = scan.nextLine();
				//call transfer method here
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
