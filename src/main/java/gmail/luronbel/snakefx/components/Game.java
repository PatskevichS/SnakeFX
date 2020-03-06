package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.components.utils.Driver;
import gmail.luronbel.snakefx.components.view.Generator;
import gmail.luronbel.snakefx.components.view.apple.AppleView;
import gmail.luronbel.snakefx.configuration.CoreData;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class Game {
    private boolean isDone = false;
    private boolean isPaused = false;
    private final Snake snake;
    private final AppleView apple;
    private final CoreData coreData;
    @Getter
    private final long id;

    private int stepsBetweenApple = 0;

    public Game(@NonNull final CoreData coreData, final Snake snake, final AppleView apple,
                final Generator... generators) {
        this.coreData = coreData;
        this.snake = snake;
        this.apple = apple;
        id = System.currentTimeMillis();
    }

    public void start() {
        snake.show();
        apple.show();
        final Thread game = new Thread(() -> {
            while (!isDone) {
                try {
                    if (!isPaused) {
                        if (snake.move()) {
                            final int points = 15 - ((int) (stepsBetweenApple * 0.2));
                            coreData.addPoints(points < 0 ? 5 : points);
                            apple.show();
                            stepsBetweenApple = 0;
                        } else {
                            stepsBetweenApple++;
                        }
                    }
                    Thread.sleep(100);
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

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public void setDirection(@NonNull final Driver.Direction direction) {
        if (!isPaused) {
            snake.setDirection(direction);
        }
    }

    public boolean isDone() {
        return isDone;
    }
}
