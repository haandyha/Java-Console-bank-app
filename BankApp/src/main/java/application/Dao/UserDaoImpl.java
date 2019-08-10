package application.Dao;

import application.Domain.User;
import application.Utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

	@Override
	public User confirmLogin(String userName, String password) {
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
		String sql = "SELECT USERNAME FROM USERS WHERE USER_NAME = ?";
		try(Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql) ){

			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();

			//if rs returns a value then the sql query has returned an existing user
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean saveUser(User user) {
		// Verify user does not already exist
		return false;
	}

}
