package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import isep.fr.easyolympics.model.TestDatabaseConnection;

import java.io.IOException;
public class adminUserList extends Application {

    public TextFlow userListTextArea;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(adminUserList.class.getResource("adminUserList.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Administration");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/logojo.png")));
        primaryStage.show();

        initialize();
    }

    public void initialize() {
        // Assurez-vous que userListTextArea n'est pas null avant d'ajouter du texte
        if (userListTextArea != null) {
            // Obtenir la liste des utilisateurs depuis la base de données
            String userList = "Oui";

            // Créer un nouveau Text pour afficher la liste des utilisateurs
            Text text = new Text(userList);

            // Ajouter le texte au TextFlow
            userListTextArea.getChildren().add(text);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
