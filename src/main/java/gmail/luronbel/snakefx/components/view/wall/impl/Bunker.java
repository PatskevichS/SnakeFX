package gmail.luronbel.snakefx.components.view.wall.impl;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.wall.AbstractWallGenerator;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.lang.NonNull;

/**
 * Bunker.
 *
 * @author Stas_Patskevich
 */
public class Bunker extends AbstractWallGenerator {

    @Override
    public void generate(@NonNull final CoreData coreData, @NonNull final GameField gameField) {
        for (int y = 1; y <= Y_COUNT; y++) {
            gameField.createWall(y, 0);
            view.show(coreData.getWall(), y, 0);
            gameField.createWall(y, X_COUNT + 1);
            view.show(coreData.getWall(), y, X_COUNT + 1);
        }
        for (int x = 1; x <= X_COUNT; x++) {
            gameField.createWall(0, x);
            view.show(coreData.getWall(), 0, x);
            gameField.createWall(Y_COUNT + 1, x);
            view.show(coreData.getWall(), Y_COUNT + 1, x);
        }
        view.show(coreData.getWall(), 0, 0);
        view.show(coreData.getWall(), Y_COUNT + 1, 0);
        view.show(coreData.getWall(), 0, X_COUNT + 1);
        view.show(coreData.getWall(), Y_COUNT + 1, X_COUNT + 1);
    }
}
