package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class adminSports extends Application {

    @FXML
    private Button deleteSport;

    @FXML
    private Button addSport;

    @FXML
    private TextField sportField;

    @FXML
    private TableView<String> sportsTable;

    @FXML
    private TableColumn<String, String> sportColumn;

    @FXML
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(adminUserList.class.getResource("adminUserList.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Liste des Athletes");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        sportColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue()));

        try {
            List<String> sports = DatabaseQueries.getSports();
            sportsTable.getItems().addAll(sports);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Menu.setupMenuAdmin(menuButton);


    }

    @FXML
    private void handleAddSport() {
        String sportName = sportField.getText();
        if (sportName != null && !sportName.trim().isEmpty()) {
            try {
                DatabaseQueries.addSport(sportName);
                sportsTable.getItems().add(sportName);
                sportField.clear();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle error (e.g., show an error message to the user)
            }
        }
    }
    @FXML
    private void handleDeleteSport() {
        String selectedSport = sportsTable.getSelectionModel().getSelectedItem();
        if (selectedSport != null) {
            try {
                DatabaseQueries.deleteSport(selectedSport);
                sportsTable.getItems().remove(selectedSport);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle error (e.g., show an error message to the user)
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
