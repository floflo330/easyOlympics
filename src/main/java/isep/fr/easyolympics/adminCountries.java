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


public class adminCountries extends Application {

    @FXML
    private Button deleteCountry;

    @FXML
    private Button addCountry;

    @FXML
    private TextField countryField;

    @FXML
    private TableView<String> countryTable;

    @FXML
    private TableColumn<String, String> countryColumn;

    @FXML
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;

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
    public void initialize() {
        countryColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue()));

        try {
            List<String> countries = DatabaseQueries.getCountries();
            countryTable.getItems().addAll(countries);
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez également afficher un message d'erreur à l'utilisateur ici
        }

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

    @FXML
    private void handleAddCountry() {
        String countryName = countryField.getText();
        if (countryName != null && !countryName.trim().isEmpty()) {
            try {
                DatabaseQueries.addSport(countryName);
                countryTable.getItems().add(countryName);
                countryField.clear();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle error (e.g., show an error message to the user)
            }
        }
    }
    @FXML
    private void handleDeleteCountry() {
        String selectedCountry = countryTable.getSelectionModel().getSelectedItem();
        if (selectedCountry != null) {
            try {
                DatabaseQueries.deleteCountry(selectedCountry);
                countryTable.getItems().remove(selectedCountry);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle error (e.g., show an error message to the user)
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static class Day {
    }
}
