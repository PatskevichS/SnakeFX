package gmail.luronbel.snakefx.components.view.obstacle.impl;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.obstacle.AbstractObstacleGenerator;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.stereotype.Component;

/**
 * Random.
 *
 * @author Stas_Patskevich
 */
@Component
public class RandomObstacles extends AbstractObstacleGenerator {
    private static final int RANDOM_COUNT = 20;

    @Override
    public void generate(final CoreData coreData, final GameField gameField) {
        if (RANDOM_COUNT > Y_COUNT * X_COUNT - 15) {
            throw new RuntimeException("Initialization error");
        }

        for (int i = 1; i <= RANDOM_COUNT; i++) {
            final GameField.Cell position = gameField.findPositionForElement();
            gameField.createObstacle(position.getY(), position.getX());
            view.show(coreData.getObstacle(), position.getY(), position.getX());
        }
    }
}
