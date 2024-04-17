module isep.fr.easyolympics {
    requires javafx.controls;
    requires javafx.fxml;


    opens isep.fr.easyolympics to javafx.fxml;
    exports isep.fr.easyolympics;
}