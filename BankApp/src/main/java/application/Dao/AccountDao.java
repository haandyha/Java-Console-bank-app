package application.Dao;

import java.util.ArrayList;

import application.Domain.Account;

public interface AccountDao {
    public Account getBalance(int accountNumber);
    public boolean setBalance(int accountNumber);
    public boolean deposit(int accountNumber, double amount);
    public boolean withdraw(int accountNumber, double amount);
    public boolean transfer(int accountFrom, int accountTo, double amount);
    public boolean createAccountApplication(String userName, int newAcctNum);
    public boolean createAccount(int accountNumber);
    public boolean createJointAccountApplication(int account, String userName);
    public boolean changeAccountStatus(int accountNumber, char operation);
    public ArrayList<Integer> getAccountsByUser(String userName);
    public void getAccountStatus(int accountNumber);
    public Account getAccountByNumber(int accountNumber);
    public ArrayList<Integer> getAllAccounts(); 
}
