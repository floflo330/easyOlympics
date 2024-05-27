package isep.fr.easyolympics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    private Button menuButton;

    private ContextMenu contextMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contextMenu = new ContextMenu();

        MenuItem homeItem = new MenuItem("Accueil");
        homeItem.setOnAction(event -> {
            try {
                Main.showHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem profileItem = new MenuItem("Resultats par Pays");
        profileItem.setOnAction(event -> {
            try {
                Main.showCountryResults();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem logoutItem = new MenuItem("Calendrier");
        logoutItem.setOnAction(event -> {
            try {
                Main.showCalendar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem adminItem = new MenuItem("Administration");
        logoutItem.setOnAction(event -> {
            try {
                Main.showAdminHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenu.getItems().addAll(homeItem, profileItem, logoutItem, adminItem);

        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }
}
