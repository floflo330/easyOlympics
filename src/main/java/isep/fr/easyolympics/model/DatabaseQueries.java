package isep.fr.easyolympics.model;
import isep.fr.easyolympics.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.fxml.FXMLLoader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseQueries {
    // Méthode pour récupérer tous les utilisateurs
    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String query = "SELECT * FROM users";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idUser");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                // Utilisez le constructeur de User pour créer un nouvel utilisateur
                User user = new User(id, username, email, password);
                users.add(user);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return users;
    }


    // Méthode pour récupérer un utilisateur par son identifiant
    public static User getUserById(int userId) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String query = "SELECT * FROM users WHERE idUser = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("userId");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                // Utilisez le constructeur de User pour créer un nouvel utilisateur
                user = new User(id, username, email, password);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return user;
    }


    public static User getUserByEmailAndPassword(String email, String password) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idUser");
                String username = rs.getString("name");
                // Récupérez d'autres colonnes si nécessaire
                user = new User(id, username, email, password);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return user;
    }
    public static Map<String, Map<String, Integer>> getMedalsByCountries() throws SQLException {
        Map<String, Map<String, Integer>> medalsByCountry = new HashMap<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String query = "SELECT\n" +
                    "    c.name AS country_name,\n" +
                    "    SUM(CASE WHEN m.rank = 1 THEN 1 ELSE 0 END) AS gold_count,\n" +
                    "    SUM(CASE WHEN m.rank = 2 THEN 1 ELSE 0 END) AS silver_count,\n" +
                    "    SUM(CASE WHEN m.rank = 3 THEN 1 ELSE 0 END) AS bronze_count\n" +
                    "FROM\n" +
                    "    medals m\n" +
                    "INNER JOIN\n" +
                    "    countries c ON m.country = c.idCountry\n" +
                    "GROUP BY\n" +
                    "    m.country;\n";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String countryName = rs.getString("country_name");
                int goldCount = rs.getInt("gold_count");
                int silverCount = rs.getInt("silver_count");
                int bronzeCount = rs.getInt("bronze_count");

                Map<String, Integer> countryMedals = new HashMap<>();
                countryMedals.put("gold", goldCount);
                countryMedals.put("silver", silverCount);
                countryMedals.put("bronze", bronzeCount);

                medalsByCountry.put(countryName, countryMedals);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return medalsByCountry;
    }

    public static List<String> getAthletes() throws SQLException {
        List<String> athletes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String query = "SELECT a.name, a.surname, a.email, c.name as country, s.name as sport " +
                    "FROM athletes a " +
                    "JOIN countries c ON a.idCountry = c.idCountry " +
                    "JOIN sports s ON a.idSport = s.idSport";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String sport = rs.getString("sport");
                String athleteInfo = String.format("%s %s, Email: %s, Country: %s, Sport: %s", name, surname, email, country, sport);
                athletes.add(athleteInfo);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return athletes;
    }
    public static List<String> getSports() throws SQLException {
        List<String> sports = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String query = "SELECT name FROM sports";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String sport = rs.getString("name");
                sports.add(sport);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return sports;
    }

}


