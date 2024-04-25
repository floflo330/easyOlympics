package isep.fr.easyolympics.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import isep.fr.easyolympics.model.TestDatabaseConnection;

public class adminUserListController {

    @FXML
    private TextArea userListTextArea;

    public void displayUserList() {
        // Appeler la méthode de TestDatabaseConnection pour récupérer les données des utilisateurs
        String userList = TestDatabaseConnection.getUserListAsString();

        // Afficher les données dans le TextArea
        userListTextArea.setText(userList);
    }
}
