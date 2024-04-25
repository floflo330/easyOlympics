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

    @FXML

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(adminUserList.class.getResource("adminUserList.fxml"));

        initialize();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Administration");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
//        String userList = TestDatabaseConnection.getUserListAsString();
        String userList = "Oui";

        Text text = new Text(userList);

        userListTextArea.getChildren().add(text);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
