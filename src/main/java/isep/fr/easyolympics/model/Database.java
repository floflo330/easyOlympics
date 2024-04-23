package isep.fr.easyolympics.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11701300";
    private static final String USER = "sql11701300";
    private static final String PASSWORD = "xDFXbUXiJY";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}