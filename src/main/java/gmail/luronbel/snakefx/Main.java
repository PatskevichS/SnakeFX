package gmail.luronbel.snakefx;

import gmail.luronbel.snakefx.configuration.ContextConfig;
import gmail.luronbel.snakefx.configuration.Core;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static gmail.luronbel.snakefx.configuration.Core.CORE_BEAN;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);
        context.getBean(CORE_BEAN, Core.class).start(primaryStage);
    }
}
