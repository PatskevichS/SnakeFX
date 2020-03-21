package gmail.luronbel.snakefx.components.view.snake;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.RADIUS;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;

/**
 * ColorfulSnakeViewElement.
 *
 * @author Stas_Patskevich
 */
public class ColorfulSnakeViewElement extends AbstractSnakeViewElement {

    public ColorfulSnakeViewElement(final Sphere view) {
        super(view);
        view.setVisible(false);
        view.setRadius(RADIUS - 0.5);
        view.setCullFace(CullFace.BACK);
        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.color(Math.random(), Math.random(), Math.random()));
        view.setMaterial(material);
    }

}
