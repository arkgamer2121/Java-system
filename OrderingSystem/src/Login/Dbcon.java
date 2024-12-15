package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcon {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Ensure the database driver is loaded
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Return the database connection
        return DriverManager.getConnection("jdbc:mysql://localhost/cruddb", "root", "");
    }
}
