package gmail.luronbel.snakefx.components.view.apple;

import gmail.luronbel.snakefx.components.GameField;
import gmail.luronbel.snakefx.configuration.CoreData;

/**
 * AppleView.
 *
 * @author Stas_Patskevich
 */
public interface AppleView {
    void show();

    void setCoreData(CoreData coreData);

    void setGameField(GameField gameField);
}
