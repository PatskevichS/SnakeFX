package gmail.luronbel.snakefx.components.view.wall;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.BORDER;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.BOTTOM_Y;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.CELL;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.RADIUS;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.RIGHT_X;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * SimpleWall.
 *
 * @author Stas_Patskevich
 */
public class SimpleWall implements WallView {
    private final static String IMAGE_URL = "/game/wall2.jpg";

    @Override
    public void show(final ImageView view, final int y, final int x) {
        view.setImage(new Image(IMAGE_URL));
        if (x == 0) {
            view.setX(BORDER - RADIUS);
            view.setY(BORDER + (CELL * (y - 1)));
            view.setFitHeight(CELL);
            view.setFitWidth(RADIUS);
        } else if (x == X_COUNT + 1) {
            view.setX(RIGHT_X + CELL);
            view.setY(BORDER + (CELL * (y - 1)));
            view.setFitHeight(CELL);
            view.setFitWidth(RADIUS);
        } else if (y == 0) {
            view.setY(BORDER - RADIUS);
            view.setX(BORDER + (CELL * (x - 1)));
            view.setFitHeight(RADIUS);
            view.setFitWidth(CELL);
        } else if (y == Y_COUNT + 1) {
            view.setY(BOTTOM_Y + CELL);
            view.setX(BORDER + (CELL * (x - 1)));
            view.setFitHeight(RADIUS);
            view.setFitWidth(CELL);
        }
        view.setVisible(true);
    }
}
