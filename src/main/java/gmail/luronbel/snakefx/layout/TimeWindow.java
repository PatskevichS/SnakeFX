package gmail.luronbel.snakefx.layout;

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
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * TimeWindow.
 *
 * @author Stas_Patskevich
 */
@Component(TimeWindow.TIME_WINDOW_BEAN)
public class TimeWindow extends VBox {
    public static final String TIME_WINDOW_BEAN = "timeWindow";

    private static final int PADDING = 15;
    private static final int SPACING = 5;
    private static final int HEIGHT = 150;
    private static final int WIDTH = 260;

    private final Text timer;
    private final int seconds;

    public TimeWindow(@Value("${timer_in_sec}") final int seconds,
                      @Value("${window_width}") final int windowWidth, @Value("${window_height}") final int windowHeight) {
        this.seconds = seconds;

        final double layoutX = (double) (windowWidth - WIDTH) / 2;
        final double layoutY = (double) (windowHeight - HEIGHT) / 2;


        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setPadding(new Insets(PADDING));
        setSpacing(SPACING);
        setAlignment(Pos.CENTER);

        setMinWidth(WIDTH);
        setMaxWidth(WIDTH);
        setMinHeight(HEIGHT);
        setMaxHeight(HEIGHT);


        final InnerShadow innerShadow = new InnerShadow();
        final DropShadow dropShadow = new DropShadow();
        innerShadow.setInput(dropShadow);
        setEffect(innerShadow);

        final Background background = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        setBackground(background);

        final Text caption = new Text("Game will start in");
        final Font captionFont = new Font("Odibee Sans", 30);
        caption.setFont(captionFont);

        timer = new Text("3");
        final Font timerFont = new Font("Odibee Sans", 46);
        timer.setFont(timerFont);

        final Text sec = new Text("seconds");
        final Font secFont = new Font("Odibee Sans", 25);
        sec.setFont(secFont);
        sec.setOpacity(0.7);
        setVisible(false);
        getChildren().addAll(caption, timer, sec);
    }

    public void show(final Runnable toPerform) {
        final Thread thread = new Thread(toPerform);
        setVisible(true);
        thread.start();
    }

    public void show(final Runnable prepare, final Runnable start) {
        setVisible(true);
        new Thread(() -> {
            for (int i = 1; i <= seconds; i++) {
                try {
                    Thread.sleep(i * 700);
                    timer.setText("" + (seconds - i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            start.run();
        }).start();
        new Thread(prepare).start();
    }

    public void hide() {
        setVisible(false);
    }

    public boolean isShown() {
        return isVisible();
    }
}
