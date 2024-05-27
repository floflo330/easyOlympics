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

    @FXML private Button btnJuly30;
    @FXML private Button btnJuly27;
    @FXML private Button btnJuly26;
    @FXML private Button btnJuly29;
    @FXML private Button btnJuly31;
    @FXML private Button btnJuly28;
    @FXML private Button btnAugust6;
    @FXML private Button btnAugust2;
    @FXML private Button btnAugust5;
    @FXML private Button btnAugust4;
    @FXML private Button btnAugust3;
    @FXML private Button btnAugust11;
    @FXML private Button btnAugust7;
    @FXML private Button btnAugust10;
    @FXML private Button btnAugust9;
    @FXML private Button btnAugust8;
    @FXML private Button btnAugust1;

    @FXML private Button button6;
    @FXML private Button button12;
    @FXML private Button button13;
    @FXML private Button button14;
    @FXML private Button button15;
    @FXML private Button button16;
    @FXML private Button button17;
    @FXML private Button button18;
    @FXML private Button button19;
    @FXML private Button button20;
    @FXML private Button button21;
    @FXML private Button button22;
    @FXML private Button button23;
    @FXML private Button button24;
    @FXML private Button button25;
    @FXML private Button button26;
    @FXML private Button button27;
    @FXML private Button button28;
    @FXML private Button button29;
    @FXML private Button button31;
    @FXML private Button button32;

    @FXML
    private TextArea eventTextArea;

    @FXML
    public void initialize() {
        // Assurez-vous que la zone de texte est initialisée
        eventTextArea = new TextArea();



    }




    @FXML
    void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        int day = Integer.parseInt(buttonText);

        try {
            List<String> events = DatabaseQueries.getEventsForDay(day);
            displayEvents(events);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // Load the Day.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Day.fxml"));
            Parent root = loader.load();

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

    private void displayEvents(List<String> events) {
        eventTextArea.clear();
        if (events.isEmpty()) {
            eventTextArea.setText("Aucun événement pour ce jour.");
        } else {
            for (String event : events) {
                eventTextArea.appendText(event + "\n");
            }
        }
    }
}
