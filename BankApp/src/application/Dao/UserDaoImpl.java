package application.Dao;

import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean confirmLogin(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserRole(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAccountAccess(String userName, int accountNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doesUserExist(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveUser(String userName, String password, String role, ArrayList<Integer> accounts) {
		// TODO Auto-generated method stub
		
	}

}
