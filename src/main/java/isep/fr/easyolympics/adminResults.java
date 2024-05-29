package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class adminResults implements Initializable {

    @FXML
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private ChoiceBox<String> eventChoiceBox;
    @FXML
    private ChoiceBox<String> firstChoiceBox;
    @FXML
    private TextField firstTimeField;
    @FXML
    private TextField firstScoreField;
    @FXML
    private ChoiceBox<String> secondChoiceBox;
    @FXML
    private TextField secondTimeField;
    @FXML
    private TextField secondScoreField;
    @FXML
    private ChoiceBox<String> thirdChoiceBox;
    @FXML
    private TextField thirdTimeField;
    @FXML
    private TextField thirdScoreField;
    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadEvents();
        setupEventChoiceBoxListener();
        setupChoiceBoxesListeners();

        Menu.setupMenuAdmin(menuButton);

    }
    private void loadEvents() {
        try {
            List<String> events = DatabaseQueries.getEventNames();
            eventChoiceBox.setItems(FXCollections.observableArrayList(events));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupEventChoiceBoxListener() {
        eventChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadAthletesForEvent(newValue);
            }
        });
    }
    private void loadAthletesForEvent(String eventName) {
        try {
            List<String> athletes = DatabaseQueries.getAthletesForEvent(eventName);
            ObservableList<String> athleteOptions = FXCollections.observableArrayList(athletes);

            firstChoiceBox.setItems(athleteOptions);
            secondChoiceBox.setItems(athleteOptions);
            thirdChoiceBox.setItems(athleteOptions);

            // Clear previous selections
            firstChoiceBox.getSelectionModel().clearSelection();
            secondChoiceBox.getSelectionModel().clearSelection();
            thirdChoiceBox.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupChoiceBoxesListeners() {
        // Ensure unique selections across all choice boxes
        firstChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(secondChoiceBox.getValue())) {
                    secondChoiceBox.getSelectionModel().clearSelection();
                }
                if (newValue.equals(thirdChoiceBox.getValue())) {
                    thirdChoiceBox.getSelectionModel().clearSelection();
                }
            }
        });

        secondChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(firstChoiceBox.getValue())) {
                    firstChoiceBox.getSelectionModel().clearSelection();
                }
                if (newValue.equals(thirdChoiceBox.getValue())) {
                    thirdChoiceBox.getSelectionModel().clearSelection();
                }
            }
        });

        thirdChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(firstChoiceBox.getValue())) {
                    firstChoiceBox.getSelectionModel().clearSelection();
                }
                if (newValue.equals(secondChoiceBox.getValue())) {
                    secondChoiceBox.getSelectionModel().clearSelection();
                }
            }
        });
    }

    @FXML
    private void handleSaveButton() {
        String selectedEvent = eventChoiceBox.getValue();
        String firstAthlete = firstChoiceBox.getValue();
        String secondAthlete = secondChoiceBox.getValue();
        String thirdAthlete = thirdChoiceBox.getValue();

        String firstTime = firstTimeField.getText();
        String secondTime = secondTimeField.getText();
        String thirdTime = thirdTimeField.getText();

        String firstScore = firstScoreField.getText();
        String secondScore = secondScoreField.getText();
        String thirdScore = thirdScoreField.getText();

        if (selectedEvent == null || firstAthlete == null || secondAthlete == null) {
            showAlert("Erreur", "Veuillez sélectionner un événement et au moins deux athlètes.");
            return;
        }

        try {
            DatabaseQueries.saveResult(firstAthlete, selectedEvent, firstScore, firstTime);
            DatabaseQueries.saveResult(secondAthlete, selectedEvent, secondScore, secondTime);
            if (thirdAthlete != null) {
                DatabaseQueries.saveResult(thirdAthlete, selectedEvent, thirdScore, thirdTime);
            }

            DatabaseQueries.addMedal("Gold", firstAthlete, selectedEvent);
            DatabaseQueries.addMedal("Silver", secondAthlete, selectedEvent);
            if (thirdAthlete != null) {
                DatabaseQueries.addMedal("Bronze", thirdAthlete, selectedEvent);
            }

            showAlert("Succès", "Les résultats et les médailles ont été sauvegardés avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur s'est produite lors de la sauvegarde des résultats et des médailles.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


