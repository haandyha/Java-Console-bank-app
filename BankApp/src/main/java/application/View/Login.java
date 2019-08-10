package application.View;

import java.util.Scanner;

import application.Dao.AccountDaoImpl;
import application.Dao.UserDaoImpl;
import application.Domain.User;
import org.apache.log4j.Logger;

public class Login {
	static Scanner scan = new Scanner(System.in);
	private static final Logger log = Logger.getLogger("");
	private AccountDaoImpl accountDao = new AccountDaoImpl();
	private UserDaoImpl userDao = new UserDaoImpl();

	public Login() {
		super();
	}

	public static void loginMenu(User user) {
		System.out.println("\nSuccessfully logged in.");
		System.out.println("\nWelcome " + user.getUserName() );

		String option = scan.nextLine().toLowerCase();
		while (!option.equals("l")) {
			// Display accounts


			System.out.println("\nPlease select an option:");
			System.out.println("(a) Apply for new account.");
			System.out.println("(j) Apply for Joint account.");
			System.out.println("(d) Deposit.");
			System.out.println("(w) Withdraw.");
			System.out.println("(t) Transfer between accounts");
			System.out.println("(l) Log out.");
			option = scan.nextLine().toLowerCase();
			switch (option) {
			case "a":
				// Apply for new account
				break;
			case "j":
				// Apply for jointAccount
				break;
			case "d":
				// Deposit into an account
				break;

			case "w":
				// Withdraw from account
				break;

			case "t":
				// Transfer funds between accounts
				break;
			case "l":
				System.out.println("\nSuccessfully logged out.\n");
				log.info(user.getUserName() + " logged out.");
				break;
			default:
				System.out.println("Invalid selection.");
				break;
			}
		}
	}
}
