package gmail.luronbel.snakefx.components.view.wall.impl;

import gmail.luronbel.snakefx.components.view.wall.AbstractWall;
import org.springframework.stereotype.Component;

/**
 * SimpleWall.
 *
 * @author Stas_Patskevich
 */
@Component
public class SimpleWall extends AbstractWall {
    private static final String IMAGE_URL = "/game/wall2.jpg";

    public SimpleWall() {
        super(IMAGE_URL);
    }
}
