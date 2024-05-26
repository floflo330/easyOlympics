package isep.fr.easyolympics;

import isep.fr.easyolympics.model.Event;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.Initializable;

public class adminEvents extends Application{



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

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> hourSpinner;

    @FXML
    private Spinner<Integer> minuteSpinner;

    @FXML
    private TextField placeField;

    @FXML
    private ChoiceBox<String> sportChoiceBox;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Button menuButton;

    @FXML
    private ContextMenu contextMenu;

    @Override
    public void start(Stage stage) throws Exception {


    }

    @FXML
    public void initialize() {

        contextMenu = new ContextMenu();

        MenuItem homeItem = new MenuItem("Accueil Administration");
        homeItem.setOnAction(event -> {
            try {
                Main.showAdminHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem profileItem = new MenuItem("Gestion des Athletes");
        profileItem.setOnAction(event -> {
            try {
                Main.showAdminUserList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem eventsItem = new MenuItem("Gestion des évènements");
        eventsItem.setOnAction(event -> {
            try {
                Main.showAdminEvents();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem sportsItem = new MenuItem("Gestion des disciplines");
        sportsItem.setOnAction(event -> {
            try {
                Main.showAdminSports();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem countriesItem = new MenuItem("Gestion des pays");
        countriesItem.setOnAction(event -> {
            try {
                Main.showAdminCountries();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem exitItem = new MenuItem("Quitter l'administration");
        exitItem.setOnAction(event -> {
            try {
                Main.showHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenu.getItems().addAll(homeItem, profileItem, eventsItem, sportsItem, countriesItem, exitItem);


        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
        // Initialiser les colonnes du tableau
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        sportColumn.setCellValueFactory(new PropertyValueFactory<>("sportName"));

        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        // Charger les événements dans le tableau
        loadEvents();
        loadSports();
        // Ajouter un événement
        addButton.setOnAction(event -> addEvent());

        // Supprimer l'événement sélectionné
        deleteButton.setOnAction(event -> deleteEvent());

    }


    private void loadEvents() {
        try {
            List<Event> events = DatabaseQueries.getEvents();
            ObservableList<Event> eventList = FXCollections.observableArrayList(events);
            eventsTable.setItems(eventList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void loadSports() {
        try {
            List<String> sports = DatabaseQueries.getSports();
            sportChoiceBox.getItems().addAll(sports);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addEvent() {
        String name = nameField.getText();
        String date = datePicker.getValue().toString();
        String time = String.format("%02d:%02d", hourSpinner.getValue(), minuteSpinner.getValue());
        String place = placeField.getText();
        String sportName = sportChoiceBox.getValue();

        try {
            int idSport = DatabaseQueries.getSportIdByName(sportName);

            Event newEvent = new Event(0, name, place, date, time, idSport, sportName);
            DatabaseQueries.addEvent(newEvent);
            loadEvents(); // Recharger les événements
        } catch (SQLException e) {
            e.printStackTrace();
            // Afficher un message d'erreur à l'utilisateur
        }
    }

    private void deleteEvent() {
        Event selectedEvent = eventsTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            try {
                DatabaseQueries.deleteEvent(selectedEvent.getIdEvent());
                loadEvents(); // Recharger les événements
            } catch (SQLException e) {
                e.printStackTrace();
                // Afficher un message d'erreur à l'utilisateur
            }
        }
    }
    @FXML
    private void handleDeleteEvent() {
        Event selectedEvent = eventsTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            try {
                DatabaseQueries.deleteEvent(selectedEvent.getIdEvent());

                // Mettre à jour la table des événements
                List<Event> events = DatabaseQueries.getEvents();
                ObservableList<Event> eventList = FXCollections.observableArrayList(events);
                eventsTable.setItems(eventList);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Avertir l'utilisateur qu'aucun événement n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun événement sélectionné");
            alert.setContentText("Veuillez sélectionner un événement dans la table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddEvent() {
        try {
            int id = 0; // Si votre ID est généré automatiquement, vous pouvez ignorer ceci
            String name = nameField.getText();
            String place = placeField.getText();
            String date = datePicker.getValue().toString();
            String time = hourSpinner.getValue() + ":" + minuteSpinner.getValue();
            String sportName = sportChoiceBox.getValue();
            int idSport = DatabaseQueries.getSportIdByName(sportName);

            Event newEvent = new Event(id, name, place, date, time, idSport, sportName);
            DatabaseQueries.addEvent(newEvent);

            // Mettre à jour la table des événements
            List<Event> events = DatabaseQueries.getEvents();
            ObservableList<Event> eventList = FXCollections.observableArrayList(events);
            eventsTable.setItems(eventList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
