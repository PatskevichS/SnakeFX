package gmail.luronbel.snakefx.components.view.obstacle.impl;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.obstacle.AbstractObstacleGenerator;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.stereotype.Component;

/**
 * FullCorners.
 *
 * @author Stas_Patskevich
 */
@Component
public class FullCorners extends AbstractObstacleGenerator {

    @Override
    public void generate(final CoreData coreData, final GameField gameField) {
        final OuterCorners outerCorners = new OuterCorners();
        outerCorners.setView(view);
        outerCorners.generate(coreData, gameField);

        final InnerCorners innerCorners = new InnerCorners();
        innerCorners.setView(view);
        innerCorners.generate(coreData, gameField);
    }
}
