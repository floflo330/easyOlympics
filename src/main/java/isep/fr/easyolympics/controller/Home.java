package isep.fr.easyolympics.controller;

import isep.fr.easyolympics.controller.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Home extends Application {

    public Text labelNomPage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("EasyOlympics - Accueil");

        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("img/logojo.png")));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
