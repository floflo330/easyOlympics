package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

    public Text labelNomPage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("EasyOlympics - Connexion");

        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("img/logojo.png")));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
