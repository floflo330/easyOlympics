package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.sql.SQLException;
import java.util.List;

public class Calendar {

    @FXML
    private Button button1;
    @FXML
    private Button button100;
    @FXML
    private Button button12;
    @FXML
    private Button button2;
    // Ajoutez les autres boutons ici...

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
