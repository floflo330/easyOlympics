package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Mettre la scène dans la fenêtre principale (primaryStage)
        primaryStage.setScene(scene);

        // Définir le titre de la fenêtre
        primaryStage.setTitle("EasyOlympics - Connexion");

        // Afficher la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
