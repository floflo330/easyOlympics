package isep.fr.easyolympics;

import isep.fr.easyolympics.model.Event;
import isep.fr.easyolympics.model.DatabaseQueries;
import isep.fr.easyolympics.model.Athlete; // Importez votre classe Athlete
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class adminEventsDetail {
    @FXML
    private TableView<Athlete> athletesTable;

    @FXML
    private TableColumn<Athlete, String> nameColumn;

    @FXML
    private TableColumn<Athlete, String> surnameColumn;

    @FXML
    private TableColumn<Athlete, String> countryColumn;

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
    private Button backButton;

    @FXML
    private ChoiceBox<String> athleteChoiceBox;

    @FXML
    private Button addAthleteButton;

    @FXML
    private Button menuButton;

    private Event event;



    public void initialize() {
        // Initialisation des Spinners
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }

    public void setEvent(Event event) {
        this.event = event;
        loadEventDetails();
    }

    private void loadEventDetails() {
        nameField.setText(event.getName());
        datePicker.setValue(LocalDate.parse(event.getDate()));
        String[] timeParts = event.getTime().split(":");
        hourSpinner.getValueFactory().setValue(Integer.parseInt(timeParts[0]));
        minuteSpinner.getValueFactory().setValue(Integer.parseInt(timeParts[1]));
        placeField.setText(event.getPlace());
        sportChoiceBox.setValue(event.getSportName());

        if (hourSpinner.getValueFactory() == null) {
            hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        }
        if (minuteSpinner.getValueFactory() == null) {
            minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        }

        loadAthletes();
    }

    private void loadAthletes() {
        try {
            List<Athlete> athletes = DatabaseQueries.getAthletesByEvent(event.getIdEvent());
            ObservableList<Athlete> athleteList = FXCollections.observableArrayList(athletes);
            athletesTable.setItems(athleteList);

            // Initialiser les colonnes du tableau des athlètes
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

            // Utiliser une cell factory pour la colonne "Pays" afin d'afficher le nom du pays au lieu de l'ID
            countryColumn.setCellValueFactory(cellData -> {
                Athlete athlete = cellData.getValue();
                String countryName = null;
                try {
                    // Obtenir le nom du pays à partir de l'ID du pays de l'athlète
                    countryName = DatabaseQueries.getCountryById(Integer.parseInt(athlete.getCountry()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return new SimpleStringProperty(countryName);
            });

            // Charger les athlètes liés au sport de l'événement dans le ChoiceBox
            loadAthletesBySport(event.getSportName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadAthletesBySport(String sportName) {
        try {
            List<String> athletes = DatabaseQueries.getAthletesBySport(sportName);
            athleteChoiceBox.getItems().clear();
            athleteChoiceBox.getItems().addAll(athletes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton() {
        try {
            // Récupérer le nom de l'athlète sélectionné dans le choix box
            String selectedAthleteName = athleteChoiceBox.getValue();

            // Récupérer l'ID de l'athlète à partir de son nom
            int athleteId = DatabaseQueries.getAthleteIdByName(selectedAthleteName);

            // Ajouter l'athlète à l'événement dans la base de données
            DatabaseQueries.addAthleteToEvent(athleteId, event.getIdEvent());

            // Recharger les détails de l'événement pour afficher les nouveaux athlètes ajoutés
            loadEventDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackButton(){
        try {
            Main.showAdminEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
