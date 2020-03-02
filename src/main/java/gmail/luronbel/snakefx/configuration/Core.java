package gmail.luronbel.snakefx.configuration;

import static gmail.luronbel.snakefx.components.utils.Driver.Direction.BOTTOM;
import static gmail.luronbel.snakefx.components.utils.Driver.Direction.LEFT;
import static gmail.luronbel.snakefx.components.utils.Driver.Direction.RIGHT;
import static gmail.luronbel.snakefx.components.utils.Driver.Direction.TOP;
import static gmail.luronbel.snakefx.configuration.CoreData.CORE_DATA_BEAN;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.GAME_FIELD_LAYOUT_BEAN;
import static gmail.luronbel.snakefx.layout.MainMenu.MAIN_MENU_BEAN;
import static gmail.luronbel.snakefx.layout.TimeWindow.TIME_WINDOW_BEAN;

import gmail.luronbel.snakefx.components.Game;
import gmail.luronbel.snakefx.components.view.obstacle.SimpleObstacle;
import gmail.luronbel.snakefx.components.view.obstacle.SimpleObstacleGenerator;
import gmail.luronbel.snakefx.components.view.snake.SimpleSnakeViewFactory;
import gmail.luronbel.snakefx.components.view.wall.Random;
import gmail.luronbel.snakefx.components.view.wall.SimpleWall;
import gmail.luronbel.snakefx.layout.GameFieldLayout;
import gmail.luronbel.snakefx.layout.MainMenu;
import gmail.luronbel.snakefx.layout.TimeWindow;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component(Core.CORE_BEAN)
public class Core {
    public static final String CORE_BEAN = "core";

    @Value("${app_name}")
    private String appName;

    @Value("${app_version}")
    private String appVersion;

    @Value("${window_height}")
    private int windowHeight;

    @Value("${window_width}")
    private int windowWidth;

    private Game currentGame;

    @Autowired
    @Qualifier(GAME_FIELD_LAYOUT_BEAN)
    private GameFieldLayout gameFieldLayout;

    @Autowired
    @Qualifier(CORE_DATA_BEAN)
    private CoreData coreData;

    @Autowired
    @Qualifier(MAIN_MENU_BEAN)
    private MainMenu mainMenu;

    @Autowired
    @Qualifier(TIME_WINDOW_BEAN)
    private TimeWindow timeWindow;

    private double xOffset = 0;
    private double yOffset = 0;

    public void start(@NonNull final Stage primaryStage) {
        final Scene mainScene = new Scene(gameFieldLayout, windowWidth, windowHeight);
        mainScene.setOnKeyPressed(event -> {
            final KeyCode k = event.getCode();
            switch (k) {
                case D:
                    currentGame.setDirection(RIGHT);
                    break;
                case A:
                    currentGame.setDirection(LEFT);
                    break;
                case S:
                    currentGame.setDirection(BOTTOM);
                    break;
                case W:
                    currentGame.setDirection(TOP);
                    break;
                case ESCAPE:
                    pauseGame();
                    break;
            }
        });

        gameFieldLayout.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();

        });
        gameFieldLayout.setOnMouseReleased(event -> {
            if (!mainMenu.isShown() && !timeWindow.isShown()) {
                resumeCurrentGame();
                gameFieldLayout.hideModalView();
            }
        });
        gameFieldLayout.setOnMouseDragged(event -> {
            if (!gameFieldLayout.isModalViewShown() && !timeWindow.isShown()) {
                pauseCurrentGame();
                gameFieldLayout.showModalView();
            }
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        primaryStage.setTitle(appName + " " + appVersion);
        primaryStage.setMaxWidth(windowWidth);
        primaryStage.setMaxHeight(windowHeight + 28);
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.setOnCloseRequest(event -> stopCurrentGame());
        primaryStage.iconifiedProperty().addListener((ov, t, t1) -> {
            if (t1) {
                pauseGame();
            }
        });
        primaryStage.show();
        mainMenu.setExitButtonAction(event ->
        {
            stopCurrentGame();
            primaryStage.close();
        });
        mainMenu.setNewGameButtonAction(event ->
        {
            stopCurrentGame();
            currentGame = new Game(coreData, new SimpleSnakeViewFactory(), new Random(new SimpleWall()),
                    new SimpleObstacleGenerator(new SimpleObstacle()));
            mainMenu.hide();
            gameFieldLayout.hideModalView();
            currentGame.start();
        });
        mainMenu.setResumeButtonAction(event ->
        {
            mainMenu.hide();
            gameFieldLayout.hideModalView();
            currentGame.resume();
        });
        gameFieldLayout.showModalView();
        mainMenu.show(currentGame != null);
    }

    private void pauseGame() {
        if (currentGame != null) {
            if (gameFieldLayout.isModalViewShown()) {
                gameFieldLayout.hideModalView();
                resumeCurrentGame();
                mainMenu.hide();
            } else {
                pauseCurrentGame();
                gameFieldLayout.showModalView();
                mainMenu.show(currentGame != null);
            }
        }
    }

    private void stopCurrentGame() {
        if (currentGame != null) {
            currentGame.stop();
            coreData.reset();
            System.out.println("Game is stopped");
        }
    }

    private void pauseCurrentGame() {
        if (currentGame != null) {
            currentGame.pause();
            System.out.println("Game is paused");
        }
    }

    private void resumeCurrentGame() {
        if (currentGame != null) {
            currentGame.resume();
            System.out.println("Game is resumed");
        }
    }
}
