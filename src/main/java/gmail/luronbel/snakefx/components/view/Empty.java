package gmail.luronbel.snakefx.components.view;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.components.view.wall.WallView;
import gmail.luronbel.snakefx.configuration.CoreData;
import org.springframework.lang.NonNull;

/**
 * Empty.
 *
 * @author Stas_Patskevich
 */
public class Empty implements Generator {

    public Empty(final WallView view) {
    }

    @Override
    public void generate(@NonNull final CoreData coreData, @NonNull final GameField gameField) {
    }

    @Override
    public void setView(final View view) {
    }
}
