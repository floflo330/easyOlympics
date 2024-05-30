package isep.fr.easyolympics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SportMatchesController implements Initializable {

    @FXML
    private Label sportLabel;

    private String selectedSport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(String sport) {
        selectedSport = sport;
        sportLabel.setText("Matches for " + selectedSport);
    }
}
