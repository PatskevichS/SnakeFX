package gmail.luronbel.snakefx.components.view.wall.impl;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.wall.AbstractWallGenerator;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Checkers.
 *
 * @author Stas_Patskevich
 */
@Component
public class Checkers extends AbstractWallGenerator {

    @Override
    public void generate(@NonNull final CoreData coreData, @NonNull final GameField gameField) {

        //  vertical sides
        for (int i = 3; i <= 5; i++) {
            gameField.createWall(i, 0);
            gameField.createWall(i, X_COUNT + 1);
            gameField.createWall(i + 7, 0);
            gameField.createWall(i + 7, X_COUNT + 1);
            view.show(coreData.getWall(), i, 0);
            view.show(coreData.getWall(), i, X_COUNT + 1);
            view.show(coreData.getWall(), i + 7, 0);
            view.show(coreData.getWall(), i + 7, X_COUNT + 1);
        }

        //  horizontal sides
        for (int i = 6; i <= 11; i++) {
            gameField.createWall(0, i);
            gameField.createWall(Y_COUNT + 1, i);
            gameField.createWall(0, i + 12);
            gameField.createWall(Y_COUNT + 1, i + 12);
            view.show(coreData.getWall(), 0, i);
            view.show(coreData.getWall(), Y_COUNT + 1, i);
            view.show(coreData.getWall(), 0, i + 12);
            view.show(coreData.getWall(), Y_COUNT + 1, i + 12);
        }
    }

}
