package application.Dao;

import application.Domain.User;

public class AccountDao {

    public String getBalance(String accountNumber){
        return "0.00";
    }

    public boolean setBalance(String accountNumber){
        return false;
    }

    public boolean deposit(String accountNumber, String amount) {
        return false;
    }

    public boolean withdraw(String accountNumber, String amount) {
        return false;
    }

    public boolean transfer(String accountFrom, String accountTo, String amount) {
        return false;
    }
}
