package application.View;

import application.Dao.AccountDaoImpl;
import application.Dao.UserDaoImpl;
import application.Domain.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminLogin {
	static Scanner scan = new Scanner(System.in);
	private static final Logger log = Logger.getLogger("");
	private AccountDaoImpl accountDao = new AccountDaoImpl();
	private UserDaoImpl userDao = new UserDaoImpl();
	//admin account menu
	public static void adminMenu(User user) {
		System.out.println("Welcome " + user.getUserName() );
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
				System.out.println("(v) View all accounts.");
				break;
			
			case "b":
				System.out.println("(b) view account balance.");
				break;
				
			case "c":
				System.out.println("(c) Cancel account.");

				break;
				
			case "e":
				System.out.println("(e) Deposit.");
				break;
				
			case "w":
				System.out.println("(w) Withdraw.");

				break;
				
			case "t":
				System.out.println("(t) transfer.");
				break;
				
			case "a":
				System.out.println("(a) Approve pending account.");

				break;
				
			case "d":
				System.out.println("(d) Deny pending account.");

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
