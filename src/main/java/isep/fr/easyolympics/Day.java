package isep.fr.easyolympics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Day {

    // Méthode appelée lorsque l'utilisateur clique sur un bouton ou un jour du calendrier
    @FXML
    private void handleCalendarButtonClick(ActionEvent event) {
        try {
            // Charger la vue Day.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Day.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Mettre à jour la scène avec Day.fxml
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la vue Day.fxml
        }
    }

    @FXML
    private void handleBackButton(){
        try {
            Main.showCalendar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
