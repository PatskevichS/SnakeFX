package gmail.luronbel.snakefx.components.view.wall;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.Generator;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.lang.NonNull;

/**
 * SimpleWallGenerator.
 *
 * @author Stas_Patskevich
 */
public class SimpleWallGenerator implements Generator {

    private final WallView view;

    public SimpleWallGenerator(final WallView view) {
        this.view = view;
    }

    @Override
    public void generate(@NonNull final CoreData coreData, @NonNull final GameField gameField) {
        gameField.createWall(1, 0);
        view.show(coreData.getWall(), 1, 0);

        gameField.createWall(0, 2);
        view.show(coreData.getWall(), 0, 2);

        gameField.createWall(3, X_COUNT + 1);
        view.show(coreData.getWall(), 3, X_COUNT + 1);

        gameField.createWall(Y_COUNT + 1, 4);
        view.show(coreData.getWall(), Y_COUNT + 1, 4);
    }
}
