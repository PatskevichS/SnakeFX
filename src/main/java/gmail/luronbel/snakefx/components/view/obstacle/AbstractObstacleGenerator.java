package gmail.luronbel.snakefx.components.view.obstacle;

import gmail.luronbel.snakefx.components.view.View;

/**
 * AbsctractObstacleGenerator.
 *
 * @author Stas_Patskevich
 */
public abstract class AbstractObstacleGenerator implements ObstacleGenerator {
    protected ObstacleView view;

    @Override
    public void setView(final View view) {
        this.view = (ObstacleView) view;
    }
}
