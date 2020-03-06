package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.components.utils.Driver;
import gmail.luronbel.snakefx.components.view.snake.SnakeView;
import gmail.luronbel.snakefx.components.view.snake.SnakeViewFactory;
import gmail.luronbel.snakefx.configuration.CoreData;
import gmail.luronbel.snakefx.exceptions.CrashException;
import org.springframework.lang.NonNull;

import java.util.ArrayList;

public class Snake {
    protected final CoreData coreData;
    protected final GameField gameField;

    private final Driver driver;
    private final SnakeViewFactory factory;
    private final ArrayList<SnakeView> snake = new ArrayList<>();
    private SnakeView tail;

    public Snake(@NonNull final CoreData coreData, @NonNull final GameField gameField,
                 @NonNull final SnakeViewFactory factory) {
        this.factory = factory;
        this.coreData = coreData;
        this.gameField = gameField;
        driver = new Driver(gameField);
        init();
        hide();
    }

    public void init() {
        createSegment(14, 26);
        createSegment(14, 27);
        createSegment(14, 28);
    }

    public boolean move() throws CrashException {
        driver.forbidChangingDirection();
        final SnakeView head = snake.get(0);
        final int headY = driver.resolveHeadY(head.getY(), head.getX());
        final int headX = driver.resolveHeadX(head.getY(), head.getX());
        final int yToAdd = tail.getY();
        final int xToAdd = tail.getX();
        boolean skip = true;
        int previousY = head.getY();
        int previousX = head.getX();
        System.out.println("Move snake from {" + previousY + "," + previousX + "} to {" + headY + "," + headX + "}");
        final boolean isAppleEaten = moveSnakeSegment(head, headY, headX);
        gameField.catchPosition(headY, headX);
        gameField.revealPosition(tail.getY(), tail.getX());

        for (final SnakeView segment : snake) {
            if (skip) {
                skip = false;
                continue;
            }
            final int y = segment.getY();
            final int x = segment.getX();
            moveSnakeSegment(segment, previousY, previousX);
            previousY = y;
            previousX = x;
        }

        if (isAppleEaten) {
            System.out.println("Create new snake segment at {" + yToAdd + "," + xToAdd + "}");
            createSegment(yToAdd, xToAdd);
        }

        driver.allowChangingDirection();
        return isAppleEaten;
    }

    public boolean moveSnakeSegment(final SnakeView toMove, final int toY, final int toX) throws CrashException {
        final boolean needCreateNewOne = gameField.moveSnakeSegment(toMove.getY(), toMove.getX(), toY, toX);
        toMove.setY(toY).setX(toX);
        return needCreateNewOne;
    }

    private void createSegment(final int y, final int x) {
        gameField.createSnakeSegment(y, x);
        final SnakeView snakeView = factory.wrap(coreData.getSnakeSegment());
        if (snakeView == null) {
            throw new RuntimeException("Snake view pull is empty.");
        }
        snakeView.setY(y).setX(x).show();
        snake.add(snakeView);
        tail = snakeView;
        gameField.catchPosition(y, x);
    }

    public void setDirection(@NonNull final Driver.Direction direction) {
        driver.setDirection(direction);
    }

    public void hide() {
        snake.forEach(segment -> segment.hide());
    }

    public void show() {
        snake.forEach(segment -> segment.show());
    }
}
