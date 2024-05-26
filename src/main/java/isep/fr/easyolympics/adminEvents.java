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
import javafx.fxml.Initializable;

public class adminEvents extends Application{

    @FXML
    private Button menuButton;

    @FXML
    private ContextMenu contextMenu;

    @Override
    public void start(Stage stage) throws Exception {


    }

    @FXML
    public void initialize() {

        contextMenu = new ContextMenu();

        MenuItem homeItem = new MenuItem("Accueil Administration");
        homeItem.setOnAction(event -> {
            try {
                Main.showAdminHome();
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
            try {
                Main.showAdminEvents();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem sportsItem = new MenuItem("Gestion des disciplines");
        sportsItem.setOnAction(event -> {
            try {
                Main.showAdminSports();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem countriesItem = new MenuItem("Gestion des pays");
        countriesItem.setOnAction(event -> {
            try {
                Main.showAdminCountries();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem exitItem = new MenuItem("Quitter l'administration");
        exitItem.setOnAction(event -> {
            try {
                Main.showHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenu.getItems().addAll(homeItem, profileItem, eventsItem, sportsItem, countriesItem, exitItem);


        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });


    }
}
