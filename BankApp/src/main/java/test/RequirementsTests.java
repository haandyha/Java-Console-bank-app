package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import application.Dao.UserDaoImpl;
import application.Domain.User;
import org.junit.Test;

public class RequirementsTests {
	UserDaoImpl userDao = new UserDaoImpl();
	/*
		Verify a duplicate user cannot be created in the database
	 */
	@Test
	public void cannotCreateDuplicateUser() {
		if(!userDao.doesUserExist("Fabrizio") ){
			userDao.saveUser(new User("Fabrizio", "password", "Customer") );
		}
		assertFalse(userDao.saveUser(new User("Fabrizio", "password", "Customer") ) );
	}

}
