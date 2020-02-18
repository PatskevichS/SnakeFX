package gmail.luronbel.snakefx.configuration;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static gmail.luronbel.snakefx.configuration.ContextConfig.MAIN_SCENE;

@Component(Core.CORE_BEAN)
public class Core {
    public static final String CORE_BEAN = "core";

    @Value("${window_caption}")
    private String windowCaption;

    @Autowired
    @Qualifier(MAIN_SCENE)
    private Scene mainScene;

    public void start(@NonNull final Stage primaryStage) {
        primaryStage.setTitle(windowCaption);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
