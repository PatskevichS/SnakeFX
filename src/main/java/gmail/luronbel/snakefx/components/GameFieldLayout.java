package gmail.luronbel.snakefx.components;

import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static gmail.luronbel.snakefx.components.Background.BACKGROUND_BEAN;
import static gmail.luronbel.snakefx.components.GameElements.GAME_ELEMENTS_BEAN;
import static gmail.luronbel.snakefx.components.Grid.GRID_BEAN;

@Component(GameFieldLayout.GAME_FIELD_LAYOUT_BEAN)
public class GameFieldLayout extends Pane {
    public static final String GAME_FIELD_LAYOUT_BEAN = "gameFieldLayout";
    public static final double RADIUS = 12.5;
    public static final double CELL = RADIUS * 2;
    public static final int TOP_Y = 75;
    public static final int BOTTOM_Y = 375;
    public static final int LEFT_X = 75;
    public static final int RIGHT_X = 725;
    public static final int X = RIGHT_X - (int) CELL;
    public static final int Y = BOTTOM_Y - (int) CELL;

    private final Grid grid;
    private final Background background;

    @Autowired
    public GameFieldLayout(@Qualifier(GAME_ELEMENTS_BEAN) final GameElements gameElements,
                           @Qualifier(GRID_BEAN) final Grid grid, @Qualifier(BACKGROUND_BEAN) final Background background) {
        super();
        getChildren().add(background);
        getChildren().add(grid);
        getChildren().add(gameElements);
        this.grid = grid;
        this.background = background;
    }

    public void changeGridVisibility() {
        grid.changeVisibility();
    }

}
