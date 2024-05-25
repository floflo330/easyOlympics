package isep.fr.easyolympics.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
        //    private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11701300";
        //    private static final String USER = "sql11701300";
        //    private static final String PASSWORD = "xDFXbUXiJY";
        private static final String URL = "jdbc:mysql://localhost:3306/easyolympics";
        private static final String USER = "root";
        private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}