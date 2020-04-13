package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.components.utils.Driver;
import gmail.luronbel.snakefx.components.view.Generator;
import gmail.luronbel.snakefx.components.view.apple.AppleView;
import gmail.luronbel.snakefx.configuration.CoreData;
import gmail.luronbel.snakefx.layout.Timer;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class Game {
    private boolean isDone = false;
    private boolean isPaused = false;
    private final Snake snake;
    private final AppleView apple;
    private final CoreData coreData;
    private final Timer timer;
    @Getter
    private final long id;
    private final int speed;

    private int stepsBetweenApple = 0;

    public Game(@NonNull final CoreData coreData, @NonNull final Timer timer,
                final Snake snake, final AppleView apple, final int speed, final Generator... generators) {
        this.coreData = coreData;
        this.snake = snake;
        this.apple = apple;
        this.speed = speed;
        this.timer = timer;
        id = System.currentTimeMillis() % 1000000;
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
                    Thread.sleep(speed);
                } catch (final Exception ex) {
                    timer.stop();
                    return;
                }
            }
        });

        game.start();
    }

    public void stop() {
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
