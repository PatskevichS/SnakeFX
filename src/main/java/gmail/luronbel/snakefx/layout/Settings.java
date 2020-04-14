package gmail.luronbel.snakefx.layout;

import gmail.luronbel.snakefx.components.view.Empty;
import gmail.luronbel.snakefx.components.view.Generator;
import gmail.luronbel.snakefx.components.view.apple.SimpleApple;
import gmail.luronbel.snakefx.components.view.obstacle.ObstacleGenerator;
import gmail.luronbel.snakefx.components.view.obstacle.SimpleObstacle;
import gmail.luronbel.snakefx.components.view.snake.SimpleSnake;
import gmail.luronbel.snakefx.components.view.snake.SnakeViewFactory;
import gmail.luronbel.snakefx.components.view.wall.WallGenerator;
import gmail.luronbel.snakefx.components.view.wall.impl.SimpleWall;
import gmail.luronbel.snakefx.configuration.GameEngine;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * GameConfiguration.
 *
 * @author Stas_Patskevich
 */
@Component(Settings.GAME_CONFIGURATION_BEAN)
public class Settings extends VBox {
    public static final String GAME_CONFIGURATION_BEAN = "gameConfiguration";
    private static final int PADDING = 25;
    private static final int SPACING = 10;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BUTTON_WIDTH = 160;
    private static final int FONT_SIZE = 20;
    private static final String FONT_NAME = "times new roman";

    private final Button backButton;
    private final Empty empty;
    private final SnakeViewFactory defaultSnake;

    private final Font font = new Font(FONT_NAME, FONT_SIZE);

    public Settings(@Autowired final GameEngine gameEngine,
                    @Autowired final Empty empty,
                    @Autowired final List<WallGenerator> wallGenerators,
                    @Autowired final List<ObstacleGenerator> obstacleGenerators,
                    @Autowired final List<SnakeViewFactory> snake,
                    @Value("${window_width}") final int windowWidth,
                    @Value("${window_height}") final int windowHeight) {
        this.empty = empty;
        setVisible(false);
        final Background background = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        setBackground(background);
        final InnerShadow innerShadow = new InnerShadow();
        final DropShadow dropShadow = new DropShadow();
        innerShadow.setInput(dropShadow);
        setEffect(innerShadow);
        defaultSnake = new SimpleSnake();

        final int menuWidth = BUTTON_WIDTH + (PADDING * 2);
        final double layoutX = (double) (windowWidth - menuWidth) / 2;

        final int menuHeight = (BUTTON_HEIGHT * 4) + (FONT_SIZE * 4) + (PADDING * 2) + (SPACING * 11);
        final double layoutY = (double) (windowHeight - menuHeight) / 2;

        setLayoutY(layoutY);
        setLayoutX(layoutX);
        setPadding(new Insets(PADDING));
        setSpacing(SPACING);

        final int arc = (BUTTON_HEIGHT / 4) * 3;
        final Rectangle rectangle = new Rectangle(BUTTON_WIDTH, BUTTON_HEIGHT);
        rectangle.setArcHeight(arc);
        rectangle.setArcWidth(arc);
        rectangle.setFill(Color.DARKGRAY);
        setAlignment(Pos.CENTER);

        backButton = new Button("Back");
        backButton.setFont(font);
        backButton.setShape(rectangle);
        backButton.setMinHeight(BUTTON_HEIGHT);
        backButton.setMinWidth(BUTTON_WIDTH);
        backButton.setEffect(dropShadow);

        setupComboBoxView("Wall generator", wallGenerators, value -> {
            if (value.isPresent()) {
                gameEngine.setWallGenerator(value.get());
            } else {
                gameEngine.setWallGenerator(empty);
            }
        });

        setupComboBoxView("Obstacle generator", obstacleGenerators, value -> {
            if (value.isPresent()) {
                gameEngine.setObstacleGenerator(value.get());
            } else {
                gameEngine.setObstacleGenerator(empty);
            }
        });

        final Text snakeViewCaption = new Text("Snake view");
        snakeViewCaption.setEffect(new InnerShadow());
        snakeViewCaption.setFont(font);
        final ComboBox<String> snakeComboBox = new ComboBox<>();
        final List<String> itemsSnakes = snake.stream().map(bean -> bean.getClass().getSimpleName()).collect(Collectors.toList());
        snakeComboBox.setItems(FXCollections.observableArrayList(itemsSnakes));

        snakeComboBox.getSelectionModel().select(defaultSnake.getClass().getSimpleName());

        snakeComboBox.setOnAction(event -> {
            final Optional<SnakeViewFactory> value = snake.stream()
                    .filter(e -> e.getClass().getSimpleName().equals(snakeComboBox.getValue())).findFirst();
            if (value.isPresent()) {
                gameEngine.setSnakeViewFactory(value.get());
            } else {
                gameEngine.setSnakeViewFactory(defaultSnake);
            }
        });

        final Text gameSpeedCaption = new Text("Game speed");
        gameSpeedCaption.setEffect(new InnerShadow());
        gameSpeedCaption.setFont(font);
        final Slider slider = new Slider(50, 150, 100);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.setSnapToTicks(true);
        slider.valueProperty().addListener((changed, oldValue, newValue) -> gameEngine.setSpeed(200 - newValue.intValue()));
        gameEngine.setSpeed(100);

        getChildren().addAll(snakeViewCaption, snakeComboBox, gameSpeedCaption, slider, backButton);

        gameEngine.setWallGenerator(empty);
        gameEngine.setObstacleGenerator(empty);
        gameEngine.setObstacleView(new SimpleObstacle());
        gameEngine.setWallView(new SimpleWall());
        gameEngine.setAppleView(new SimpleApple());
        gameEngine.setSnakeViewFactory(defaultSnake);
    }

    private <T extends Generator> void setupComboBoxView(final String caption, final List<T> generators,
                                                         final Consumer<Optional<T>> action) {
        final Text captionView = new Text(caption);
        captionView.setFont(font);
        captionView.setEffect(new InnerShadow());
        final ComboBox<String> comboBoxView = new ComboBox<>();
        comboBoxView.minWidth(BUTTON_WIDTH);
        comboBoxView.maxWidth(BUTTON_WIDTH);

        final List<String> itemsWalls = generators.stream().map(bean -> bean.getClass().getSimpleName()).collect(Collectors.toList());
        itemsWalls.add(empty.getClass().getSimpleName());
        comboBoxView.setItems(FXCollections.observableArrayList(itemsWalls));

        comboBoxView.getSelectionModel().select(empty.getClass().getSimpleName());

        comboBoxView.setOnAction(event -> {
            final Optional<T> value = generators.stream()
                    .filter(e -> e.getClass().getSimpleName().equals(comboBoxView.getValue())).findFirst();
            action.accept(value);
        });
        getChildren().addAll(captionView, comboBoxView);
    }

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

    public boolean isShown() {
        return isVisible();
    }

    public void setBackButtonAction(final EventHandler<? super MouseEvent> action) {
        backButton.setOnMouseClicked(action);
    }
}
