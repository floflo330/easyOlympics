package isep.fr.easyolympics;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SportListCell extends ListCell<String> {
    private HBox content;
    private Text name;
    private ImageView imageView;

    public SportListCell() {
        super();
        name = new Text();
        imageView = new ImageView();
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        content = new HBox(imageView, name);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            name.setText(item);
            // Replace with actual logic to load appropriate image
            imageView.setImage(new Image("https://github.com/floflo330/easyOlympics/blob/5aa6d226c1d7058e024a9c0ee39bd7df1aad8401/src/main/resources/isep/fr/easyolympics/img/1.png"));
            setGraphic(content);
            setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10px; -fx-font-size: 14px; -fx-font-family: 'Arial';");
        }
    }
}
