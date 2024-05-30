package isep.fr.easyolympics;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SportListCell extends ListCell<String> {
    private final HBox content;
    private final Text name;
    private final ImageView imageView;
    private final Map<String, String> imagePaths;

    public SportListCell() {
        super();
        name = new Text();
        imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        content = new HBox(imageView, name);
        content.setSpacing(10);

        imagePaths = new HashMap<>();

        imagePaths.put("athlétisme", "/isep/fr/easyolympics/img/athlétisme.png");
        imagePaths.put("badminton", "/isep/fr/easyolympics/img/badminton.png");
        imagePaths.put("basketball", "/isep/fr/easyolympics/img/basketball.png");
        imagePaths.put("football", "/isep/fr/easyolympics/img/football.png");
        imagePaths.put("lutte", "/isep/fr/easyolympics/img/lutte.png");
        imagePaths.put("Natation Artistique", "/isep/fr/easyolympics/img/Natation Artistique.png");
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            name.setText(item);
            String imagePath = imagePaths.getOrDefault(item.toLowerCase(), "/isep/fr/easyolympics/img/logojo.png");
            try {
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    imageView.setImage(new Image(imageStream));
                } else {
                    System.err.println("Impossible de charger l'image : " + imagePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            setGraphic(content);
            setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10px; -fx-font-size: 20px; -fx-font-family: 'Lemon Milk';");
        }
    }
}