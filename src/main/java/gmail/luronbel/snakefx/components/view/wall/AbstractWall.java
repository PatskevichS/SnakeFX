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
 * AbstractWall.
 *
 * @author Stas_Patskevich
 */
public abstract class AbstractWall implements WallView {
    private final String imageUrl;

    protected AbstractWall(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void show(final ImageView view, final int y, final int x) {
        view.setImage(new Image(imageUrl));
        if (x == 0) {
            if (y == 0) {
                view.setY(BORDER - RADIUS + 1);
                view.setFitHeight(RADIUS);
            } else if (y == Y_COUNT + 1) {
                view.setY(BOTTOM_Y + CELL);
                view.setFitHeight(RADIUS);
            } else {
                view.setY(BORDER + (CELL * (y - 1)));
                view.setFitHeight(CELL);
            }
            view.setX(BORDER - RADIUS);
            view.setFitWidth(RADIUS);
        } else if (y == 0) {
            if (x == X_COUNT + 1) {
                view.setX(RIGHT_X + CELL);
                view.setFitWidth(RADIUS);
            } else {
                view.setX(BORDER + (CELL * (x - 1)));
                view.setFitWidth(CELL);
            }
            view.setY(BORDER - RADIUS + 1);
            view.setFitHeight(RADIUS);
        } else if (x == X_COUNT + 1) {
            if (y == Y_COUNT + 1) {
                view.setY(BOTTOM_Y + CELL);
                view.setFitHeight(RADIUS);
            } else {
                view.setY(BORDER + (CELL * (y - 1)));
                view.setFitHeight(CELL);
            }
            view.setX(RIGHT_X + CELL);
            view.setFitWidth(RADIUS);
        } else if (y == Y_COUNT + 1) {
            view.setY(BOTTOM_Y + CELL);
            view.setX(BORDER + (CELL * (x - 1)));
            view.setFitHeight(RADIUS);
            view.setFitWidth(CELL);
        }
        view.setVisible(true);
    }
}
