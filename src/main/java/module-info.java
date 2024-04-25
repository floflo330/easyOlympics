module isep.fr.easyolympics {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens isep.fr.easyolympics to javafx.fxml;
    exports isep.fr.easyolympics;
    exports isep.fr.easyolympics.controller;
    opens isep.fr.easyolympics.controller to javafx.fxml;
    exports isep.fr.easyolympics.model;
    opens isep.fr.easyolympics.model to javafx.fxml;
}