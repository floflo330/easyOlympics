package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ListView;

public class adminHome implements Initializable {

    @FXML
    private Button menuButton;
    @FXML
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

        MenuItem profileItem = new MenuItem("Gestion des Athletes");
        profileItem.setOnAction(event -> {
            try {
                Main.showAdminUserList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem logoutItem = new MenuItem("Gestion des évènements");
        logoutItem.setOnAction(event -> {
            // Action pour se déconnecter
            // Par exemple : Main.showLoginScene();
        });

        MenuItem sportsItem = new MenuItem("Gestion des disciplines");
        sportsItem.setOnAction(event -> {
            try {
                Main.showAdminSports();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenu.getItems().addAll(homeItem, profileItem, logoutItem, sportsItem);


        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }


}