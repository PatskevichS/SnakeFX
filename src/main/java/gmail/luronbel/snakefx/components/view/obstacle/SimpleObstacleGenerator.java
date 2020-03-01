package gmail.luronbel.snakefx.components.view.obstacle;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.Generator;
import gmail.luronbel.snakefx.configuration.CoreData;

/**
 * SimpleObstacleGenerator.
 *
 * @author Stas_Patskevich
 */
public class SimpleObstacleGenerator implements Generator {

    private final ObstacleView view;

    public SimpleObstacleGenerator(final ObstacleView view) {
        this.view = view;
    }


    @Override
    public void generate(final CoreData coreData, final GameField gameField) {
        final GameField.Cell position1 = gameField.findPositionForElement();
        gameField.createObstacle(position1.getY(), position1.getX());
        view.show(coreData.getObstacle(), position1.getY(), position1.getX());

        final GameField.Cell position2 = gameField.findPositionForElement();
        gameField.createObstacle(position2.getY(), position2.getX());
        view.show(coreData.getObstacle(), position2.getY(), position2.getX());

        final GameField.Cell position3 = gameField.findPositionForElement();
        gameField.createObstacle(position3.getY(), position3.getX());
        view.show(coreData.getObstacle(), position3.getY(), position3.getX());

        final GameField.Cell position4 = gameField.findPositionForElement();
        gameField.createObstacle(position4.getY(), position4.getX());
        view.show(coreData.getObstacle(), position4.getY(), position4.getX());
    }
}
