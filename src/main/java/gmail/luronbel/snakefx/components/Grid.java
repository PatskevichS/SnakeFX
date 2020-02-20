package gmail.luronbel.snakefx.components;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(Grid.GRID_BEAN)
public class Grid extends Group {
    public static final String GRID_BEAN = "grid";
    public static final int STEP = 25;
    private final boolean showGrid;

    public Grid(@Value("${show_grid:false}") final boolean showGrid) {
        super();
//        Vertical
        for (int i = 75; i < 750; i += STEP) {
            final Line line = new Line();
            line.setStartX(i);
            line.setStartY(50);
            line.setEndX(i);
            line.setEndY(400);
            line.setStroke(Color.DARKGREEN);
            getChildren().add(line);
        }
//        Horizontal
        for (int i = 75; i < 400; i += STEP) {
            final Line line = new Line();
            line.setStartX(50);
            line.setStartY(i);
            line.setEndX(750);
            line.setEndY(i);
            line.setStroke(Color.DARKGREEN);
            getChildren().add(line);
        }
        setVisible(showGrid);
        this.showGrid = showGrid;
    }

    public void changeVisibility() {
        if (showGrid) {
            setVisible(!isVisible());
        }
    }
}
