package isep.fr.easyolympics;

import isep.fr.easyolympics.model.DatabaseQueries;
import isep.fr.easyolympics.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
public class Signup extends Application {

    public Text labelNomPage;
    public TextField emailField;
    public PasswordField passwordField;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("EasyOlympics - Incription");

        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("img/logojo.png")));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            User user = DatabaseQueries.getUserByEmailAndPassword(email, password);
            if (user != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connexion réussie");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenue, " + user.getUsername() + "!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Email ou mot de passe incorrect!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible de vérifier les identifiants");
            alert.showAndWait();
        }
    }
}
