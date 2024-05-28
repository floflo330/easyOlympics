package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Athletes implements Initializable {

    @FXML
    private Button downloadButton;

    @FXML
    private Button menuButton;

    @FXML
    private ListView<String> medalList;

    @FXML
    private TextField searchField;

    private ContextMenu contextMenu;
    private FilteredList<String> filteredData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Menu.setupMenu(menuButton);
        loadMedalsFromDatabase();
        configureSearchField();
        configureDownloadButton();
    }

    private MenuItem createMenuItem(String text, Runnable action) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.setOnAction(event -> action.run());
        return menuItem;
    }

    private void loadMedalsFromDatabase() {
        try {
            List<String> medals = DatabaseQueries.getMedals();
            Collections.sort(medals);
            filteredData = new FilteredList<>(FXCollections.observableArrayList(medals), p -> true);
            medalList.setItems(filteredData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void configureMenuButton() {
        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(menuButton, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void configureSearchField() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medal -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return medal.toLowerCase().contains(lowerCaseFilter);
            });
        });
    }

    private void configureDownloadButton() {
        downloadButton.setOnAction(event -> {
            try {
                generateAndDownloadCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void generateAndDownloadCSV() throws IOException {
        List<String> medals = filteredData;

        try (FileWriter csvWriter = new FileWriter("medals_list.csv")) {
            csvWriter.append("Country,Athlete,Medal\n");

            for (String medal : medals) {
                csvWriter.append(medal).append("\n");
            }

            System.out.println("Fichier CSV généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la génération du fichier CSV : " + e.getMessage());
        }
    }
}
