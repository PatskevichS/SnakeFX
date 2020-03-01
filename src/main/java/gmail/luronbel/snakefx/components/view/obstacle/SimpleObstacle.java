package gmail.luronbel.snakefx.components.view.obstacle;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.BORDER;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.CELL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * SimpleObstacle.
 *
 * @author Stas_Patskevich
 */
public class SimpleObstacle implements ObstacleView {
    private final static String IMAGE_URL = "/game/wall.jpg";

    @Override
    public void show(final ImageView view, final int y, final int x) {
        view.setImage(new Image(IMAGE_URL));
        view.setY(BORDER + (CELL * (y - 1)));
        view.setX(BORDER + (CELL * (x - 1)));
        view.setFitHeight(CELL);
        view.setFitWidth(CELL);
        view.setVisible(true);
    }
}
