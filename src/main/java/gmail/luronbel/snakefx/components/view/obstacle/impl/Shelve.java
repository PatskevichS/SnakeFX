package gmail.luronbel.snakefx.components.view.obstacle.impl;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.obstacle.AbstractObstacleGenerator;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.stereotype.Component;

/**
 * Shelve.
 *
 * @author Stas_Patskevich
 */
@Component
public class Shelve extends AbstractObstacleGenerator {
    public static final int LENGTH = 4;

    @Override
    public void generate(final CoreData coreData, final GameField gameField) {
        final int hor_gap = X_COUNT - LENGTH * 2 - 6;

        // horizontal lines
        for (int i = LENGTH; i < LENGTH * 2; i++) {
            gameField.createObstacle(3, i);
            gameField.createObstacle(3, i + LENGTH + hor_gap);
            gameField.createObstacle(Y_COUNT - 2, i);
            gameField.createObstacle(Y_COUNT - 2, i + LENGTH + hor_gap);
            view.show(coreData.getObstacle(), 3, i);
            view.show(coreData.getObstacle(), 3, i + LENGTH + hor_gap);
            view.show(coreData.getObstacle(), Y_COUNT - 2, i);
            view.show(coreData.getObstacle(), Y_COUNT - 2, i + LENGTH + hor_gap);
        }

        // vertical top lines
        for (int i = LENGTH; i <= Y_COUNT - 3; i++) {
            gameField.createObstacle(i, LENGTH);
            gameField.createObstacle(i, X_COUNT - 3);
            view.show(coreData.getObstacle(), i, LENGTH);
            view.show(coreData.getObstacle(), i, X_COUNT - 3);
        }

        // central lines
        for (int i = LENGTH * 2 - 1; i < X_COUNT - LENGTH - 1; i++) {
            gameField.createObstacle(6, i);
            gameField.createObstacle(Y_COUNT - 5, i);
            view.show(coreData.getObstacle(), 6, i);
            view.show(coreData.getObstacle(), Y_COUNT - 5, i);
        }
    }

}
