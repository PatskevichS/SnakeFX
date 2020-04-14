package gmail.luronbel.snakefx.layout;

import static gmail.luronbel.snakefx.layout.PointsCounter.POINTS_COUNTER_BEAN;
import static gmail.luronbel.snakefx.layout.Timer.TIMER_BEAN;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Notification.
 *
 * @author Stas_Patskevich
 */
@Component(Notification.NOTIFICATION_BEAN)
public class Notification extends VBox {
    public static final String NOTIFICATION_BEAN = "notification";

    private static final String SCORE_TEMPLATE = "Score: %s";
    private static final String TIME_TEMPLATE = "Time: %s.%s";

    private static final int PADDING = 25;
    private static final int SPACING = 10;
    private static final int FONT_SIZE = 25;
    private static final String FONT_NAME = "times new roman";
    private static final int TEXT_WIDTH = 120;

    private final Timer timer;
    private final PointsCounter pointsCounter;

    private final Text score;
    private final Text time;

    public Notification(@Value("${window_width}") final int windowWidth, @Value("${window_height}") final int windowHeight,
                        @Qualifier(POINTS_COUNTER_BEAN) final PointsCounter pointsCounter,
                        @Qualifier(TIMER_BEAN) final Timer timer) {
        this.timer = timer;
        this.pointsCounter = pointsCounter;

        final int menuWidth = TEXT_WIDTH + (PADDING * 2);
        final double layoutX = (double) (windowWidth - menuWidth) / 2;

        final int menuHeight = (FONT_SIZE * 2) + (PADDING * 2) + (SPACING * 2);
        final double layoutY = (double) (windowHeight - menuHeight) / 2;

        setLayoutY(layoutY);
        setLayoutX(layoutX);
        setPadding(new Insets(PADDING));
        setSpacing(SPACING);
        setAlignment(Pos.CENTER);

        final javafx.scene.layout.Background background = new Background(
                new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));

        setBackground(background);
        final InnerShadow innerShadow = new InnerShadow();
        final DropShadow dropShadow = new DropShadow();
        innerShadow.setInput(dropShadow);
        setEffect(innerShadow);

        score = new Text();
        score.setEffect(new InnerShadow());
        score.setFont(Font.font(FONT_NAME, FontWeight.BOLD, FONT_SIZE));
        score.setFill(Color.BLACK);
        score.setWrappingWidth(TEXT_WIDTH);
        score.setTextAlignment(TextAlignment.CENTER);

        time = new Text();
        time.setEffect(new InnerShadow());
        time.setFont(Font.font(FONT_NAME, FontWeight.BOLD, FONT_SIZE));
        time.setFill(Color.BLACK);
        time.setWrappingWidth(TEXT_WIDTH);
        time.setTextAlignment(TextAlignment.CENTER);

        getChildren().addAll(score, time);
        show();
        hide();
    }

    public void show() {
        score.setText(String.format(SCORE_TEMPLATE, pointsCounter.getPoints()));
        time.setText(String.format(TIME_TEMPLATE, timer.getMin(), timer.getSec()));
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }
}
