package isep.fr.easyolympics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;

import java.net.URL;
import java.util.ResourceBundle;


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
