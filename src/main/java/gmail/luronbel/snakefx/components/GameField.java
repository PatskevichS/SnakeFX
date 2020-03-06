package gmail.luronbel.snakefx.components;

import static gmail.luronbel.snakefx.enm.Type.APPLE;
import static gmail.luronbel.snakefx.enm.Type.EMPTY;
import static gmail.luronbel.snakefx.enm.Type.OBSTACLE;
import static gmail.luronbel.snakefx.enm.Type.SNAKE;
import static gmail.luronbel.snakefx.enm.Type.WALL;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.utils.SpotFinder;
import gmail.luronbel.snakefx.enm.Type;
import gmail.luronbel.snakefx.exceptions.CrashException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class GameField {

    @AllArgsConstructor
    public static class Cell {
        protected Type element;
        @Getter
        protected final int x;
        @Getter
        protected final int y;
    }

    @Getter
    private final Cell[][] map = new Cell[Y_COUNT + 2][X_COUNT + 2];
    protected final SpotFinder spotFinder;

    public GameField() {
        spotFinder = new SpotFinder(this);
        for (int y = 0; y <= Y_COUNT + 1; y++) {
            for (int x = 0; x <= X_COUNT + 1; x++) {
                map[y][x] = new Cell(EMPTY, x, y);
            }
        }
        System.out.println("Game field has been created (" + Y_COUNT + "x" + X_COUNT + ")");
    }

    public void createSnakeSegment(final int y, final int x) {
        if (map[y][x].element != EMPTY) {
            throw new RuntimeException("Error during creating snake segment "
                    + map[y][x].element + "{" + y + "," + x + "}");
        } else {
            map[y][x].element = SNAKE;
        }
    }

    public void createApple(final int y, final int x) {
        if (map[y][x].element != EMPTY) {
            throw new RuntimeException("Error during creating apple segment");
        } else {
            map[y][x].element = APPLE;
        }
    }

    public void createWall(final int y, final int x) {
        if (map[y][x].element != EMPTY) {
            throw new RuntimeException("Error during creating wall segment");
        } else {
            map[y][x].element = WALL;
        }
    }

    public void createObstacle(final int y, final int x) {
        if (map[y][x].element != EMPTY) {
            throw new RuntimeException("Error during creating obstacle segment {" + y + "," + x + "}");
        } else {
            map[y][x].element = OBSTACLE;
            catchPosition(y, x);
        }
    }

    public boolean moveSnakeSegment(final int fromY, final int fromX,
                                    final int toY, final int toX) throws CrashException {
//        System.out.println("Move snake from {" + fromY + "," + fromX + "} to {" + toY + "," + toX + "}");
        final Cell move = map[fromY][fromX];
        if (move.element != SNAKE) {
            throw new RuntimeException("Error element segment state: " + move.element);
        }
        if (map[toY][toX].element == EMPTY) {
            map[toY][toX].element = SNAKE;
            move.element = EMPTY;
            return false;
        } else if (map[toY][toX].element == APPLE) {
            map[toY][toX].element = SNAKE;
            move.element = EMPTY;
            return true;
        } else {
            throw new CrashException(map[toY][toX].element + ": from {" + fromY + "," + fromX + "}" +
                    " to {" + toY + "," + toX + "}");
        }
    }

    public Cell findPositionForElement() {
        return spotFinder.find();
    }

    public void revealPosition(final int y, final int x) {
        spotFinder.addPosition(y, x);
    }

    public void catchPosition(final int y, final int x) {
        spotFinder.removePosition(y, x);
    }

    public Cell getCell(final int indexY, final int indexX) {
        return map[indexY][indexX];
    }

    public Type getCellType(final int indexY, final int indexX) {
        return map[indexY][indexX].element;
    }
}
