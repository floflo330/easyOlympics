package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calendar extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Charger le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("calendar.fxml"));

        // Créer une scène
        Scene scene = new Scene(root, 1200, 800);

        // Configurer la scène et afficher la fenêtre
        primaryStage.setTitle("Mon Calendrier");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
