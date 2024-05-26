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

        // Définir les chemins d'image pour chaque discipline
        imagePaths = new HashMap<>();
        imagePaths.put("athlétisme", "/isep/fr/easyolympics/img/Athlétisme.svg");
        imagePaths.put("aviron", "/isep/fr/easyolympics/img/swimming.png");
        imagePaths.put("badminton", "/isep/fr/easyolympics/img/BDM_small.svg");
        imagePaths.put("basketball", "/isep/fr/easyolympics/img/BKB_small.svg");
        imagePaths.put("swimming", "/isep/fr/easyolympics/img/swimming.png");
        // Ajoutez ici les autres disciplines avec leurs chemins d'image correspondants
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            name.setText(item);
            // Charger l'image spécifique à la discipline si elle existe, sinon charger l'image par défaut
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
