package gmail.luronbel.snakefx.components.view.apple;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.BORDER;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.CELL;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.RADIUS;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.configuration.CoreData;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import lombok.Setter;

/**
 * SimpleApple.
 *
 * @author Stas_Patskevich
 */
public class SimpleApple implements AppleView {
    @Setter
    protected CoreData coreData;
    @Setter
    protected GameField gameField;

    private Sphere apple;

    @Override
    public void show() {
        if (apple == null) {
            init();
        }

        final GameField.Cell cell = gameField.findPositionForElement();
        gameField.createApple(cell.getY(), cell.getX());
        apple.setTranslateY(BORDER + (CELL * (cell.getY() - 1)) + RADIUS);
        apple.setTranslateX(BORDER + (CELL * (cell.getX() - 1)) + RADIUS);
        apple.setVisible(true);
    }

    private void init() {
        final Sphere view = coreData.getApple();
        view.setVisible(false);
        view.setRadius((RADIUS / 3) * 2);
        view.setCullFace(CullFace.BACK);
        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        view.setMaterial(material);
        apple = view;
    }

}
