package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.sql.SQLException;
import java.util.Map;

public class CountryResults extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public BarChart<String, Number> barChart;

    @FXML
    public TableView<String> tableView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charge le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("countryResults.fxml"));
        loader.setController(this); // Définit le contrôleur pour ce fichier FXML
        primaryStage.setTitle("Country Medals Results");
        primaryStage.setScene(new Scene(loader.load(), 1200, 800));
        primaryStage.show();

        // Appelle la méthode initialize pour afficher les données
        initialize();
    }

    @FXML
    public void initialize() {
        try {
            // Obtenir les données des médailles depuis la base de données
            Map<String, Map<String, Integer>> medalsByCountry = DatabaseQueries.getMedalsByCountries();

            // Ajouter les données des médailles à la TableView
            tableView.getItems().clear();
            for (String country : medalsByCountry.keySet()) {
                Map<String, Integer> medals = medalsByCountry.get(country);
                tableView.getItems().add(country + " - Gold: " + medals.getOrDefault("gold", 0) +
                        ", Silver: " + medals.getOrDefault("silver", 0) +
                        ", Bronze: " + medals.getOrDefault("bronze", 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
