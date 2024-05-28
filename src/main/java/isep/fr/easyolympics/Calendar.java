package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class Calendar {

    @FXML
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;

    @FXML
    private Button btnJuly30;
    @FXML
    private Button btnJuly27;
    @FXML
    private Button btnJuly26;
    @FXML
    private Button btnJuly29;
    @FXML
    private Button btnJuly31;
    @FXML
    private Button btnJuly28;
    @FXML
    private Button btnAugust6;
    @FXML
    private Button btnAugust2;
    @FXML
    private Button btnAugust5;
    @FXML
    private Button btnAugust4;
    @FXML
    private Button btnAugust3;
    @FXML
    private Button btnAugust11;
    @FXML
    private Button btnAugust7;
    @FXML
    private Button btnAugust10;
    @FXML
    private Button btnAugust9;
    @FXML
    private Button btnAugust8;
    @FXML
    private Button btnAugust1;

    @FXML
    private TextArea eventTextArea;

    @FXML
    public void initialize() {
        Menu.setupMenu(menuButton);
        configureButtonWithDate(btnJuly30, "2024-07-30");
        configureButtonWithDate(btnJuly27, "2024-07-27");
        configureButtonWithDate(btnJuly26, "2024-07-26");
        configureButtonWithDate(btnJuly29, "2024-07-29");
        configureButtonWithDate(btnJuly31, "2024-07-31");
        configureButtonWithDate(btnJuly28, "2024-07-28");
        configureButtonWithDate(btnAugust6, "2024-08-06");
        configureButtonWithDate(btnAugust2, "2024-08-02");
        configureButtonWithDate(btnAugust5, "2024-08-05");
        configureButtonWithDate(btnAugust4, "2024-08-04");
        configureButtonWithDate(btnAugust3, "2024-08-03");
        configureButtonWithDate(btnAugust11, "2024-08-11");
        configureButtonWithDate(btnAugust7, "2024-08-07");
        configureButtonWithDate(btnAugust10, "2024-08-10");
        configureButtonWithDate(btnAugust9, "2024-08-09");
        configureButtonWithDate(btnAugust8, "2024-08-08");
        configureButtonWithDate(btnAugust1, "2024-08-01");
    }

    private void configureButtonWithDate(Button button, String date) {
        button.setOnAction(event -> handleButtonClick(event, date));
    }

    @FXML
    private void handleButtonClick(ActionEvent event, String date) {
        try {
            // Load the Day.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Day.fxml"));
            Parent root = loader.load();

            // Get the controller of Day.fxml
            Day dayController = loader.getController();
            dayController.setDate(date); // Pass the date to the controller

            // Create a new scene with the loaded view
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the scene with Day.fxml
            stage.setScene(scene);
            stage.show();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
