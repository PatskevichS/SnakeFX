package gmail.luronbel.snakefx.configuration;

import gmail.luronbel.snakefx.components.Game;
import gmail.luronbel.snakefx.layout.GameElementsGroup;
import gmail.luronbel.snakefx.layout.GameFieldLayout;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static gmail.luronbel.snakefx.configuration.Direction.*;
import static gmail.luronbel.snakefx.layout.GameElementsGroup.GAME_ELEMENTS_BEAN;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.GAME_FIELD_LAYOUT_BEAN;

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
    @Qualifier(GAME_ELEMENTS_BEAN)
    private GameElementsGroup gameElementsGroup;

    public void start(@NonNull final Stage primaryStage) {
        currentGame = new Game(gameElementsGroup, false);
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
            }
        });

        primaryStage.setTitle(appName + " " + appVersion);
        primaryStage.setMaxWidth(windowWidth);
        primaryStage.setMaxHeight(windowHeight + 28);
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.setOnCloseRequest(event -> currentGame.stop());
        primaryStage.show();
        currentGame.start();
    }
}
