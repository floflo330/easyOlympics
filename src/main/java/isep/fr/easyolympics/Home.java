package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import isep.fr.easyolympics.model.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private Button menuButton;
    @FXML
    private ListView<String> userList;
    @FXML
    private ListView<String> userList1;
    @FXML
    private TextField searchField;
    @FXML
    private TextField searchField1;
    private FilteredList<String> filteredData;
    private FilteredList<String> filteredData1;
    @FXML
    private TableView<Event> eventsTable;
    @FXML
    private TableColumn<Event, String> dateColumn;
    @FXML
    private TableColumn<Event, String> timeColumn;
    @FXML
    private TableColumn<Event, String> nameColumn;
    @FXML
    private TableColumn<Event, String> sportColumn;
    @FXML
    private TableColumn<Event, String> placeColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Menu.setupMenu(menuButton);
        loadAthletesFromDatabase();
        loadCountriesFromDatabase();
        configureSearchFields();

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        sportColumn.setCellValueFactory(new PropertyValueFactory<>("sportName"));
        try {
            List<Event> events = DatabaseQueries.getEvents();
            ObservableList<Event> eventList = FXCollections.observableArrayList(events);
            eventsTable.setItems(eventList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAthletesFromDatabase() {
        try {
            List<String> athletes = DatabaseQueries.getSports();
            Collections.sort(athletes);
            filteredData = new FilteredList<>(FXCollections.observableArrayList(athletes), p -> true);
            userList.setItems(filteredData);
            userList.setCellFactory(listView -> new SportListCell());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCountriesFromDatabase() {
        try {
            List<String> countries = DatabaseQueries.getCountries();
            Collections.sort(countries);
            filteredData1 = new FilteredList<>(FXCollections.observableArrayList(countries), p -> true);
            userList1.setItems(filteredData1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void configureSearchFields() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sport -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return sport.toLowerCase().contains(lowerCaseFilter);
            });
        });

        searchField1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData1.setPredicate(country -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return country.toLowerCase().contains(lowerCaseFilter);
            });
        });
    }
}
