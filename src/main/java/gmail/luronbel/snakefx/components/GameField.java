package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.exceptions.CrashException;
import lombok.AllArgsConstructor;

import static gmail.luronbel.snakefx.components.GameField.Element.*;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.X;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y;

public class GameField {

    public enum Element {
        SNAKE, WALL, APPLE, OBSTACLE, EMPTY
    }

    @AllArgsConstructor
    public static class Cell {
        protected Element type;
        protected final int x;
        protected final int y;
    }

    private final Cell[][] map = new Cell[Y + 2][X + 2];
    private final boolean withWalls;

    public GameField(final boolean withWalls) {
        this.withWalls = withWalls;
        for (int y = 0; y <= Y + 1; y++) {
            for (int x = 0; x <= X + 1; x++) {
                if (y == 0 || y == Y + 1 || x == 0 || x == X + 1) {
                    map[y][x] = new Cell(WALL, x, y);
                } else {
                    map[y][x] = new Cell(EMPTY, x, y);
                }
            }
        }
        System.out.println("Game field has been created (" + Y + "x" + X + ")");
    }

    public void createSnakeSegment(final int y, final int x) {
        if (map[y][x].type != EMPTY) {
            throw new RuntimeException("Error during creating snake segment "
                    + map[y][x].type + "{" + y + "," + x + "}");
        } else {
            map[y][x].type = SNAKE;
        }
    }

    public void createApple(final int y, final int x) {
        if (map[y][x].type != EMPTY) {
            throw new RuntimeException("Error during creating apple segment");
        } else {
            map[y][x].type = APPLE;
        }
    }

    public boolean moveSnakeSegment(final int fromY, final int fromX,
                                    final int toY, final int toX) throws CrashException {
        System.out.println("Move snake from {" + fromY + "," + fromX + "} to {" + toY + "," + toX + "}");
        final Cell move = map[fromY][fromX];
        if (move.type != Element.SNAKE) {
            throw new RuntimeException("Error element segment state: " + move.type);
        }
        if (map[toY][toX].type == EMPTY) {
            map[toY][toX].type = SNAKE;
            move.type = EMPTY;
            return false;
        } else if (map[toY][toX].type == Element.APPLE) {
            map[toY][toX].type = SNAKE;
            move.type = EMPTY;
            return true;
        } else {
            throw new CrashException(map[toY][toX].type + ": from {" + fromY + "," + fromX + "}" +
                    " to {" + toY + "," + toX + "}");
        }
    }

    public Cell getCell(final int indexY, final int indexX) {
        return map[indexY][indexX];
    }
}
