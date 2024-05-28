package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;



import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddAthlete implements Initializable  {

    @FXML
    private TextField emailField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private ChoiceBox<String> countryChoiceBox;
    @FXML
    private ChoiceBox<String> sportChoiceBox;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;

    @FXML
    private Button menuButton;
    @FXML
    private ContextMenu contextMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(genderGroup);
        femaleRadioButton.setToggleGroup(genderGroup);

        try {
            List<String> countries = DatabaseQueries.getCountries();
            List<String> sports = DatabaseQueries.getSports();

            countryChoiceBox.getItems().addAll(countries);
            sportChoiceBox.getItems().addAll(sports);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error (e.g., show an error message to the user)
        }

        Menu.setupMenuAdmin(menuButton);

    }

    @FXML
    private void handleAddAthlete(ActionEvent event) {
        String email = emailField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        LocalDate birthDate = birthDatePicker.getValue();
        String country = countryChoiceBox.getValue();
        String sport = sportChoiceBox.getValue();
        char sex = maleRadioButton.isSelected() ? 'M' : 'F';

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || birthDate == null || country == null || sport == null) {
            return;
        }

        try {
            int idCountry = DatabaseQueries.getCountryIdByName(country);
            int idSport = DatabaseQueries.getSportIdByName(sport);

            DatabaseQueries.addAthlete(firstName, lastName, email, idCountry, birthDate, sex, idSport);
            //System.out.println(birthDate);
            // Clear the fields after successful addition
            emailField.clear();
            lastNameField.clear();
            firstNameField.clear();
            birthDatePicker.setValue(null);
            countryChoiceBox.setValue(null);
            sportChoiceBox.setValue(null);
            maleRadioButton.setSelected(false);
            femaleRadioButton.setSelected(false);
            try {
                Main.showAdminUserList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error (e.g., show an error message to the user)
        }
    }

    @FXML
    private void handleBackButton(){
        try {
            Main.showAdminUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
