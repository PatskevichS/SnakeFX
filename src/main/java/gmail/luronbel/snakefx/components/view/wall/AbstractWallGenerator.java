package gmail.luronbel.snakefx.components.view.wall;

import gmail.luronbel.snakefx.components.view.View;

/**
 * AbstractWallGenerator.
 *
 * @author Stas_Patskevich
 */
public abstract class AbstractWallGenerator implements WallGenerator {
    protected WallView view;

    @Override
    public void setView(final View view) {
        this.view = (WallView) view;
    }
}
