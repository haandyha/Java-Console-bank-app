package application;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Login {
	static Scanner scan = new Scanner(System.in);
	private static final Logger log = Logger.getLogger("");

	public Login() {
		super();
	}

	public static void displayAccounts(String userName) {
		ArrayList<String> accounts = FileRead.getAccountsAsUser(userName);

		for (int i = 0; i < accounts.size(); i++) {
			System.out.println(accounts.get(i));
		}
	}

	public static void UserAccount(String userName) {
		System.out.println("\nSuccessfully logged in.");
		double amount = 0;
		int accountNum = userName.hashCode();
		String option = "", confirm;

		while (!option.equals("l")) {
			System.out.println("Welcome " + userName);
			System.out.println("ACCOUNTS");
			System.out.println("--------------");

			displayAccounts(userName);

			String status = FileRead.getAccountStatus(accountNum);
			if (!"approved".equals(status)) {
				System.out.println("(l) Logout.");
				option = scan.nextLine().toLowerCase();
			} 
			else {
				String balance = FileEdit.readBalance(accountNum);
				// System.out.println("Current balance: $"+balance);

				System.out.println("\nPlease select an option:");
				System.out.println("(d) Deposit.");
				System.out.println("(w) Withdraw.");
				System.out.println("(l) Log out.");
				option = scan.nextLine().toLowerCase();
				switch (option) {
				case "d":
					System.out.println("\nDeposit option selected.");
					amount = 0;
					System.out.println("\nEnter deposit amount: ");
					amount = Double.parseDouble(scan.nextLine());
					System.out.println("\nConfirm deposit amount: " + amount + " (y/n)?");
					confirm = scan.nextLine();
					if (confirm.equals("y")) {
						boolean adjust = FileEdit.adjustBalance(accountNum, amount, 'd');
						if (adjust) {
							System.out.println(amount + " Successfully deposited.");
							log.info(userName + " deposited $" + amount);
						} 
						else
							System.out.println("error.");
					}
					break;

				case "w":
					System.out.println("\nWithdraw option selected.");
					amount = 0;
					System.out.println("\nEnter withdraw amount: ");
					amount = Double.parseDouble(scan.nextLine());
					System.out.println("\nConfirm withdraw amount: " + amount + " (y/n)?");
					confirm = scan.nextLine();
					if (confirm.equals("y")) {
						if (amount > Double.parseDouble(balance)) {
							System.out.println("Unable to process: withdraw amount exceeds current balance.");
						} 
						else {
							boolean adjust = FileEdit.adjustBalance(accountNum, amount, 'w');
							if (adjust) {
								System.out.println(amount + " Successfully withdrawn.");
								log.info(userName + " withdrew $" + amount);
							} 
							else
								System.out.println("error.");
						}
					}
					break;

				case "l":
					System.out.println("\nSuccessfully logged out.\n");
					log.info(userName + " logged out.");
					break;
				default:
					System.out.println("Invalid selection.");
					break;
				}
			}
		}
	}
}
