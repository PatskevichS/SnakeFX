package gmail.luronbel.snakefx.components.view.snake;

import javafx.scene.shape.Sphere;
import org.springframework.stereotype.Component;

/**
 * ColofullSnake.
 *
 * @author Stas_Patskevich
 */
@Component
public class Colorful implements SnakeViewFactory {

    @Override
    public SnakeView wrap(final Sphere view) {
        return new ColorfulSnakeViewElement(view);
    }
}
