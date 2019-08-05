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
				// This name will be hashed to generate a new account number
				System.out.println("Please enter a name for this application");
				String name = scan.nextLine();
				int newAcctNum = name.hashCode() + userName.hashCode();
				if(newAcctNum < 0 )newAcctNum = newAcctNum*-1;

				FileEdit.createAccountApplication(userName,newAcctNum);
				break;
			case "j":
				System.out.println("Please enter the account number you want access to");
				String account = scan.nextLine();
				//Validate account number
				if(FileEdit.createJointAccountApplication(account, userName) ){
					System.out.println("Application successful. You are now pending approval.");

				}else{
					System.out.println("You have already applied to this account or the number provided was invalid");
				}
				break;
			case "d":
				System.out.println("Enter the account you'd like to deposit to.");
				String accountTo = scan.nextLine();

				System.out.println("\nEnter the amount you'd like to deposit.");
				String depositAmount = scan.nextLine();

				if(FileEdit.deposit(accountTo, depositAmount, userName) ){
					//success
					System.out.println("Deposit successful!");
				} else {
					//failure
					System.out.println("Please double check you've entered valid information");
				}
				break;

			case "w":
				System.out.println("Enter the account you'd like to withdraw from.");
				String accountFrom = scan.nextLine();

				System.out.println("\nEnter withdraw amount: ");
				String withdrawAmount = scan.nextLine();

				if(FileEdit.withdrawal(accountFrom, withdrawAmount, userName) ){
					//success
					System.out.println("Withdrawal successful!");
				} else {
					//failure
					System.out.println("Please double check you've entered valid information");
				}
				break;

			case "t":
				System.out.println("Enter the account you'd like to withdraw from.");
				String transferFrom = scan.nextLine();
				System.out.println("Enter the account you'd like to deposit to.");
				String transferTo = scan.nextLine();

				System.out.println("\nEnter the transfer amount: ");
				String transferAmount = scan.nextLine();

				if(FileEdit.transferFunds(transferFrom, transferTo, transferAmount, userName) ){
					//success
					System.out.println("Transfer successful!");
				} else {
					//failure
					System.out.println("Please double check you've entered valid information");
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
