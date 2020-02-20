package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.configuration.Direction;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

import static gmail.luronbel.snakefx.components.GameElements.GAME_ELEMENTS_BEAN;
import static gmail.luronbel.snakefx.components.GameFieldLayout.*;
import static gmail.luronbel.snakefx.configuration.Direction.*;

@Component(Game.GAME_BEAN)
public class Game {
    public static final String GAME_BEAN = "game";

    private boolean isDone = false;
    private final boolean isPaused = false;
    private Direction direction = LEFT;
    private boolean isDirectionChanged = false;
    private final ArrayList<Sphere> snake = new ArrayList<>();

    @Autowired
    @Qualifier(GAME_FIELD_LAYOUT_BEAN)
    private GameFieldLayout gameFieldLayout;

    @Autowired
    @Qualifier(GAME_ELEMENTS_BEAN)
    private GameElements gameElements;

    public void start() {
        init();
        new Thread(() -> {
            while (!isDone && !isPaused) {
                try {
                    move();
                    Thread.sleep(200);
                } catch (final Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }).start();
        new Thread(() -> {
            Random random = new Random();
            while (!isDone && !isPaused) {
                int x = random.nextInt(X);
                int y = random.nextInt(Y);
            }
        }).start();
        System.out.println("Game has been started");
    }

    public void stop() {
        System.out.println("Game has been stopped");
        isDone = true;
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
        isDirectionChanged = true;
    }

    private void move() {
        final Sphere head = snake.get(0);
        switch (direction) {
            case LEFT:
                moveSnake(head.getTranslateX() - CELL, head.getTranslateY());
                break;
            case RIGHT:
                moveSnake(head.getTranslateX() + CELL, head.getTranslateY());
                break;
            case TOP:
                moveSnake(head.getTranslateX(), head.getTranslateY() - CELL);
                break;
            case BOTTOM:
                moveSnake(head.getTranslateX(), head.getTranslateY() + CELL);
                break;
        }
    }

    private void moveSnake(final double headX, final double headY) {
        validateHeadPosition(headX, headY);
        boolean skip = true;
        final Sphere head = snake.get(0);
        double previousX = head.getTranslateX();
        double previousY = head.getTranslateY();
        head.setTranslateX(headX);
        head.setTranslateY(headY);

        for (final Sphere segment : snake) {
            if (skip) {
                skip = false;
                continue;
            }
            final double x = segment.getTranslateX();
            final double y = segment.getTranslateY();
            segment.setTranslateX(previousX);
            segment.setTranslateY(previousY);
            previousX = x;
            previousY = y;

        }
    }

    private void validateHeadPosition(final double x, final double y) {
        if ((x < LEFT_X || x > RIGHT_X) || (y < TOP_Y || y > BOTTOM_Y)) {
            System.out.println("Snake crashed into a wall");
            stop();
        }
    }

    private void init() {
        createSegment((double) 425 + RADIUS, (double) 225 + RADIUS);
        createSegment((double) 425 + RADIUS, (double) 225 + RADIUS);
    }

    private void createSegment(final double x, final double y) {
        final Sphere segment = new Sphere();
        segment.setRadius(RADIUS);
        segment.setTranslateX(x);
        segment.setTranslateY(y);
        segment.setCullFace(CullFace.BACK);
        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.AQUA);
        segment.setMaterial(material);
        snake.add(segment);
        gameElements.getChildren().add(segment);
    }
}
