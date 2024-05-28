package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import isep.fr.easyolympics.model.Event;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CalendarNew {

    @FXML
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;

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
    private DatePicker datePicker;

    @FXML
    public void initialize() {
        Menu.setupMenu(menuButton);

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        sportColumn.setCellValueFactory(new PropertyValueFactory<>("sportName"));

        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    loadEventsForDate(newValue);
                }
            }
        });
    }

    private void loadEventsForDate(LocalDate date) {
        try {
            List<Event> events = DatabaseQueries.getEventsForDay(date.toString());
            ObservableList<Event> eventList = FXCollections.observableArrayList(events);
            eventsTable.setItems(eventList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Afficher un message d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de chargement");
            alert.setHeaderText("Impossible de charger les événements");
            alert.setContentText("Une erreur est survenue lors du chargement des événements pour la date sélectionnée.");
            alert.showAndWait();
        }
    }
}
