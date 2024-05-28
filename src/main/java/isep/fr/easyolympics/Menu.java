package isep.fr.easyolympics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class Menu {


    @FXML
    private static Button menuButton;

    private static ContextMenu contextMenu;

    public static void setupMenuAdmin(Button menuButton) {
        ContextMenu contextMenu = new ContextMenu();

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

        MenuItem resultsItem = new MenuItem("Gestion des resultats");
        resultsItem.setOnAction(event -> {
            try {
                Main.showAdminResults();
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

        contextMenu.getItems().addAll(homeItem, profileItem, eventsItem, sportsItem, countriesItem, resultsItem, exitItem);

        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }

    public static void setupMenu(Button menuButton) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem homeItem = new MenuItem("Accueil");
        homeItem.setOnAction(event -> {
            try {
                Main.showHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem profileItem = new MenuItem("Résultats par Pays");
        profileItem.setOnAction(event -> {
            try {
                Main.showCountryResults();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem calendarItem = new MenuItem("Calendrier");
        calendarItem.setOnAction(event -> {
            try {
                Main.showCalendar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem eventsItem = new MenuItem("Administration");
        eventsItem.setOnAction(event -> {
            try {
                Main.showAdminHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenu.getItems().addAll(homeItem, profileItem, calendarItem, eventsItem);

        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }
}
