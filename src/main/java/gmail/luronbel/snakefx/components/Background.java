package gmail.luronbel.snakefx.components;

import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(Background.BACKGROUND_BEAN)
public class Background extends ImageView {
    public static final String BACKGROUND_BEAN = "background";

    private final int windowHeight;
    private final int windowWidth;

    @Autowired
    public Background(@Value("${window_height}") final int windowHeight, @Value("${window_width}") final int windowWidth) {
        super("/game/background.png");
        setFitHeight(windowHeight);
        setFitWidth(windowWidth);
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
    }
}
