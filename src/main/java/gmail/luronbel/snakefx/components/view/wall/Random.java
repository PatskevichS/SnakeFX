package gmail.luronbel.snakefx.components.view.wall;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.Generator;
import gmail.luronbel.snakefx.configuration.CoreData;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Random.
 *
 * @author Stas_Patskevich
 */
@AllArgsConstructor
public class Random implements Generator {
    private static final int RANDOM_Y_COUNT = 6;
    private static final int RANDOM_X_COUNT = 16;

    protected final WallView view;


    @Override
    public void generate(@NonNull final CoreData coreData, @NonNull final GameField gameField) {
        final List<Integer> randomY = new ArrayList<>();
        final List<Integer> randomX = new ArrayList<>();

        for (int y = 1; y <= Y_COUNT; y++) {
            randomY.add(y);
        }
        for (int x = 1; x <= X_COUNT; x++) {
            randomX.add(x);
        }
        Collections.shuffle(randomY);
        Collections.shuffle(randomX);

        final List<Integer> left = new ArrayList<>(randomY);
        Collections.shuffle(left);
        for (int l = 1; l <= RANDOM_Y_COUNT; l++) {
            final int position = left.get(l);
            gameField.createWall(position, 0);
            view.show(coreData.getWall(), position, 0);
        }

        final List<Integer> right = new ArrayList<>(randomY);
        Collections.shuffle(right);
        for (int r = 1; r <= RANDOM_Y_COUNT; r++) {
            final int position = right.get(r);
            gameField.createWall(position, X_COUNT + 1);
            view.show(coreData.getWall(), position, X_COUNT + 1);
        }

        final List<Integer> top = new ArrayList<>(randomX);
        Collections.shuffle(top);
        for (int t = 1; t <= RANDOM_X_COUNT; t++) {
            final int position = top.get(t);
            gameField.createWall(0, position);
            view.show(coreData.getWall(), 0, position);
        }

        final List<Integer> bottom = new ArrayList<>(randomX);
        Collections.shuffle(bottom);
        for (int b = 1; b <= RANDOM_X_COUNT; b++) {
            final int position = bottom.get(b);
            gameField.createWall(Y_COUNT + 1, position);
            view.show(coreData.getWall(), Y_COUNT + 1, position);
        }
    }
}
