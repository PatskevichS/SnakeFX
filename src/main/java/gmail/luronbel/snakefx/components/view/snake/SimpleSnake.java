package gmail.luronbel.snakefx.components.view.snake;

import javafx.scene.shape.Sphere;
import org.springframework.stereotype.Component;

/**
 * SimpleSnakeViewFactory.
 *
 * @author Stas_Patskevich
 */
@Component
public class SimpleSnake implements SnakeViewFactory {

    @Override
    public SnakeView wrap(final Sphere view) {
        return new SimpleSnakeViewElement(view);
    }
}
