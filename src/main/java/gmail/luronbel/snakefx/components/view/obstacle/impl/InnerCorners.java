package gmail.luronbel.snakefx.components.view.obstacle.impl;

import static gmail.luronbel.snakefx.components.view.obstacle.impl.OuterCorners.LENGTH;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.obstacle.AbstractObstacleGenerator;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.stereotype.Component;

/**
 * InnerCorners.
 *
 * @author Stas_Patskevich
 */
@Component
public class InnerCorners extends AbstractObstacleGenerator {

    @Override
    public void generate(final CoreData coreData, final GameField gameField) {

        final int hor_gap = X_COUNT - LENGTH * 4 - 10;
        final int ver_gap = Y_COUNT - LENGTH * 4;

        // horizontal lines
        for (int i = 6 + LENGTH; i <= 5 + LENGTH * 2; i++) {
            gameField.createObstacle(4, i);
            gameField.createObstacle(4, i + LENGTH + hor_gap);
            gameField.createObstacle(Y_COUNT - 3, i);
            gameField.createObstacle(Y_COUNT - 3, i + LENGTH + hor_gap);
            view.show(coreData.getObstacle(), 4, i);
            view.show(coreData.getObstacle(), 4, i + LENGTH + hor_gap);
            view.show(coreData.getObstacle(), Y_COUNT - 3, i);
            view.show(coreData.getObstacle(), Y_COUNT - 3, i + LENGTH + hor_gap);
        }

        // vertical top lines
        for (int i = 5; i <= LENGTH * 2; i++) {
            gameField.createObstacle(i, 6 + LENGTH);
            gameField.createObstacle(i, X_COUNT - 5 - LENGTH);
            view.show(coreData.getObstacle(), i, 6 + LENGTH);
            view.show(coreData.getObstacle(), i, X_COUNT - 5 - LENGTH);
        }

        // vertical bottom lines
        for (int i = 4 + LENGTH + ver_gap; i <= LENGTH * 2 + 2 + ver_gap; i++) {
            gameField.createObstacle(i, 6 + LENGTH);
            gameField.createObstacle(i, X_COUNT - 5 - LENGTH);
            view.show(coreData.getObstacle(), i, 6 + LENGTH);
            view.show(coreData.getObstacle(), i, X_COUNT - 5 - LENGTH);
        }
    }
}
