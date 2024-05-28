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
    @FXML
    private Button athletesButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button sportsButton;
    @FXML
    private Button countriesButton;
    @FXML
    private Button resultsButton;
    @FXML
    private Button exitButton;

    @FXML
    private void handleAthletesButton(){
        try {
            Main.showAdminUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleEventButton(){
        try {
            Main.showAdminEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSportsButton(){
        try {
            Main.showAdminSports();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleCountriesButton(){
        try {
            Main.showAdminCountries();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleResultsButton(){
        try {
            Main.showAdminResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleExitButton(){
        try {
            Main.showHomeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Menu.setupMenuAdmin(menuButton);

    }


}
