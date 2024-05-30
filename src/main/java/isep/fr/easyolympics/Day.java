package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import isep.fr.easyolympics.model.Event;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Day {

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
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;

    private String selectedDate;

    @FXML
    public void initialize() {
        Menu.setupMenu(menuButton);

        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        sportColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSport()));
        placeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlace()));
    }

    public void setDate(String date) {
        this.selectedDate = date;
        loadEventsForDate();
    }

    private void loadEventsForDate() {
        try {
            List<Event> events = DatabaseQueries.getEventsForDay(selectedDate);
            eventsTable.setItems(FXCollections.observableArrayList(events));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCalendarButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Day.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackButton() {
        try {
            Main.showCalendar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
