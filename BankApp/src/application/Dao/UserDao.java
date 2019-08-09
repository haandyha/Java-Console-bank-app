package application.Dao;

import java.util.ArrayList;

public interface UserDao {

    public boolean confirmLogin(String userName, String password);
    public String getUserRole(String userName);
    public boolean hasAccountAccess(String userName, int accountNumber);
    public boolean doesUserExist(String userName);
    public void saveUser(String userName, String password, String role, ArrayList<Integer> accounts);
}
