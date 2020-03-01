package gmail.luronbel.snakefx.components.view.snake;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.BORDER;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.CELL;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.RADIUS;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;

/**
 * SimpleSnakeView.
 *
 * @author Stas_Patskevich
 */
public class SimpleSnakeView implements SnakeView {

    private final Sphere view;
    private int y;
    private int x;

    public SimpleSnakeView(final Sphere view) {
        view.setVisible(false);
        view.setRadius(RADIUS - 0.5);
        view.setCullFace(CullFace.BACK);
        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.AQUA);
        view.setMaterial(material);
        this.view = view;
    }

    @Override
    public SnakeView setY(final int y) {
        this.y = y;
        view.setTranslateY(BORDER + (CELL * (y - 1)) + RADIUS);
        return this;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public SnakeView setX(final int x) {
        this.x = x;
        view.setTranslateX(BORDER + (CELL * (x - 1)) + RADIUS);
        return this;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void show() {
        view.setVisible(true);
    }
}
