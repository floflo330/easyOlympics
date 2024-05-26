package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
        initializeContextMenu();
        loadAthletesFromDatabase();
        configureMenuButton();
    }

    private void initializeContextMenu() {
        contextMenu = new ContextMenu();

        MenuItem homeItem = createMenuItem("Accueil", () -> {
            try {
                Main.showHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem profileItem = createMenuItem("Gestion des Athletes", () -> {
            try {
                Main.showAdminUserList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MenuItem eventsItem = createMenuItem("Gestion des évènements", () -> {
            // Action pour gérer les évènements
            // Par exemple : Main.showEventManagement();
        });

        MenuItem calendarItem = createMenuItem("Calendrier", () -> {
            try {
                Main.showCalendar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenu.getItems().addAll(homeItem, profileItem, eventsItem, calendarItem);
    }

    private MenuItem createMenuItem(String text, Runnable action) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.setOnAction(event -> action.run());
        return menuItem;
    }

    private void loadAthletesFromDatabase() {
        try {
            List<String> athletes = DatabaseQueries.getSports();
            userList.getItems().addAll(athletes);
            userList.setCellFactory(listView -> new SportListCell());  // Use custom cell
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally, display an error message to the user
        }
    }

    private void configureMenuButton() {
        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }
}
