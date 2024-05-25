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

public class Home implements Initializable {

    @FXML
    private Button menuButton;

    private ContextMenu contextMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contextMenu = new ContextMenu();

        MenuItem homeItem = new MenuItem("Accueil");
        homeItem.setOnAction(event -> {
            // Action pour aller à la page d'accueil
            try {
                Main.showHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem profileItem = new MenuItem("Profil");
        profileItem.setOnAction(event -> {
            // Action pour aller à la page de profil
            // Par exemple : Main.showProfileScene();
        });

        MenuItem logoutItem = new MenuItem("Déconnexion");
        logoutItem.setOnAction(event -> {
            // Action pour se déconnecter
            // Par exemple : Main.showLoginScene();
        });

        MenuItem calendarItem = new MenuItem("Calendrier");
        calendarItem.setOnAction(event -> {
            // Action pour se déconnecter
            // Par exemple : Main.showLoginScene();
        });

        contextMenu.getItems().addAll(homeItem, calendarItem, profileItem, logoutItem);

        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }
}
