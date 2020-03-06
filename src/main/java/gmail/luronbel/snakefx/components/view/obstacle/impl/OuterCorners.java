package gmail.luronbel.snakefx.components.view.obstacle.impl;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.obstacle.AbstractObstacleGenerator;
import gmail.luronbel.snakefx.configuration.CoreData;

/**
 * Corners.
 *
 * @author Stas_Patskevich
 */
public class OuterCorners extends AbstractObstacleGenerator {
    public static final int LENGTH = 3;

    @Override
    public void generate(final CoreData coreData, final GameField gameField) {
        final int hor_gap = X_COUNT - LENGTH * 2 - 2;
        final int ver_gap = Y_COUNT - LENGTH * 2 - 2;

        // horizontal lines
        for (int i = 2; i <= LENGTH + 1; i++) {
            gameField.createObstacle(2, i);
            gameField.createObstacle(2, i + LENGTH + hor_gap);
            gameField.createObstacle(Y_COUNT - 1, i);
            gameField.createObstacle(Y_COUNT - 1, i + LENGTH + hor_gap);
            view.show(coreData.getObstacle(), 2, i);
            view.show(coreData.getObstacle(), 2, i + LENGTH + hor_gap);
            view.show(coreData.getObstacle(), Y_COUNT - 1, i);
            view.show(coreData.getObstacle(), Y_COUNT - 1, i + LENGTH + hor_gap);
        }
//
        // vertical top lines
        for (int i = 3; i <= LENGTH + 1; i++) {
            gameField.createObstacle(i, 2);
            gameField.createObstacle(i, X_COUNT - 1);
            view.show(coreData.getObstacle(), i, 2);
            view.show(coreData.getObstacle(), i, X_COUNT - 1);
        }

        // vertical bottom lines
        for (int i = 2 + LENGTH + ver_gap; i <= LENGTH * 2 + ver_gap; i++) {
            gameField.createObstacle(i, 2);
            gameField.createObstacle(i, X_COUNT - 1);
            view.show(coreData.getObstacle(), i, 2);
            view.show(coreData.getObstacle(), i, X_COUNT - 1);
        }
    }
}
