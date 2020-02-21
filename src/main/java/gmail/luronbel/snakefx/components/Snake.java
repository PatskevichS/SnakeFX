package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.configuration.Direction;
import gmail.luronbel.snakefx.exceptions.CrashException;
import gmail.luronbel.snakefx.layout.GameElementsGroup;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.ArrayList;

import static gmail.luronbel.snakefx.components.GameField.Element.EMPTY;
import static gmail.luronbel.snakefx.configuration.Direction.*;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.*;

public class Snake {

//    @AllArgsConstructor
//    @Data
//    private static class SnakeView {
//        private final Sphere view;
//    }

    @AllArgsConstructor
    private static class Segment {
        protected int y;
        protected int x;
    }


    private final GameElementsGroup gameElementsGroup;
    private final GameField gameField;
    private Direction direction = LEFT;
    private final Sphere[][] map = new Sphere[Y + 2][X + 2];

    private final ArrayList<Segment> snake = new ArrayList<>();
    private Segment tail;


    public Snake(@NonNull final GameElementsGroup gameElementsGroup, @NonNull final GameField gameField) {
        this.gameElementsGroup = gameElementsGroup;
        this.gameField = gameField;
        for (int y = 0; y <= Y + 1; y++) {
            for (int x = 0; x <= X + 1; x++) {
                if (y == 0 || y == Y + 1 || x == 0 || x == X + 1) {
                    continue;
                }
                final GameField.Cell cell = gameField.getCell(y, x);
                if (cell.type == EMPTY) {
                    map[y][x] = createView(y, x);
                }
            }
        }
    }

    public void init() {
        createSegment(6, 10);
        createSegment(6, 11);
        createSegment(6, 12);
        createSegment(6, 13);

        gameField.createApple(2, 5);
        gameField.createApple(2, 8);
        gameField.createApple(2, 12);
    }

    public void move() throws CrashException {
        final Segment head = snake.get(0);
        switch (direction) {
            case LEFT:
                move(head.y, head.x - 1);
                break;
            case RIGHT:
                move(head.y, head.x + 1);
                break;
            case TOP:
                move(head.y - 1, head.x);
                break;
            case BOTTOM:
                move(head.y + 1, head.x);
                break;
        }
    }

    private void move(final int headY, final int headX) throws CrashException {
        boolean skip = true;
        final Segment head = snake.get(0);
        int previousY = head.y;
        int previousX = head.x;
        head.y = headY;
        head.x = headX;
        if (moveSnakeSegment(previousY, previousX, headY, headX)) {
            System.out.println("Create new snake segment");
            if (direction == LEFT) {
                createSegment(tail.y, tail.x + 1);
            } else if (direction == RIGHT) {
                createSegment(tail.y, tail.x - 1);
            } else if (direction == TOP) {
                createSegment(tail.y + 1, tail.x);
            } else if (direction == BOTTOM) {
                createSegment(tail.y - 1, tail.x);
            }
        }

        for (final Segment segment : snake) {
            if (skip) {
                skip = false;
                continue;
            }
            final int y = segment.y;
            final int x = segment.x;
            moveSnakeSegment(y, x, previousY, previousX);
            segment.y = previousY;
            segment.x = previousX;
            previousY = y;
            previousX = x;
        }
    }

    public boolean moveSnakeSegment(final int fromY, final int fromX,
                                    final int toY, final int toX) throws CrashException {
        final boolean needCreateNewOne = gameField.moveSnakeSegment(fromY, fromX, toY, toX);
        map[fromY][fromX].setVisible(false);
        map[toY][toX].setVisible(true);
        return needCreateNewOne;
    }

    private void createSegment(final int y, final int x) {
        gameField.createSnakeSegment(y, x);
        final Sphere snakeView = map[y][x];
        snakeView.setVisible(true);
        final Segment segment = new Segment(y, x);
        snake.add(segment);
        tail = segment;
    }

    private Sphere createView(final int y, final int x) {
        if (map[y][x] != null) {
            throw new RuntimeException("Error creating snake view");
        }
        final Sphere view = new Sphere();
        view.setVisible(false);
        view.setRadius(RADIUS);
        view.setTranslateY(BORDER + (CELL * (y - 1)) + RADIUS);
        view.setTranslateX(BORDER + (CELL * (x - 1)) + RADIUS);
        view.setCullFace(CullFace.BACK);
        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.AQUA);
        view.setMaterial(material);
        gameElementsGroup.getChildren().add(view);
        return view;
    }

    public void setDirection(@NonNull final Direction direction) {
        if (this.direction == TOP && direction == BOTTOM) {
            return;
        } else if (this.direction == BOTTOM && direction == TOP) {
            return;
        } else if (this.direction == LEFT && direction == RIGHT) {
            return;
        } else if (this.direction == RIGHT && direction == LEFT) {
            return;
        }
        this.direction = direction;
    }
}
