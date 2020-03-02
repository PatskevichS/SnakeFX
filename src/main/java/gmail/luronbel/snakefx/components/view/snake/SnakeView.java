package gmail.luronbel.snakefx.components.view.snake;

/**
 * SnakeView.
 *
 * @author Stas_Patskevich
 */
public interface SnakeView {

    SnakeView setY(int y);

    int getY();

    SnakeView setX(int x);

    int getX();

    void show();

    void hide();
}
