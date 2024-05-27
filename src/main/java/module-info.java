module isep.fr.easyolympics {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.apache.pdfbox;
    requires itextpdf;
    requires com.opencsv;

    opens isep.fr.easyolympics to javafx.fxml;
    opens isep.fr.easyolympics.controller to javafx.fxml;
    opens isep.fr.easyolympics.model to javafx.fxml;

    opens isep.fr.easyolympics.csv to javafx.fxml;

    exports isep.fr.easyolympics;
    exports isep.fr.easyolympics.controller;
    exports isep.fr.easyolympics.model;

    exports isep.fr.easyolympics.csv;
}
