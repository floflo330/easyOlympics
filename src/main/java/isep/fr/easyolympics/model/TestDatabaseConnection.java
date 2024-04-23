package isep.fr.easyolympics.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getConnection();

            String query = "SELECT * FROM users";

            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();
            System.out.println("Utilisateurs de la plateforme");
            while (resultSet.next()) {
                int id = resultSet.getInt("userId");
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                String discipline = resultSet.getString("email");
                System.out.println("ID: " + id + ", Prénom: " + userName + ", Nom: " + userSurname + ", Email: " + discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
