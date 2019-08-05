package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileEdit {
	static String userFilePath = "src/storage/Users.txt";
	static String accBalFilePath = "src/storage/AccountBalance.txt";
	static String acctAccessPath = "src/storage/AccountAccess.txt";
	
	//overwrite user's new balance
	public static boolean adjustBalance(String userName, double amount, char operation) {
		String oldContent = "";
		String oldLine = "";
		//read in current user balance
		double currBal = Double.parseDouble(readBalance(userName));
		if(operation == 'd') {
			try(BufferedWriter accWrite = new BufferedWriter(new FileWriter(accBalFilePath));
				BufferedReader br = new BufferedReader(new FileReader(accBalFilePath));){
				
				double newBal = currBal + amount;
				int accountNum = userName.hashCode();
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
				accWrite.write(newContent);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else if(operation == 'w') {
			try(BufferedWriter accWrite = new BufferedWriter(new FileWriter(accBalFilePath));
				BufferedReader br = new BufferedReader(new FileReader(accBalFilePath));){
				
				double newBal = currBal - amount;
				int accountNum = userName.hashCode();
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
				accWrite.write(newContent);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else if(operation == 't') {
			
		}
		return false;
	}
	
	//read in users balance
	public static String readBalance(String userName) {
		String output = "error";
		int accountNum = userName.hashCode();
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
				accWrite.append("\n" + acctNum + " : 0");
				acctAccessWrite.append("\n" + acctNum + " : PENDING : " + userName);
				System.out.println("\nUser successfuly saved.\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
