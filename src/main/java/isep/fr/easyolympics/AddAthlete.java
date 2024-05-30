package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import isep.fr.easyolympics.model.DatabaseQueries;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddAthlete implements Initializable {

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
            emailField.clear();
            lastNameField.clear();
            firstNameField.clear();
            birthDatePicker.setValue(null);
            countryChoiceBox.setValue(null);
            sportChoiceBox.setValue(null);
            maleRadioButton.setSelected(false);
            femaleRadioButton.setSelected(false);
            Notification.showAlert("Succès", "Athlète ajouté avec succès !");

            try {
                Main.showAdminUserList();
            } catch (Exception e) {
                e.printStackTrace();
                Notification.showAlert("Erreur", "Erreur");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notification.showAlert("Erreur", "Erreur");


        }
    }

    @FXML
    private void handleBackButton() {
        try {
            Main.showAdminUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
