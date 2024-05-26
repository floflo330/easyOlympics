package isep.fr.easyolympics;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.InputStream;

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
            // Try to load the specific image
            try {
                String imagePath = "/isep/fr/easyolympics/img/" + item.toLowerCase() + ".png";
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    imageView.setImage(new Image(imageStream));
                } else {
                    // Load the default image if the specific image is not found
                    imageView.setImage(new Image("/isep/fr/easyolympics/img/1.png"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            setGraphic(content);
            setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10px; -fx-font-size: 14px; -fx-font-family: 'Arial';");
        }
    }
}
