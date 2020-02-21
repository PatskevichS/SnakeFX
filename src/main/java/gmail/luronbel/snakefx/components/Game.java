package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.configuration.Direction;
import gmail.luronbel.snakefx.layout.GameElementsGroup;
import org.springframework.lang.NonNull;

public class Game {
    private boolean isDone = false;
    private final boolean isPaused = false;
    private final GameField gameField;
    private final Snake snake;

    public Game(@NonNull final GameElementsGroup gameElementsGroup, final boolean withWalls) {
        gameField = new GameField(withWalls);
        snake = new Snake(gameElementsGroup, gameField);
    }

    public void start() {
        snake.init();

        final Thread game = new Thread(() -> {
            while (!isDone && !isPaused) {
                try {
                    snake.move();
                    Thread.sleep(200);
                } catch (final RuntimeException ex) {
                    System.out.println("Snake crashed");
                    stop();
                    throw new RuntimeException(ex);
                } catch (final Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        game.start();
        System.out.println("Game has been started");
    }

    public void stop() {
        System.out.println("Game has been stopped");
        isDone = true;
    }

    public void setDirection(@NonNull final Direction direction) {
        snake.setDirection(direction);
    }
}
