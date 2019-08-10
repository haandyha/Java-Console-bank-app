package application.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Domain.Account;
import application.Domain.User;
import application.Utils.ConnectionUtil;

public class AccountDaoImpl implements AccountDao{

	/**
	 * This method creates a SQL query to read the balance associated with the provided account number.
	 * The Account object is then updated with the new balance, waiting to be pulled for use.
	 */
	@Override
	public Account getBalance(int accountNumber) {
		String sql = "SELECT BALANCE FROM BANK_ACCOUNTS WHERE USER_NAME = ?";
		Account a = new Account();
		try(Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			//The first arg: '1' identifies the first '?' in the sql string declared above.
			//The second arg: converts the account number to type string and replaces the '?'.
			ps.setString(1, Integer.toString(accountNumber));
			ResultSet rs = ps.executeQuery();
			
			//if rs returns a value then the sql query has returned the balance
			if(rs.next()) {
				double balance = rs.getDouble("BALANCE");
				a.setBalance(balance);
			}
			//otherwise nothing was returned and a '-1' is set to signal an error has occurred.
			else {
				a.setBalance(-1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public boolean setBalance(int accountNumber) {
		return false;
	}

	@Override
	public boolean deposit(int accountNumber, double amount) {
		return false;
	}

	@Override
	public boolean withdraw(int accountNumber, double amount) {
		return false;
	}

	@Override
	public boolean transfer(int accountFrom, int accountTo, double amount) {
		return false;
	}

	@Override
	public Account getAccount(int accountNum) {
		return null;
	}

	@Override
	public boolean denyAccountApplication(int accountNum) {
		return false;
	}

	@Override
	public boolean denyJointApplication(String username, int accountNum) {
		return false;
	}

	@Override
	public boolean approveAccountApplication(int accountNum) {
		return false;
	}

	@Override
	public boolean approveJointApplication(String username, int accountNum) {
		return false;
	}

	@Override
	public boolean cancelAccount(int accountNum) {
		return false;
	}

	@Override
	public int createAccountApplication(User user) {
		return 0;
	}

	@Override
	public boolean createAccount(double balance, char status) {
		return false;
	}

	@Override
	public boolean createJointAccountApplication(int account) {
		return false;
	}

	@Override
	public boolean changeAccountStatus(int accountNumber, char operation) {
		return false;
	}

	@Override
	public ArrayList<Integer> getUserAccounts(String userName) {
		return null;
	}

	@Override
	public void getAccountStatus(int accountNumber) {

	}

	@Override
	public ArrayList<Integer> getAllAccounts() {
		return null;
	}

	@Override
	public ArrayList<Account> getPendingJoingApplications() {
		return null;
	}

	@Override
	public ArrayList<Account> getPendingApplications() {
		return null;
	}

}
