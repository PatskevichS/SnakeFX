package gmail.luronbel.snakefx.components;

import gmail.luronbel.snakefx.configuration.CoreData;
import lombok.AllArgsConstructor;

/**
 * AbstractComponent.
 *
 * @author Stas_Patskevich
 */
@AllArgsConstructor
public abstract class AbstractComponent {

    protected final CoreData coreData;
    protected final GameField gameField;
}
