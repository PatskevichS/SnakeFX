package gmail.luronbel.snakefx.layout;

import static gmail.luronbel.snakefx.components.Grid.GRID_BEAN;
import static gmail.luronbel.snakefx.layout.Background.BACKGROUND_BEAN;
import static gmail.luronbel.snakefx.layout.GameElementsGroup.GAME_ELEMENTS_BEAN;
import static gmail.luronbel.snakefx.layout.MainMenu.MAIN_MENU_BEAN;
import static gmail.luronbel.snakefx.layout.MenuBar.MEU_BAR_BEAN;
import static gmail.luronbel.snakefx.layout.Settings.GAME_CONFIGURATION_BEAN;

import gmail.luronbel.snakefx.components.Grid;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    public static final int X_COUNT = (RIGHT_X - CELL) / CELL;
    public static final int Y_COUNT = (BOTTOM_Y - CELL) / CELL;

    private final Rectangle modalView;
    private final Pane interactiveViews;

    @Autowired
    public GameFieldLayout(@Qualifier(GAME_ELEMENTS_BEAN) final GameElementsGroup gameElementsGroup,
                           @Qualifier(MEU_BAR_BEAN) final MenuBar menuBar,
                           @Qualifier(GRID_BEAN) final Grid grid, @Qualifier(BACKGROUND_BEAN) final Background background,
                           @Qualifier(MAIN_MENU_BEAN) final MainMenu mainMenu,
                           @Qualifier(GAME_CONFIGURATION_BEAN) final Settings settings,
                           @Value("${window_height}") final int windowHeight, @Value("${window_width}") final int windowWidth) {
        super();
        interactiveViews = new Pane();
        interactiveViews.getChildren().add(gameElementsGroup);
        interactiveViews.getChildren().add(grid);
        interactiveViews.getChildren().add(menuBar);

        modalView = new Rectangle(windowWidth, windowHeight);
        modalView.setOpacity(0.7);
        modalView.setVisible(false);

        getChildren().add(background);
        getChildren().add(interactiveViews);
        getChildren().add(modalView);
        getChildren().add(mainMenu);
        getChildren().add(settings);
    }

    public void showModalView() {
        modalView.setVisible(true);
        interactiveViews.setEffect(new GaussianBlur());
    }

    public void hideModalView() {
        modalView.setVisible(false);
        interactiveViews.setEffect(null);
    }

    public boolean isModalViewShown() {
        return modalView.isVisible();
    }

}
