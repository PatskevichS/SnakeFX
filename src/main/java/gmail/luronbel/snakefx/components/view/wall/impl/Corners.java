package gmail.luronbel.snakefx.components.view.wall.impl;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.wall.AbstractWallGenerator;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.lang.NonNull;

/**
 * Corners.
 *
 * @author Stas_Patskevich
 */
public class Corners extends AbstractWallGenerator {
    private static final int SCALE = 4;

    @Override
    public void generate(@NonNull final CoreData coreData, @NonNull final GameField gameField) {
        final int count;

        if (Y_COUNT < X_COUNT) {
            count = Y_COUNT / SCALE;
        } else {
            count = X_COUNT / SCALE;
        }

        // left
        for (int i = 1; i <= count; i++) {
            gameField.createWall(i, 0);
            view.show(coreData.getWall(), i, 0);
            gameField.createWall(Y_COUNT - i + 1, 0);
            view.show(coreData.getWall(), Y_COUNT - i + 1, 0);
        }

        // right
        for (int i = 1; i <= count; i++) {
            gameField.createWall(i, X_COUNT + 1);
            view.show(coreData.getWall(), i, X_COUNT + 1);
            gameField.createWall(Y_COUNT - i + 1, X_COUNT + 1);
            view.show(coreData.getWall(), Y_COUNT - i + 1, X_COUNT + 1);
        }

        // top
        for (int i = 1; i <= count; i++) {
            gameField.createWall(0, i);
            view.show(coreData.getWall(), 0, i);
            gameField.createWall(0, X_COUNT - i + 1);
            view.show(coreData.getWall(), 0, X_COUNT - i + 1);
        }

        // bottom
        for (int i = 1; i <= count; i++) {
            gameField.createWall(Y_COUNT + 1, i);
            view.show(coreData.getWall(), Y_COUNT + 1, i);
            gameField.createWall(Y_COUNT + 1, X_COUNT - i + 1);
            view.show(coreData.getWall(), Y_COUNT + 1, X_COUNT - i + 1);
        }

        view.show(coreData.getWall(), 0, 0);
        view.show(coreData.getWall(), Y_COUNT + 1, 0);
        view.show(coreData.getWall(), 0, X_COUNT + 1);
        view.show(coreData.getWall(), Y_COUNT + 1, X_COUNT + 1);
    }
}
