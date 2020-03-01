package gmail.luronbel.snakefx.components.utils;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * SpotFinder.
 *
 * @author Stas_Patskevich
 */

public class SpotFinder {

    private final List<Entity> data = new ArrayList<>();
    private final GameField gameField;
    private final Random random = new Random();

    public SpotFinder(final GameField gameField) {
        for (int y = 1; y <= Y_COUNT; y++) {
            for (int x = 1; x <= X_COUNT; x++) {
                data.add(new Entity(y, x));
            }
        }
        Collections.shuffle(data);
        this.gameField = gameField;
    }

    public GameField.Cell find() {
        final Entity position = data.get(random.nextInt(data.size()));
        return gameField.getCell(position.y, position.x);
    }

    public void addPosition(final int y, final int x) {
        final Entity entity = new Entity(y, x);
        if (!data.contains(entity)) {
            data.add(entity);
        } else {
            throw new RuntimeException("SpotFinder data already contains this value {" + y + "," + x + "}");
        }
    }

    public void removePosition(final int y, final int x) {
        final Entity entity = new Entity(y, x);
        if (data.contains(entity)) {
            data.remove(entity);
        } else {
            throw new RuntimeException("SpotFinder data does not contain this value {" + y + "," + x + "}");
        }
    }

    @AllArgsConstructor
    private static class Entity {
        private final int y;
        private final int x;

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Entity entity = (Entity) o;
            return y == entity.y &&
                    x == entity.x;
        }

        @Override
        public int hashCode() {
            return Integer.parseInt("99" + y + "77" + x);
        }
    }
}
