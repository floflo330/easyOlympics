package isep.fr.easyolympics.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabaseConnection {

    public static String getUserListAsString() {
        StringBuilder userList = new StringBuilder();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getConnection();

            String query = "SELECT * FROM users";

            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();
            userList.append("Utilisateurs de la plateforme\n");
            while (resultSet.next()) {
                int id = resultSet.getInt("userId");
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                String discipline = resultSet.getString("email");
                userList.append("ID: ").append(id).append(", Prénom: ").append(userName)
                        .append(", Nom: ").append(userSurname).append(", Email: ").append(discipline).append("\n");
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

        return userList.toString();
    }
}
