package isep.fr.easyolympics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        //showHomeScene();
        showAdminUserList();
    }

    public static void showLoginScene() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Connexion");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    public static void showHomeScene() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Accueil");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    public static void showCalendar() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("calendar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Calendrier");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    public static void showAdminUserList() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("adminUserList.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Liste des Utilisateurs");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
