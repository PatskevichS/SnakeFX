package gmail.luronbel.snakefx.configuration;

import static gmail.luronbel.snakefx.configuration.CoreData.CORE_DATA_BEAN;
import static gmail.luronbel.snakefx.layout.Timer.TIMER_BEAN;

import gmail.luronbel.snakefx.components.Game;
import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.Snake;
import gmail.luronbel.snakefx.components.utils.Driver;
import gmail.luronbel.snakefx.components.view.Generator;
import gmail.luronbel.snakefx.components.view.apple.AppleView;
import gmail.luronbel.snakefx.components.view.obstacle.ObstacleView;
import gmail.luronbel.snakefx.components.view.snake.SnakeViewFactory;
import gmail.luronbel.snakefx.components.view.wall.WallView;
import gmail.luronbel.snakefx.layout.Timer;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * GameEngine.
 *
 * @author Stas_Patskevich
 */
@Component(GameEngine.GAME_ENGINE_BEAN)
public class GameEngine {
    public static final String GAME_ENGINE_BEAN = "gameEngine";
    private static final String ERROR_PATTERN = "Game engine error: %s.";
    private static final String INFO_PATTERN = "Game (%s): %s.";

    private final CoreData coreData;
    private final Timer timer;
    private Game currentGame;
    @Setter
    private int speed;

    @Setter
    private SnakeViewFactory snakeViewFactory;
    @Setter
    private Generator wallGenerator;
    @Setter
    private Generator obstacleGenerator;
    @Setter
    private ObstacleView obstacleView;
    @Setter
    private WallView wallView;
    @Setter
    private AppleView appleView;

    public GameEngine(@Qualifier(CORE_DATA_BEAN) final CoreData coreData,
                      @Qualifier(TIMER_BEAN) final Timer timer) {
        this.coreData = coreData;
        this.timer = timer;
    }

    public void start() {
        timer.reset();
        verify();
        coreData.reset();
        if (isInitialized() && !currentGame.isDone()) {
            currentGame.stop();
        }
        final GameField gameField = new GameField();

        wallGenerator.setView(wallView);
        wallGenerator.generate(coreData, gameField);
        obstacleGenerator.setView(obstacleView);
        obstacleGenerator.generate(coreData, gameField);
        appleView.setCoreData(coreData);
        appleView.setGameField(gameField);

        final Snake snake = new Snake(coreData, gameField, snakeViewFactory);

        currentGame = new Game(coreData, timer, snake, appleView, speed, wallGenerator, obstacleGenerator);
        currentGame.start();
        timer.start();
    }

    public void pause() {
        if (isInitialized()) {
            currentGame.pause();
            timer.stop();
        }
    }

    public void resume() {
        if (isInitialized()) {
            currentGame.resume();
            timer.start();
        }
    }

    public void stop() {
        if (isInitialized()) {
            currentGame.stop();
        }
    }

    public void changeDirection(@NonNull final Driver.Direction direction) {
        if (isInitialized()) {
            currentGame.setDirection(direction);
        }
    }

    private void verify() {
        Assert.notNull(snakeViewFactory, () -> String.format(ERROR_PATTERN, "snake view factory is not initialized"));
        Assert.notNull(wallGenerator, () -> String.format(ERROR_PATTERN, "wall generator is not initialized"));
        Assert.notNull(obstacleGenerator, () -> String.format(ERROR_PATTERN, "obstacle generator is not initialized"));
        Assert.notNull(obstacleView, () -> String.format(ERROR_PATTERN, "obstacle view is not initialized"));
        Assert.notNull(wallView, () -> String.format(ERROR_PATTERN, "wall view is not initialized"));
        Assert.notNull(appleView, () -> String.format(ERROR_PATTERN, "apple view is not initialized"));
        Assert.isTrue(speed > 10, "speed is not initialized");
    }

    public boolean isInitialized() {
        return currentGame != null;
    }
}
