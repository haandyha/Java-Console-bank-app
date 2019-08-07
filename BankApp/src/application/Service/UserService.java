package application.Service;

import application.Dao.UserDao;
import application.Domain.User;

public class UserService {
    private UserDao userDao;

    /*
        May need to be replaced with getUser()
     */
    public boolean doesUserExist(String username){
        return false;
    }

    /*
        May need to be replaced with getUser()
     */
    public boolean confirmLogin(String username, String password){
        return userDao.confirmLogin(username,password);
    }

    /*
        This would allow a valid user to be returned for easy processing of the proper view.
        If login credentials are correct a simple User.getRole() can be used to send the app to the proper role view
     */
    public User getUser(String username, String password){
        return null;
    }



}
