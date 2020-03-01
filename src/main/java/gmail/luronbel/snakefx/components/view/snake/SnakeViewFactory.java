package gmail.luronbel.snakefx.components.view.snake;

import javafx.scene.shape.Sphere;

/**
 * SnakeViewFactory.
 *
 * @author Stas_Patskevich
 */
public interface SnakeViewFactory {
    SnakeView wrap(Sphere view);
}
