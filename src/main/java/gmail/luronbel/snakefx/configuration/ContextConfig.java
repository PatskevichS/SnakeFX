package gmail.luronbel.snakefx.configuration;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.lang.NonNull;

@Configuration
@ComponentScan
@PropertySource(name = "myProperties", value = "application.properties")
public class ContextConfig {
    public static final String GAME_FIELD = "gameField";
    public static final String MAIN_SCENE = "mainScene";

    @Value("${window_height}")
    private int windowHeight;

    @Value("${window_width}")
    private int windowWidth;

    @Bean(name = GAME_FIELD)
    public Parent gameField(@Value("${show_grid:false}") final boolean showGrid) {
        final Pane pane = new Pane();
        final ImageView imageView = new ImageView("/game/background.png");
        imageView.setFitHeight(windowHeight);
        imageView.setFitWidth(windowWidth);
        pane.getChildren().add(imageView);

        if (showGrid) {
            drawGrid(pane);
        }

        return pane;
    }

    @Bean(name = MAIN_SCENE)
    public Scene mainScene(@Autowired @Qualifier(GAME_FIELD) final Parent root) {
        return new Scene(root, windowWidth, windowHeight);
    }

    private void drawGrid(@NonNull final Pane parent) {
//        Vertical
        for (int i = 100; i < 750; i += 50) {
            final Line line = new Line();
            line.setStartX(i);
            line.setStartY(50);
            line.setEndX(i);
            line.setEndY(400);
            line.setStroke(Color.DARKGREEN);
            parent.getChildren().add(line);
        }
//        Horizontal
        for (int i = 100; i < 400; i += 50) {
            final Line line = new Line();
            line.setStartX(50);
            line.setStartY(i);
            line.setEndX(750);
            line.setEndY(i);
            line.setStroke(Color.DARKGREEN);
            parent.getChildren().add(line);
        }
    }
}
