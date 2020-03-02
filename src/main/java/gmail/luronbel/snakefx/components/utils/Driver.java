package gmail.luronbel.snakefx.components.utils;

import static gmail.luronbel.snakefx.components.utils.Driver.Direction.BOTTOM;
import static gmail.luronbel.snakefx.components.utils.Driver.Direction.LEFT;
import static gmail.luronbel.snakefx.components.utils.Driver.Direction.TOP;
import static gmail.luronbel.snakefx.enm.Type.WALL;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;

import gmail.luronbel.snakefx.components.GameField;
import org.springframework.lang.NonNull;

/**
 * Direction.
 *
 * @author Stas_Patskevich
 */
public class Driver {

    public enum Direction {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private Direction direction = TOP;
    private boolean canChangeDirection = true;
    private final GameField gameField;

    public Driver(@NonNull final GameField gameField) {
        this.gameField = gameField;
    }

    public void setDirection(@NonNull final Direction direction) {
        if (!canChangeDirection) {
            return;
        }
        if (this.direction == TOP && direction == BOTTOM) {
            return;
        } else if (this.direction == BOTTOM && direction == TOP) {
            return;
        } else if (this.direction == LEFT && direction == Direction.RIGHT) {
            return;
        } else if (this.direction == Direction.RIGHT && direction == LEFT) {
            return;
        }
        System.out.println("Direction changed to " + direction);
        this.direction = direction;
        canChangeDirection = false;
    }

    public void forbidChangingDirection() {
        canChangeDirection = false;
    }

    public void allowChangingDirection() {
        canChangeDirection = true;
    }

    public int resolveHeadY(int y, final int x) {
        switch (direction) {
            case TOP:
                y -= 1;
                break;
            case BOTTOM:
                y += 1;
                break;
        }
        if (y < 1 && gameField.getCellType(y, x) != WALL) {
            y = Y_COUNT;
        } else if (y > Y_COUNT && gameField.getCellType(y, x) != WALL) {
            y = 1;
        }
        return y;
    }

    public int resolveHeadX(final int y, int x) {
        switch (direction) {
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
        }
        if (x < 1 && gameField.getCellType(y, x) != WALL) {
            x = X_COUNT;
        } else if (x > X_COUNT && gameField.getCellType(y, x) != WALL) {
            x = 1;
        }
        return x;
    }

}
