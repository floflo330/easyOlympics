package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private Button menuButton;

    @FXML
    private ListView<String> userList;

    private ContextMenu contextMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Création du menu contextuel
        contextMenu = new ContextMenu();

        // Création des éléments du menu
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

        MenuItem eventsItem = new MenuItem("Gestion des évènements");
        eventsItem.setOnAction(event -> {
            // Action pour gérer les évènements
            // Par exemple : Main.showEventManagement();
        });

        MenuItem calendarItem = new MenuItem("Calendrier");
        calendarItem.setOnAction(event -> {
            try {
                Main.showCalendar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Ajout des éléments au menu
        contextMenu.getItems().addAll(homeItem, profileItem, eventsItem, calendarItem);

        try {
            // Récupération des athlètes depuis la base de données
            List<String> athletes = DatabaseQueries.getSports();
            // Ajout des athlètes à la liste d'utilisateurs
            userList.getItems().addAll(athletes);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion de l'erreur de récupération des données depuis la base de données
            // Vous pouvez afficher un message à l'utilisateur pour l'informer de l'erreur
        }

        // Gestion de l'événement de clic sur le bouton de menu
        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }
}
