package application.Domain;

import java.io.Serializable;

public class Account implements Serializable {
	private int accountNumber;
	private double balance;
	private String status;
	
	public Account() {
		super();
	}

	public Account(int accountNumber, double balance, String status) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.status = status;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
