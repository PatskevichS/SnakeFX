package gmail.luronbel.snakefx.layout;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

/**
 * PointsCounter.
 *
 * @author Stas_Patskevich
 */
@Component(PointsCounter.POINTS_COUNTER_BEAN)
public class PointsCounter extends Text {
    public static final String POINTS_COUNTER_BEAN = "pointsCounter";
    private static final String TEMPLATE = "Points: %s";
    private long points = 0;

    public PointsCounter() {
        final Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);

        final DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(254, 235, 66, 0.3));
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        ds.setRadius(5);
        ds.setSpread(0.2);

        blend.setBottomInput(ds);

        final DropShadow ds1 = new DropShadow();
        ds1.setColor(Color.web("#f13a00"));
        ds1.setRadius(20);
        ds1.setSpread(0.2);

        final Blend blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);

        final InnerShadow is = new InnerShadow();
        is.setColor(Color.web("#feeb42"));
        is.setRadius(9);
        is.setChoke(0.8);
        blend2.setBottomInput(is);

        final InnerShadow is1 = new InnerShadow();
        is1.setColor(Color.web("#f13a00"));
        is1.setRadius(5);
        is1.setChoke(0.4);
        blend2.setTopInput(is1);

        final Blend blend1 = new Blend();
        blend1.setMode(BlendMode.MULTIPLY);
        blend1.setBottomInput(ds1);
        blend1.setTopInput(blend2);

        blend.setTopInput(blend1);
        setEffect(blend);
        setFont(Font.font(null, FontWeight.BOLD, 20));
        addPoints(0);
    }

    public void addPoints(final long pointsToAdd) {
        points += pointsToAdd;
        setText(String.format(TEMPLATE, points));
    }
}
