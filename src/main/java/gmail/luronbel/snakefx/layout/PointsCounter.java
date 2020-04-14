package gmail.luronbel.snakefx.layout;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * PointsCounter.
 *
 * @author Stas_Patskevich
 */
@Component(PointsCounter.POINTS_COUNTER_BEAN)
public class PointsCounter extends Text {
    public static final String POINTS_COUNTER_BEAN = "pointsCounter";
    private static final String FONT_NAME = "times new roman";
    private static final String TEMPLATE = "Points: %s";
    @Getter
    private long points = 0;

    public PointsCounter() {
        final InnerShadow innerShadow = new InnerShadow();
        final DropShadow dropShadow = new DropShadow();
        innerShadow.setInput(dropShadow);
        setEffect(innerShadow);
        setFont(Font.font(FONT_NAME, FontWeight.BOLD, 20));
        setFill(Color.WHITE);
        addPoints(0);
    }

    public void addPoints(final long pointsToAdd) {
        points += pointsToAdd;
        setText(String.format(TEMPLATE, points));
    }

    public void reset() {
        points = 0;
        setText(String.format(TEMPLATE, points));
    }
}
