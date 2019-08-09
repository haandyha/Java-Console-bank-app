package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*This class allows you to use system variables. 
*Very useful when you don't want to upload your credentials to a public repository for all to see. 
*/
public class ConnectionUtil {
private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");
		
		if(connection == null || connection.isClosed())
			connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
}
