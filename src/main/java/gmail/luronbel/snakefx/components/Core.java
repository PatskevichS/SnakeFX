package gmail.luronbel.snakefx.components;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static gmail.luronbel.snakefx.components.Game.GAME_BEAN;
import static gmail.luronbel.snakefx.components.GameFieldLayout.GAME_FIELD_LAYOUT_BEAN;
import static gmail.luronbel.snakefx.configuration.Direction.*;

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
    @Qualifier(GAME_BEAN)
    private Game game;

    @Autowired
    @Qualifier(GAME_FIELD_LAYOUT_BEAN)
    private GameFieldLayout gameFieldLayout;

    public void start(@NonNull final Stage primaryStage) {
        final Scene mainScene = new Scene(gameFieldLayout, windowWidth, windowHeight);
        mainScene.setOnKeyPressed(event -> {
            final KeyCode k = event.getCode();
            switch (k) {
                case D:
                    game.setDirection(RIGHT);
                    break;
                case A:
                    game.setDirection(LEFT);
                    break;
                case S:
                    game.setDirection(BOTTOM);
                    break;
                case W:
                    game.setDirection(TOP);
                    break;
            }
        });

        primaryStage.setTitle(appName + " " + appVersion);
        primaryStage.setMaxWidth(windowWidth);
        primaryStage.setMaxHeight(windowHeight + 28);
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.setOnCloseRequest(event -> game.stop());
        primaryStage.show();
        game.start();
    }
}
