package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class adminUserList extends Application {

    @FXML
    private ListView<String> userList;

    @FXML
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;

    @FXML
    private Button addAthlete;


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(adminUserList.class.getResource("adminUserList.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Liste des Athletes");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    @FXML
    private void handleButtonAddAthlete() {
        try {
            Main.showAddAthlete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        try {
            List<String> athletes = DatabaseQueries.getAthletes();
            userList.getItems().addAll(athletes);
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez également afficher un message d'erreur à l'utilisateur ici
        }

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
                Main.showCalendar();
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

    public static void main(String[] args) {
        launch(args);
    }
}
