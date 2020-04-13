package gmail.luronbel.snakefx.configuration;

import static gmail.luronbel.snakefx.components.utils.Driver.Direction.BOTTOM;
import static gmail.luronbel.snakefx.components.utils.Driver.Direction.LEFT;
import static gmail.luronbel.snakefx.components.utils.Driver.Direction.RIGHT;
import static gmail.luronbel.snakefx.components.utils.Driver.Direction.TOP;
import static gmail.luronbel.snakefx.configuration.CoreData.CORE_DATA_BEAN;
import static gmail.luronbel.snakefx.configuration.GameEngine.GAME_ENGINE_BEAN;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.GAME_FIELD_LAYOUT_BEAN;
import static gmail.luronbel.snakefx.layout.MainMenu.MAIN_MENU_BEAN;
import static gmail.luronbel.snakefx.layout.Settings.GAME_CONFIGURATION_BEAN;

import gmail.luronbel.snakefx.layout.GameFieldLayout;
import gmail.luronbel.snakefx.layout.MainMenu;
import gmail.luronbel.snakefx.layout.Settings;
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
    @Qualifier(GAME_ENGINE_BEAN)
    private GameEngine gameEngine;

    @Autowired
    @Qualifier(GAME_CONFIGURATION_BEAN)
    private Settings settings;

    private double xOffset = 0;
    private double yOffset = 0;

    public void start(@NonNull final Stage primaryStage) {
        final Scene mainScene = new Scene(gameFieldLayout, windowWidth, windowHeight);
        mainScene.setOnKeyPressed(event -> {
            final KeyCode k = event.getCode();
            switch (k) {
                case D:
                    gameEngine.changeDirection(RIGHT);
                    break;
                case A:
                    gameEngine.changeDirection(LEFT);
                    break;
                case S:
                    gameEngine.changeDirection(BOTTOM);
                    break;
                case W:
                    gameEngine.changeDirection(TOP);
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
            if (!mainMenu.isShown() && !settings.isShown()) {
                resumeCurrentGame();
                gameFieldLayout.hideModalView();
            }
        });
        gameFieldLayout.setOnMouseDragged(event -> {
            if (!gameFieldLayout.isModalViewShown()) {
                pauseCurrentGame();
                gameFieldLayout.showModalView();
            }
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        settings.setBackButtonAction(event -> {
            settings.hide();
            mainMenu.show(gameEngine.isInitialized());
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
        mainMenu.setSettingsButtonAction(event ->
        {
            mainMenu.hide();
            settings.show();
        });
        mainMenu.setNewGameButtonAction(event ->
        {
            mainMenu.hide();
            gameFieldLayout.hideModalView();
            gameEngine.start();
        });
        mainMenu.setResumeButtonAction(event ->
        {
            mainMenu.hide();
            gameFieldLayout.hideModalView();
            gameEngine.resume();
        });
        gameFieldLayout.showModalView();
        mainMenu.show(gameEngine.isInitialized());
    }

    private void pauseGame() {
        if (gameEngine.isInitialized()) {
            if (gameFieldLayout.isModalViewShown()) {
                gameFieldLayout.hideModalView();
                resumeCurrentGame();
                mainMenu.hide();
            } else {
                pauseCurrentGame();
                gameFieldLayout.showModalView();
                mainMenu.show(gameEngine.isInitialized());
            }
        }
    }

    private void stopCurrentGame() {
        gameEngine.stop();
    }

    private void pauseCurrentGame() {
        gameEngine.pause();
    }

    private void resumeCurrentGame() {
        gameEngine.resume();
    }
}
