package gmail.luronbel.snakefx.components.view.snake;

import javafx.scene.shape.Sphere;

/**
 * SimpleSnakeViewFactory.
 *
 * @author Stas_Patskevich
 */
public class SimpleSnakeViewFactory implements SnakeViewFactory {

    @Override
    public SnakeView wrap(final Sphere view) {
        return new SimpleSnakeView(view);
    }
}
