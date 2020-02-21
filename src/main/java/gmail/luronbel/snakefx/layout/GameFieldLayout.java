package gmail.luronbel.snakefx.layout;

import gmail.luronbel.snakefx.components.Grid;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static gmail.luronbel.snakefx.components.Grid.GRID_BEAN;
import static gmail.luronbel.snakefx.layout.Background.BACKGROUND_BEAN;
import static gmail.luronbel.snakefx.layout.GameElementsGroup.GAME_ELEMENTS_BEAN;

@Component(GameFieldLayout.GAME_FIELD_LAYOUT_BEAN)
public class GameFieldLayout extends Pane {
    public static final String GAME_FIELD_LAYOUT_BEAN = "gameFieldLayout";
    public static final double RADIUS = 12.5;
    public static final int CELL = (int) (RADIUS * 2);
    public static final int BORDER = 50;
    public static final int TOP_Y = 75;
    public static final int BOTTOM_Y = 375;
    public static final int LEFT_X = 75;
    public static final int RIGHT_X = 725;
    public static final int X = (int) ((RIGHT_X - (int) CELL) / CELL);
    public static final int Y = (int) ((BOTTOM_Y - (int) CELL) / CELL);

    private final Grid grid;
    private final Background background;

    @Autowired
    public GameFieldLayout(@Qualifier(GAME_ELEMENTS_BEAN) final GameElementsGroup gameElementsGroup,
                           @Qualifier(GRID_BEAN) final Grid grid, @Qualifier(BACKGROUND_BEAN) final Background background) {
        super();
        getChildren().add(background);
        getChildren().add(grid);
        getChildren().add(gameElementsGroup);
        this.grid = grid;
        this.background = background;
    }

    public void changeGridVisibility() {
        grid.changeVisibility();
    }

}
