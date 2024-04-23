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
            // Obtention de la connexion à la base de données
            connection = Database.getConnection();

            // Requête SQL pour sélectionner toutes les lignes de la table 'sports'
            String query = "SELECT * FROM users";

            // Préparation de la déclaration
            statement = connection.prepareStatement(query);

            // Exécution de la requête
            resultSet = statement.executeQuery();

            // Affichage des résultats
            while (resultSet.next()) {
                // Récupération des données de chaque colonne
                int id = resultSet.getInt("userId");
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                String discipline = resultSet.getString("email");

                // Affichage des données récupérées
                System.out.println("ID: " + id + ", Prénom: " + userName + ", Nom: " + userSurname + ", Email: " + discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
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
