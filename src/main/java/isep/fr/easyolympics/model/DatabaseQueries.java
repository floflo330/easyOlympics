package isep.fr.easyolympics.model;
import isep.fr.easyolympics.model.User;

import java.sql.*;

import javafx.fxml.FXMLLoader;

import java.time.LocalDate;
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
                    "    countries c ON m.idCountry = c.idCountry\n" +
                    "GROUP BY\n" +
                    "    c.name;";
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
            String query = "SELECT name FROM sports ORDER BY name";
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
    public static List<Event> getEventsForDay(String date) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Event> events = new ArrayList<>();

        try {
            conn = Database.getConnection();
            String query = "SELECT e.idEvent, e.name, e.place, e.date, e.time, e.idSport, s.name AS sportName " +
                    "FROM events e " +
                    "JOIN sports s ON e.idSport = s.idSport " +
                    "WHERE e.date = ?";
            stmt = conn.prepareStatement(query);
            stmt.setDate(1, java.sql.Date.valueOf(date)); // Convertir la date String en java.sql.Date
            rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("idEvent"),
                        rs.getString("name"),
                        rs.getString("place"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getInt("idSport"),
                        rs.getString("sportName")
                );
                events.add(event);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return events;
    }

    public static void addEvent(Event event) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String query = "INSERT INTO events (name, place, date, time, idSport) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getPlace());
            stmt.setString(3, event.getDate());
            stmt.setString(4, event.getTime());
            stmt.setInt(5, event.getIdSport());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Méthode pour récupérer tous les événements
    public static List<Event> getEvents() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Event> events = new ArrayList<>();

        try {
            conn = Database.getConnection();
            String query = "SELECT e.idEvent, e.name, e.place, e.date, e.time, e.idSport, s.name AS sportName " +
                    "FROM events e " +
                    "JOIN sports s ON e.idSport = s.idSport";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("idEvent"),
                        rs.getString("name"),
                        rs.getString("place"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getInt("idSport"),
                        rs.getString("sportName")
                );
                events.add(event);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return events;
    }

    // Méthode pour supprimer un événement
    public static void deleteEvent(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String query = "DELETE FROM events WHERE idEvent = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }



    public static void addSport(String sportName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String query = "INSERT INTO sports (name) VALUES (?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, sportName);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public static void deleteSport(String sportName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String query = "DELETE FROM sports WHERE name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, sportName);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }


    public static void deleteCountry(String countryName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String query = "DELETE FROM countries WHERE name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, countryName);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }



    public static void deleteSportById(int sportId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String query = "DELETE FROM sports WHERE idSport = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, sportId);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public static List<String> getCountries() throws SQLException {
        List<String> countries = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String query = "SELECT name FROM countries ORDER BY name";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String country = rs.getString("name");
                countries.add(country);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return countries;
    }

    public static void addAthlete(String name, String surname, String email, int idCountry, LocalDate birthDate, char sex, int idSport) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String query = "INSERT INTO athletes (name, surname, email, idCountry, birthDate, sex, idSport) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, email);
            stmt.setInt(4, idCountry);
            stmt.setDate(5, java.sql.Date.valueOf(birthDate));
            stmt.setString(6, String.valueOf(sex)); // Assurez-vous que sex est un caractère unique
            stmt.setInt(7, idSport);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public static int getCountryIdByName(String countryName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idCountry = -1;

        try {
            conn = Database.getConnection();
            String query = "SELECT idCountry FROM countries WHERE name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, countryName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                idCountry = rs.getInt("idCountry");
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return idCountry;
    }

    public static int getSportIdByName(String sportName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idSport = -1;

        try {
            conn = Database.getConnection();
            String query = "SELECT idSport FROM sports WHERE name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, sportName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                idSport = rs.getInt("idSport");
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return idSport;
    }
    public static List<Athlete> getAthletesByEvent(int eventId) throws SQLException {
        List<Athlete> athletes = new ArrayList<>();
        String query = "SELECT a.* FROM athletes a JOIN events_athletes ea ON a.idAthlete = ea.idAthlete WHERE ea.idEvent = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, eventId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("idAthlete");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String country = rs.getString("idCountry");
                    athletes.add(new Athlete(id, name, surname, country));
                }
            }
        }
        return athletes;
    }

    public static List<String> getAthletesBySport(String sportName) throws SQLException {
        List<String> athletes = new ArrayList<>();
        String query = "SELECT a.name FROM athletes a JOIN sports s ON a.idSport = s.idSport WHERE s.name = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sportName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    athletes.add(rs.getString("name"));
                }
            }
        }
        return athletes;
    }

    public static List<String> getEventNames() throws SQLException {
        List<String> eventNames = new ArrayList<>();
        Connection connection = Database.getConnection();
        String query = "SELECT name FROM events";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                eventNames.add(resultSet.getString("name"));
            }
        }
        return eventNames;
    }

    public static List<String> getAthletesForEvent(String eventName) throws SQLException {
        List<String> athleteNames = new ArrayList<>();
        Connection connection = Database.getConnection();
        String query = "SELECT a.name\n" +
                "FROM athletes a\n" +
                "JOIN events_athletes ea ON a.idAthlete = ea.idAthlete\n" +
                "JOIN events e ON ea.idEvent = e.idEvent\n" +
                "WHERE e.name = ?\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, eventName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    athleteNames.add(resultSet.getString("name"));
                }
            }
        }
        return athleteNames;
    }

    // Method to save result
    public static void saveResult(String athleteName, String eventName, String score, String time) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "INSERT INTO results (idAthlete, idEvent, score, time) VALUES ((SELECT idAthlete FROM athletes WHERE name = ?), (SELECT idEvent FROM events WHERE name = ?), ?, ?) LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, athleteName);
            preparedStatement.setString(2, eventName);
            preparedStatement.setString(3, score);
            preparedStatement.setString(4, time);
            preparedStatement.executeUpdate();
        }
    }


    public static void addAthleteToEvent(int athleteId, int eventId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Obtenir la connexion à la base de données
            connection = Database.getConnection();

            // Préparer la requête SQL pour insérer l'athlète à l'événement dans la table events_athletes
            String query = "INSERT INTO events_athletes (idAthlete, idEvent) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);

            // Remplir les paramètres de la requête avec l'ID de l'athlète et de l'événement
            preparedStatement.setInt(1, athleteId);
            preparedStatement.setInt(2, eventId);

            // Exécuter la requête pour ajouter l'athlète à l'événement
            preparedStatement.executeUpdate();
        } finally {
            // Fermer les ressources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static int getAthleteIdByName(String athleteName) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int athleteId = -1; // Valeur par défaut si l'athlète n'est pas trouvé

        try {
            // Obtenir la connexion à la base de données
            connection = Database.getConnection();

            // Préparer la requête SQL pour obtenir l'ID de l'athlète par son nom
            String query = "SELECT idAthlete FROM athletes WHERE name = ?";
            preparedStatement = connection.prepareStatement(query);

            // Remplir les paramètres de la requête avec le nom de l'athlète
            preparedStatement.setString(1, athleteName);

            // Exécuter la requête pour obtenir l'ID de l'athlète
            resultSet = preparedStatement.executeQuery();

            // Si un résultat est trouvé, récupérer l'ID de l'athlète
            if (resultSet.next()) {
                athleteId = resultSet.getInt("idAthlete");
            }
        } finally {
            // Fermer les ressources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return athleteId;
    }

    public static String getCountryById(int countryId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String countryName = null;

        try {
            // Obtenir la connexion à la base de données
            connection = Database.getConnection();

            // Préparer la requête SQL pour obtenir le nom du pays par son ID
            String query = "SELECT name FROM countries WHERE idCountry = ?";
            preparedStatement = connection.prepareStatement(query);

            // Remplir les paramètres de la requête avec l'ID du pays
            preparedStatement.setInt(1, countryId);

            // Exécuter la requête pour obtenir le nom du pays
            resultSet = preparedStatement.executeQuery();

            // Si un résultat est trouvé, récupérer le nom du pays
            if (resultSet.next()) {
                countryName = resultSet.getString("name");
            }
        } finally {
            // Fermer les ressources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return countryName;
    }
    public static List<String> getMedals() throws SQLException {
        List<String> medals = new ArrayList<>();
        String query = "SELECT \n" +
                "    c.name AS country_name,\n" +
                "    e.name AS event_name,\n" +
                "    m.rank\n" +
                "FROM \n" +
                "    medals m\n" +
                "JOIN \n" +
                "    countries c ON m.idCountry = c.idCountry\n" +
                "JOIN \n" +
                "    events e ON m.idEvent = e.idEvent;\n";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String countryName = resultSet.getString("country_name");
                String eventName = resultSet.getString("event_name");
                String rank = resultSet.getString("rank");

                String medalInfo = String.format("%s,%s,%s", countryName, eventName, rank);
                medals.add(medalInfo);
            }
        }
        return medals;
    }

    public static void addMedal(String rank, String athlete, String event) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String query = "INSERT INTO medals (rank, idCountry, idEvent) " +
                    "VALUES (?, (SELECT idCountry FROM athletes WHERE name = ?), " +
                    "(SELECT idEvent FROM events WHERE name = ?))";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, rank);
            stmt.setString(2, athlete);
            stmt.setString(3, event);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }



}