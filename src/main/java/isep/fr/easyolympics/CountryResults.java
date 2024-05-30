package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Map;

public class CountryResults extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @FXML
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    public BarChart<String, Number> barChart;

    @FXML
    public TableView<CountryMedals> tableView;

    @FXML
    private TableColumn<CountryMedals, String> countryColumn;

    @FXML
    private TableColumn<CountryMedals, Integer> goldColumn;

    @FXML
    private TableColumn<CountryMedals, Integer> silverColumn;

    @FXML
    private TableColumn<CountryMedals, Integer> bronzeColumn;

    @FXML
    private TableColumn<CountryMedals, Integer> totalColumn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("countryResults.fxml"));
        loader.setController(this); // Set the controller for this FXML file
        primaryStage.setTitle("Country Medals Results");
        primaryStage.setScene(new Scene(loader.load(), 1200, 800));
        primaryStage.show();

        // Call the initialize method to display the data
        initialize();
    }

    @FXML
    public void initialize() {
        Menu.setupMenu(menuButton);
        try {
            countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
            goldColumn.setCellValueFactory(new PropertyValueFactory<>("gold"));
            silverColumn.setCellValueFactory(new PropertyValueFactory<>("silver"));
            bronzeColumn.setCellValueFactory(new PropertyValueFactory<>("bronze"));
            totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

            // Retrieve medal data from the database
            Map<String, Map<String, Integer>> medalsByCountry = DatabaseQueries.getMedalsByCountries();

            // Add medal data to the TableView
            ObservableList<CountryMedals> countryMedalsList = FXCollections.observableArrayList();
            for (String country : medalsByCountry.keySet()) {
                Map<String, Integer> medals = medalsByCountry.get(country);
                int gold = medals.getOrDefault("gold", 0);
                int silver = medals.getOrDefault("silver", 0);
                int bronze = medals.getOrDefault("bronze", 0);
                int total = gold + silver + bronze;
                countryMedalsList.add(new CountryMedals(country, gold, silver, bronze, total));

                // Debug log
                System.out.println("Country: " + country + ", Gold: " + gold + ", Silver: " + silver + ", Bronze: " + bronze + ", Total: " + total);
            }
            tableView.setItems(countryMedalsList);

            // Populate the BarChart
            XYChart.Series<String, Number> seriesGold = new XYChart.Series<>();
            seriesGold.setName("Gold");
            XYChart.Series<String, Number> seriesSilver = new XYChart.Series<>();
            seriesSilver.setName("Silver");
            XYChart.Series<String, Number> seriesBronze = new XYChart.Series<>();
            seriesBronze.setName("Bronze");
            
            for (CountryMedals cm : countryMedalsList) {
                seriesGold.getData().add(new XYChart.Data<>(cm.getCountry(), cm.getGold()));
                seriesSilver.getData().add(new XYChart.Data<>(cm.getCountry(), cm.getSilver()));
                seriesBronze.getData().add(new XYChart.Data<>(cm.getCountry(), cm.getBronze()));
            }

            barChart.getData().addAll(seriesGold, seriesSilver, seriesBronze);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class CountryMedals {
        private String country;
        private int gold;
        private int silver;
        private int bronze;
        private int total;

        public CountryMedals(String country, int gold, int silver, int bronze, int total) {
            this.country = country;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
            this.total = total;
        }

        public String getCountry() {
            return country;
        }

        public int getGold() {
            return gold;
        }

        public int getSilver() {
            return silver;
        }

        public int getBronze() {
            return bronze;
        }

        public int getTotal() {
            return total;
        }
    }
}
