package application.Dao;

import application.Domain.User;

import java.util.ArrayList;

public interface UserDao {

    /*
        This method will act as the intial getUser for the appropriate user view
     */
    public User confirmLogin(String userName, String password);
    public boolean hasAccountAccess(String userName, int accountNumber);
    public boolean doesUserExist(String userName);
    public boolean saveUser(User user);

}
