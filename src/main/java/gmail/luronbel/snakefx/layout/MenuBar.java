package gmail.luronbel.snakefx.layout;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.BORDER;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.BOTTOM_Y;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.CELL;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.RIGHT_X;
import static gmail.luronbel.snakefx.layout.PointsCounter.POINTS_COUNTER_BEAN;
import static gmail.luronbel.snakefx.layout.Timer.TIMER_BEAN;

import javafx.scene.Group;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * MenuBar.
 *
 * @author Stas_Patskevich
 */
@Component(MenuBar.MEU_BAR_BEAN)
public class MenuBar extends Group {
    public static final String MEU_BAR_BEAN = "menuBar";

    public MenuBar(@Qualifier(POINTS_COUNTER_BEAN) final PointsCounter pointsCounter,
                   @Qualifier(TIMER_BEAN) final Timer timer) {
        getChildren().addAll(pointsCounter, timer);

        pointsCounter.setY(BOTTOM_Y + CELL + 30);
        pointsCounter.setX(BORDER);

        timer.setY(BOTTOM_Y + CELL + 30);
        timer.setX(RIGHT_X - CELL * 3);
    }

}
