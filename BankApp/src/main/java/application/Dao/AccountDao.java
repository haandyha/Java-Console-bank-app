package application.Dao;

import java.util.ArrayList;

import application.Domain.Account;
import application.Domain.User;

public interface AccountDao {
    public Account getBalance(int accountNumber);
    public boolean setBalance(int accountNumber);
    public boolean deposit(int accountNumber, double amount);
    public boolean withdraw(int accountNumber, double amount);
    public boolean transfer(int accountFrom, int accountTo, double amount);
    public Account getAccount(int accountNum);
    public boolean denyAccountApplication(int accountNum);
    public boolean denyJointApplication(String username, int accountNum);
    public boolean approveAccountApplication(int accountNum);
    public boolean approveJointApplication(String username, int accountNum);
    public boolean cancelAccount(int accountNum);
    public int createAccountApplication(User user);
    public boolean createAccount(double balance, char status);
    public boolean createJointAccountApplication(int account);
    public boolean changeAccountStatus(int accountNumber, char operation);
    public ArrayList<Integer> getUserAccounts(String userName);
    public void getAccountStatus(int accountNumber);
    public ArrayList<Integer> getAllAccounts();
    public ArrayList<Account> getPendingJointApplications();
    public ArrayList<Account> getPendingApplications();
}
