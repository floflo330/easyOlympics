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
            Notification.showAlert("Erreur", "Erreur !");
        }

        Menu.setupMenuAdmin(menuButton);
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
                Notification.showAlert("Erreur", "Erreur !");
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
                Notification.showAlert("Succès", "Pays supprimé avec succès !");

            } catch (SQLException e) {
                e.printStackTrace();
                Notification.showAlert("Erreur", "Erreur !");
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static class Day {
    }
}
