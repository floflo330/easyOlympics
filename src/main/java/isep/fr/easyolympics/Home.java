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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private ContextMenu contextMenu;
    private FilteredList<String> filteredData;
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
        initializeContextMenu();
        loadAthletesFromDatabase();
        configureMenuButton();
        configureSearchField();
        try {
            List<String> sports = DatabaseQueries.getCountries();
            ObservableList<String> sportsList = FXCollections.observableArrayList(sports);
            userList1.setItems(sportsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private void initializeContextMenu() {
        contextMenu = new ContextMenu();

        MenuItem homeItem = createMenuItem("Accueil", () -> {
            try {
                Main.showHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem profileItem = createMenuItem("Resultats par Pays", () -> {
            try {
                Main.showCountryResults();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem calendarItem = createMenuItem("Calendrier", () -> {
            try {
                Main.showCalendar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem eventsItem = createMenuItem("Administration", () -> {
            try {
                Main.showAdminHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenu.getItems().addAll(homeItem, profileItem, calendarItem, eventsItem);
    }

    private MenuItem createMenuItem(String text, Runnable action) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.setOnAction(event -> action.run());
        return menuItem;
    }

    private void loadAthletesFromDatabase() {
        try {
            List<String> athletes = DatabaseQueries.getSports();
            // Trier les disciplines dans l'ordre alphabétique
            Collections.sort(athletes);
            filteredData = new FilteredList<>(FXCollections.observableArrayList(athletes), p -> true);
            userList.setItems(filteredData);
            userList.setCellFactory(listView -> new SportListCell());  // Utilise la cellule personnalisée
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally, display an error message to the user
        }
    }

    private void configureMenuButton() {
        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void configureSearchField() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sport -> {
                // Si le champ de recherche est vide, afficher tous les sports
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Comparer le nom du sport avec le texte de la recherche
                String lowerCaseFilter = newValue.toLowerCase();
                return sport.toLowerCase().contains(lowerCaseFilter);
            });
        });
    }
}
