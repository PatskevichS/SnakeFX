package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.layout.GameFieldLayout;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(Grid.GRID_BEAN)
public class Grid extends Group {
    public static final String GRID_BEAN = "grid";
    private final boolean showGrid;

    public Grid(@Value("${show_grid:false}") final boolean showGrid) {
        super();
//        Vertical
        for (int i = GameFieldLayout.LEFT_X; i < GameFieldLayout.RIGHT_X + GameFieldLayout.CELL; i += GameFieldLayout.CELL) {
            final Line line = new Line();
            line.setStartX(i);
            line.setStartY(GameFieldLayout.TOP_Y - GameFieldLayout.CELL);
            line.setEndX(i);
            line.setEndY(GameFieldLayout.BOTTOM_Y + GameFieldLayout.CELL);
            line.setStroke(Color.DARKGREEN);
            getChildren().add(line);
        }
//        Horizontal
        for (int i = GameFieldLayout.TOP_Y; i < GameFieldLayout.BOTTOM_Y + GameFieldLayout.CELL; i += GameFieldLayout.CELL) {
            final Line line = new Line();
            line.setStartX(GameFieldLayout.LEFT_X - GameFieldLayout.CELL);
            line.setStartY(i);
            line.setEndX(GameFieldLayout.RIGHT_X + GameFieldLayout.CELL);
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
