package isep.fr.easyolympics;

import isep.fr.easyolympics.model.Event;
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
        showHomeScene();
        //showAdminUserList();
        //showAdminEvents();
        //showAddAthlete();
        //showAdminSports();
        //showAdminCountries();
        //showAdminHome();

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

    public static void showAdminHome() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("adminHome.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Administration");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    public static void showAdminSports() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("adminSports.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Gestion des disciplines");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    public static void showAddAthlete() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("addAthlete.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Ajouter un utilisateur");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    public static void showAdminCountries() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("adminCountries.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Gestion des Pays");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    public static void showAdminEvents() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("adminEvents.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Gestion des Evènements");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }
    public static void showAdminResults() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("adminResults.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EasyOlympics - Gestion des Resultats");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/logojo.png")));
        primaryStage.show();
    }

    public static void showAdminEventsDetail(Event event) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("adminEventsDetail.fxml"));
        Parent root = loader.load();

        adminEventsDetail controller = loader.getController();
        controller.setEvent(event);

        primaryStage.setTitle("Détails de l'événement");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}