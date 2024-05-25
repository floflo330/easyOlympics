package isep.fr.easyolympics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class Calendar {

    @FXML
    private Button myButton;

    // Méthode appelée lorsque le bouton est cliqué
    @FXML
    private void handleButtonClick(ActionEvent event) {
        System.out.println("Bouton cliqué !");
        // Ajoutez votre logique ici
    }

    // Vous pouvez ajouter d'autres méthodes et champs ici selon vos besoins
}
