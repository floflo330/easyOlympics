package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import isep.fr.easyolympics.model.DatabaseQueries;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class adminUserList extends Application {

    @FXML
    private ListView<String> userList;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(adminUserList.class.getResource("adminUserList.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Liste des Athletes");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        try {
            List<String> athletes = DatabaseQueries.getAthletes();
            userList.getItems().addAll(athletes);
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez également afficher un message d'erreur à l'utilisateur ici
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
