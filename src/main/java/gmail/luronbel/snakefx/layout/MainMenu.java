package gmail.luronbel.snakefx.layout;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * MainMenu.
 *
 * @author Stas_Patskevich
 */
@Component(MainMenu.MAIN_MENU_BEAN)
public class MainMenu extends VBox {
    public static final String MAIN_MENU_BEAN = "mainMenu";

    private static final int PADDING = 25;
    private static final int SPACING = 10;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BUTTON_WIDTH = 160;
    private static final int FONT_SIZE = 16;
    private static final int VERSION_FONT_SIZE = 10;
    private static final int NAME_FONT_SIZE = 46;
    private static final String FONT_NAME = "times new roman";

    private final String appName;
    private final String appVersion;

    private final Button exitButton;
    private final Button newGameButton;
    private final Button resumeButton;
    private final Button settingsButton;
    private final Button aboutButton;

    public MainMenu(@Value("${app_name}") final String appName, @Value("${app_version}") final String appVersion,
                    @Value("${window_width}") final int windowWidth, @Value("${window_height}") final int windowHeight) {
        this.appName = appName;
        this.appVersion = appVersion;

        final int menuWidth = BUTTON_WIDTH + (PADDING * 2);
        final double layoutX = (double) (windowWidth - menuWidth) / 2;

        final int menuHeight = (BUTTON_HEIGHT * 5) + (PADDING * 2) + (SPACING * 8) + NAME_FONT_SIZE + VERSION_FONT_SIZE;
        final double layoutY = (double) (windowHeight - menuHeight) / 2;

        setLayoutY(layoutY);
        setLayoutX(layoutX);
        setPadding(new Insets(PADDING));
        setSpacing(SPACING);
        setAlignment(Pos.CENTER);

        final Background background = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));

        setBackground(background);
        final InnerShadow innerShadow = new InnerShadow();
        final DropShadow dropShadow = new DropShadow();
        innerShadow.setInput(dropShadow);
        setEffect(innerShadow);


        final int arc = (BUTTON_HEIGHT / 4) * 3;
        final Rectangle rectangle = new Rectangle(BUTTON_WIDTH, BUTTON_HEIGHT);
        rectangle.setArcHeight(arc);
        rectangle.setArcWidth(arc);
        rectangle.setFill(Color.DARKGRAY);

        final Font font = new Font(FONT_NAME, FONT_SIZE);

        newGameButton = new Button("New game");
        newGameButton.setFont(font);
        newGameButton.setShape(rectangle);
        newGameButton.setMinHeight(BUTTON_HEIGHT);
        newGameButton.setMinWidth(BUTTON_WIDTH);
        newGameButton.setEffect(dropShadow);

        resumeButton = new Button("Resume");
        resumeButton.setFont(font);
        resumeButton.setShape(rectangle);
        resumeButton.setMinHeight(BUTTON_HEIGHT);
        resumeButton.setMinWidth(BUTTON_WIDTH);
        resumeButton.setEffect(dropShadow);

        exitButton = new Button("Exit");
        exitButton.setFont(font);
        exitButton.setShape(rectangle);
        exitButton.setMinHeight(BUTTON_HEIGHT);
        exitButton.setMinWidth(BUTTON_WIDTH);
        exitButton.setEffect(dropShadow);

        settingsButton = new Button("Settings");
        settingsButton.setFont(font);
        settingsButton.setShape(rectangle);
        settingsButton.setMinHeight(BUTTON_HEIGHT);
        settingsButton.setMinWidth(BUTTON_WIDTH);
        settingsButton.setEffect(dropShadow);

        aboutButton = new Button("About");
        aboutButton.setFont(font);
        aboutButton.setShape(rectangle);
        aboutButton.setMinHeight(BUTTON_HEIGHT);
        aboutButton.setMinWidth(BUTTON_WIDTH);
        aboutButton.setEffect(dropShadow);

        final Text name = new Text(appName);
        final Font nameFont = new Font("Odibee Sans", NAME_FONT_SIZE);
        name.setFont(nameFont);
        final Text version = new Text(appVersion);
        final Font versionFont = new Font("Odibee Sans", VERSION_FONT_SIZE);
        version.setFont(versionFont);
        version.setOpacity(0.7);

        getChildren().addAll(name, version, resumeButton, newGameButton, settingsButton, aboutButton, exitButton);
        hide();
    }


    public void setSettingsButtonAction(final EventHandler<? super MouseEvent> action) {
        settingsButton.setOnMouseClicked(action);
    }

    public void setExitButtonAction(final EventHandler<? super MouseEvent> action) {
        exitButton.setOnMouseClicked(action);
    }

    public void setNewGameButtonAction(final EventHandler<? super MouseEvent> action) {
        newGameButton.setOnMouseClicked(action);
    }

    public void setResumeButtonAction(final EventHandler<? super MouseEvent> action) {
        resumeButton.setOnMouseClicked(action);
    }

    public void show(final boolean isGameCreated) {
        if (!isGameCreated) {
            resumeButton.setDisable(true);
        } else {
            resumeButton.setDisable(false);
        }
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

    public boolean isShown() {
        return isVisible();
    }
}
