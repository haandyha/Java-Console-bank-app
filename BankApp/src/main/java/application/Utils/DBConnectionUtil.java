package application.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
    private static String url = "jdbc:postgresql://class-1809-august.chueiwozbnfz.us-east-1.rds.amazonaws.com:5432/testdb";
    private static String username = "augustduet";
    private static String password = "masterpassword";


    //legacy support
    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection newConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
