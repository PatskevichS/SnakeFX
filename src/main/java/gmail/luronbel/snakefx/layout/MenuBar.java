package gmail.luronbel.snakefx.layout;

import static gmail.luronbel.snakefx.layout.GameFieldLayout.BORDER;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.BOTTOM_Y;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.CELL;
import static gmail.luronbel.snakefx.layout.PointsCounter.POINTS_COUNTER_BEAN;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * MenuBar.
 *
 * @author Stas_Patskevich
 */
@Component(MenuBar.MEU_BAR_BEAN)
public class MenuBar extends HBox {
    public static final String MEU_BAR_BEAN = "menuBar";

    public MenuBar(@Qualifier(POINTS_COUNTER_BEAN) final PointsCounter pointsCounter) {
        setPadding(new Insets(0, 12, 0, 0));
        setSpacing(10);
        getChildren().add(pointsCounter);

        setLayoutY(BOTTOM_Y + +CELL + 15);
        setLayoutX(BORDER);
    }

}
