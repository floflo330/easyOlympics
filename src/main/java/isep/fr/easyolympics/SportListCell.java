package isep.fr.easyolympics;

import javafx.scene.control.ListCell;

public class SportListCell extends ListCell<String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item);
            setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10px; -fx-font-size: 14px; -fx-font-family: 'Arial';");
        }
    }
}
