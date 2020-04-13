package gmail.luronbel.snakefx.layout;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

/**
 * Timer.
 *
 * @author Stas_Patskevich
 */
@Component(Timer.TIMER_BEAN)
public class Timer extends Text {
    public static final String TIMER_BEAN = "timer";
    private static final String TEMPLATE = "Timer: %s:%s";
    private long min = 0;
    private long sec = 0;
    private boolean running = false;

    public Timer() {
        final InnerShadow innerShadow = new InnerShadow();
        final DropShadow dropShadow = new DropShadow();
        innerShadow.setInput(dropShadow);
        setEffect(innerShadow);
        setFont(Font.font(null, FontWeight.BOLD, 20));
        setFill(Color.WHITE);
        reset();
    }

    public void start() {
        running = true;
        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1000);
                    updateTime();
                } catch (final InterruptedException e) {
                }
            }
        }).start();
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        stop();
        min = 0;
        sec = 0;
        setText(String.format(TEMPLATE, min, sec));
    }

    private void updateTime() {
        sec++;
        if (sec >= 60) {
            if (min <= 99) {
                min++;
            }
            sec = 0;
        }
        setText(String.format(TEMPLATE, min, sec));
    }
}