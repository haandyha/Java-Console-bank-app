package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileEdit {
	static String userFilePath = "src/storage/Users.txt";
	static String accBalFilePath = "src/storage/AccountBalance.txt";
	static String acctAccessPath = "src/storage/AccountAccess.txt";
	
	//overwrite user's new balance
	public static boolean adjustBalance(int accountNum, double amount, char operation) {
		String oldContent = "";
		String oldLine = "";
		//read in current user balance
		double currBal = Double.parseDouble(readBalance(accountNum));
		if(operation == 'd') {
			try(BufferedReader br = new BufferedReader(new FileReader(accBalFilePath));){
				
				double newBal = currBal + amount;
				String newLine = accountNum + " : " + newBal;
				
				//copy old content and find line by account number
				String line = br.readLine();
				while(line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					if(line.contains(Integer.toString(accountNum))) {
						oldLine = line;
					}
					line = br.readLine();
				}
				String newContent = oldContent.replaceAll(oldLine, newLine);
				BufferedWriter accWrite = new BufferedWriter(new FileWriter(accBalFilePath));
				accWrite.write(newContent);
				accWrite.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else if(operation == 'w') {
			try(BufferedReader br = new BufferedReader(new FileReader(accBalFilePath));){
				
				double newBal = currBal - amount;
				String newLine = accountNum + " : " + newBal;
				
				//copy old content and find line by account number
				String line = br.readLine();
				while(line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					if(line.contains(Integer.toString(accountNum))) {
						oldLine = line;
					}
					line = br.readLine();
				}
				String newContent = oldContent.replaceAll(oldLine, newLine);
				BufferedWriter accWrite = new BufferedWriter(new FileWriter(accBalFilePath));
				accWrite.write(newContent);
				accWrite.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	//validates before calling adjustBalance method
	public static boolean withdrawal(String acctFrom, String amount, String userName) {
		try {
			// Validate account
			if (doesAccountExist(acctFrom)) {
				double withdrawalAmount = Double.parseDouble(amount);
				int acctNum = Integer.parseInt(acctFrom);
				double acctBalance = Double.parseDouble(readBalance(acctNum) );
				if(withdrawalAmount > 0){
					// Validate no overdraft
					if(withdrawalAmount <= acctBalance ){
						//Validate access to this account
						if(hasAccountAccess(acctFrom, userName) ){
							adjustBalance(acctNum,withdrawalAmount,'w');
							return true;
						}
					}
				}
			}
		}catch (Exception e){
			return false;
		}

		return false;
	}

	//validates before calling adjustBalance method
	public static boolean deposit(String acctFrom, String amount, String userName) {
		try {
			// Validate account
			if (doesAccountExist(acctFrom)) {
				double deposit = Double.parseDouble(amount);
				int acctNum = Integer.parseInt(acctFrom);
				if(deposit > 0){
					//Validate access to this account
					if(hasAccountAccess(acctFrom, userName) ){
						adjustBalance(acctNum,deposit,'d');
						return true;
					}
				}
			}
		}catch (Exception e){
			return false;
		}
		return false;
	}

	/*
		If both steps of the transfer are not successful the transfer will reverse the initial withdrawal
	 */
	public static boolean transferFunds(String acctFrom, String accountTo, String amount, String userName) {
		// Verify withdrawal is successful
		if(withdrawal(acctFrom, amount, userName) ){
			// Verify deposit is successful
			if(deposit(accountTo, amount, userName) ){
				return true;
			}
			// If deposit fails then funds must be sent back to withdrawal account
			else {
				deposit(acctFrom,amount,userName);
				return false;
			}

		}
		return false;
	}
	
	
	//validates account is accessible
	public static boolean hasAccountAccess(String accountNum, String userName) {
		String output = "error";
		try(BufferedReader accountReader = new BufferedReader(new FileReader(acctAccessPath) );
			BufferedReader userReader = new BufferedReader(new FileReader(userFilePath) )){
			// Check if user has Admin rights
			String line = userReader.readLine();
			while(line != null){
				if(line.contains(userName) && line.contains("ADMIN") ){
					return true;
				}
				line = userReader.readLine();
			}
			userReader.close();
			// Check if user has access to account
			line = accountReader.readLine();
			while(line != null) {
				if(line.contains(accountNum) && line.contains(userName) ) {
					return true;
				}
				line = accountReader.readLine();
			}
			accountReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//read in users balance
	public static String readBalance(int accountNum) {
		String output = "error";
		try(BufferedReader br = new BufferedReader(new FileReader(accBalFilePath))){
			String line = br.readLine();
			while(line != null) {
				if(line.contains(Integer.toString(accountNum))) {
					String[] strArr = line.split(":", 2);
					String balance = strArr[1];
					output = balance;
				}
				line = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	//confirm user name and password
	public static boolean confirmLogin(String userName, String password) {
		try(BufferedReader br = new BufferedReader(new FileReader(userFilePath))){
			
			String line = br.readLine();
			while(line != null) {
				if(line.contains(userName+" : "+password)) {
					return true;
				}
				line = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//check if user already exists in file
	public static boolean doesUserExist(String userName) {
		try(BufferedReader br = new BufferedReader(new FileReader(userFilePath))){
			
			String line = br.readLine();
			while(line != null) {
				if(line.contains(userName)) {
					return true;
				}
				line = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/*
		Checks AccountBalance file for existing accounts since this file is only for approved/active accounts
	 */
	public static boolean doesAccountExist(String account) {
		try(BufferedReader br = new BufferedReader(new FileReader(accBalFilePath))){
			// Verify account string is actually an int
			int acc = Integer.parseInt(account);

			String line = br.readLine();
			while(line != null) {
				if(line.contains(account) ) {
					return true;
				}
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	//write user to users file and create balance of $0 in AccountBalance file
	public static void saveUser(String userName, String password, String role) {
		try(
				BufferedWriter usersWrite = new BufferedWriter(new FileWriter(userFilePath, true));
				BufferedWriter accWrite = new BufferedWriter(new FileWriter(accBalFilePath,true));
				BufferedWriter acctAccessWrite = new BufferedWriter(new FileWriter(acctAccessPath,true));
			){
			
			if(doesUserExist(userName))
				System.out.println("\nUser already exists\n");
			
			else {
				/*
				Set new account with hashed acct# 0 balance & status pending

				 */
				int acctNum = userName.hashCode();
				if(acctNum<0)acctNum = acctNum*-1;

				usersWrite.append("\n" + userName + " : " + password + " : " + role);
				acctAccessWrite.append("\n" + acctNum + " : PENDING : " + userName);
				System.out.println("\nUser successfuly saved.\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//creates pending account in AccountAccess file
	public static void createAccountApplication(String userName, int newAcctNum) {
		try(	BufferedWriter accWrite = new BufferedWriter(new FileWriter(accBalFilePath,true));
				BufferedWriter acctAccessWrite = new BufferedWriter(new FileWriter(acctAccessPath,true) ) ){

			acctAccessWrite.append("\n" + newAcctNum + " : PENDING : " + userName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//when account is approved, create account in AccountBalance file
	public static void createAccount(String acctNum) {
		try(BufferedWriter accWrite = new BufferedWriter(new FileWriter(accBalFilePath,true) ) ){

			accWrite.append("\n" + acctNum + " : 2500.00");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
		Verifies that the account number given for Joing application is valid and not already pending approval
	 */
	public static boolean createJointAccountApplication(String account, String userName) {
		try(BufferedWriter accAccessWrite = new BufferedWriter(new FileWriter(acctAccessPath,true) ) ){
			// This will pull all instances of a valid account number
			ArrayList<String> existingAccount = FileRead.getAccount(account);

			// Verify the accountNumber provided is valid
			if( existingAccount.size() != 0 ){
				// Verify that this user has not already applied to this account number
				for(int i=0; i < existingAccount.size(); i++){
					if(existingAccount.get(i).contains(userName) ){
						return false;
					}
				}
				accAccessWrite.append("\n" + existingAccount.get(0) + "," + userName);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	//sets account status to APPROVED, DENIED, or CANCELED
	public static boolean changeAccountStatus(String accountNum, char operation) {
		if(operation == 'a') {
			try(BufferedReader br = new BufferedReader(new FileReader(acctAccessPath))){
				
				String oldContent = "";
				String oldLine = "";
				String newLine = "";
				String line = br.readLine();
				//copy old content in file
				while(line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					if(line.contains(accountNum)) {
						//find line to be replaced
						oldLine = line;
						String strArr[] = line.split(":");
						String userName = strArr[2];
						//create new line with updated values
						newLine = accountNum + " : APPROVED : " + userName;
					}
					line = br.readLine();
				}
				//swaps old line with new line
				String newContent = oldContent.replaceAll(oldLine, newLine);
				//overwrites the file with updated value
				BufferedWriter accWrite = new BufferedWriter(new FileWriter(acctAccessPath));
				accWrite.write(newContent);
				accWrite.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else if(operation == 'd') {
			try(BufferedReader br = new BufferedReader(new FileReader(acctAccessPath))){
				
				String oldContent = "";
				String oldLine = "";
				String newLine = "";
				String line = br.readLine();
				while(line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					if(line.contains(accountNum)) {
						oldLine = line;
						String strArr[] = line.split(":");
						String userName = strArr[2];
						newLine = accountNum + " : DENIED : " + userName;
					}
					line = br.readLine();
				}
				String newContent = oldContent.replaceAll(oldLine, newLine);
				BufferedWriter accWrite = new BufferedWriter(new FileWriter(acctAccessPath));
				accWrite.write(newContent);
				accWrite.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else if(operation == 'c') {
			try(BufferedReader br = new BufferedReader(new FileReader(acctAccessPath))){
				
				String oldContent = "";
				String oldLine = "";
				String newLine = "";
				String line = br.readLine();
				while(line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					if(line.contains(accountNum)) {
						oldLine = line;
						String strArr[] = line.split(":");
						String userName = strArr[2];
						newLine = accountNum + " : CANCELED : " + userName;
					}
					line = br.readLine();
				}
				String newContent = oldContent.replaceAll(oldLine, newLine);
				BufferedWriter accWrite = new BufferedWriter(new FileWriter(acctAccessPath));
				accWrite.write(newContent);
				accWrite.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try(BufferedReader br = new BufferedReader(new FileReader(accBalFilePath))){
				
				String oldContent = "";
				String oldLine = "";
				String newLine = "";
				String line = br.readLine();
				while(line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					if(line.contains(accountNum)) {
						oldLine = line;
						newLine = "";
					}
					line = br.readLine();
				}
				String newContent = oldContent.replaceAll(oldLine, newLine);
				BufferedWriter accWrite = new BufferedWriter(new FileWriter(accBalFilePath));
				accWrite.write(newContent);
				accWrite.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
}
