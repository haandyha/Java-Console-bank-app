package application.Service;

import application.Dao.AccountDao;
import application.Domain.User;

import java.util.Map;

/*
    - Not sure what the return values should be just yet, e.g. boolean or strings
    - Considering breaking up this class e.g. CustomerAccountService, AdminAccountService to split size of this class
    although it's not totally necessary.
    - Method parameters are almost always going to be strings since they are almost always going to be user input
 */
public class AccountService {

    private AccountDao accountDao;


    public boolean deposit(String accountNumber, String amount){
        return accountDao.deposit(accountNumber, amount);
    }

    public boolean withdraw(String accountNumber, String amount){
        return accountDao.withdraw(accountNumber, amount);
    }

    public boolean transfer(String accountFrom, String accountTo, String amount){
        return accountDao.transfer(accountFrom, accountTo, amount);
    }

    public String getAccountBalance(String accountNumber){
        return accountDao.getBalance(accountNumber);
    }

    public boolean createAccountApplication(User user){
        return false;
    }

    public boolean createJoingApplication(User user, String accountNum){
        return false;
    }

    public Map<String, String> getAccounts(User user){
        return null;
    }

    public Map<String, String> getAllAccounts(){
        return null;
    }

    public boolean approveAccount(String accountNum){
        return false;
    }

    public boolean denyAccount(String accountNum){
        return false;
    }

    public boolean cancelAccount(String accountNum){
        return false;
    }

}
