package gmail.luronbel.snakefx.configuration;

import static gmail.luronbel.snakefx.layout.GameElementsGroup.GAME_ELEMENTS_BEAN;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.X_COUNT;
import static gmail.luronbel.snakefx.layout.GameFieldLayout.Y_COUNT;
import static gmail.luronbel.snakefx.layout.PointsCounter.POINTS_COUNTER_BEAN;

import gmail.luronbel.snakefx.layout.GameElementsGroup;
import gmail.luronbel.snakefx.layout.PointsCounter;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Sphere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * CoreData.
 *
 * @author Stas_Patskevich
 */
@Component(CoreData.CORE_DATA_BEAN)
public class CoreData {
    public static final String CORE_DATA_BEAN = "coreData";

    private final Sphere apple;
    private final Queue<ImageView> obstacles = new LinkedList<>();
    private Iterator<ImageView> obstaclesIterator;
    private final Queue<ImageView> walls = new LinkedList<>();
    private Iterator<ImageView> wallsIterator;
    private final Queue<Sphere> snakeSegments = new LinkedList<>();
    private Iterator<Sphere> snakeSegmentsIterator;

    @Autowired
    @Qualifier(POINTS_COUNTER_BEAN)
    private PointsCounter pointsCounter;

    public CoreData(@Qualifier(GAME_ELEMENTS_BEAN) final GameElementsGroup gameElementsGroup,
                    @Value("${obstacles_count:15}") final int obstaclesCount) {
        apple = new Sphere();
        apple.setVisible(false);
        gameElementsGroup.getChildren().add(apple);

        for (int i = 1; i <= obstaclesCount; i++) {
            final ImageView obstacle = new ImageView();
            obstacle.setVisible(false);
            obstacles.add(obstacle);
        }
        gameElementsGroup.getChildren().addAll(obstacles);
        obstaclesIterator = obstacles.iterator();

        for (int i = 1; i <= (X_COUNT * 2 + Y_COUNT * 2 + 4); i++) {
            final ImageView wall = new ImageView();
            wall.setVisible(false);
            walls.add(wall);
        }
        gameElementsGroup.getChildren().addAll(walls);
        wallsIterator = walls.iterator();


        for (int i = 1; i <= Y_COUNT * X_COUNT; i++) {
            final Sphere snakeSegment = new Sphere();
            snakeSegment.setVisible(false);
            snakeSegments.add(snakeSegment);
        }
        gameElementsGroup.getChildren().addAll(snakeSegments);
        snakeSegmentsIterator = snakeSegments.iterator();
    }

    public void reset() {
        apple.setVisible(false);

        obstacles.iterator().forEachRemaining(e -> e.setVisible(false));
        obstaclesIterator = obstacles.iterator();

        walls.iterator().forEachRemaining(e -> e.setVisible(false));
        wallsIterator = walls.iterator();

        snakeSegments.iterator().forEachRemaining(e -> e.setVisible(false));
        snakeSegmentsIterator = snakeSegments.iterator();

        pointsCounter.reset();
    }

    public Sphere getApple() {
        return apple;
    }

    public ImageView getObstacle() {
        if (obstaclesIterator.hasNext()) {
            return obstaclesIterator.next();
        } else {
            return null;
        }
    }

    public ImageView getWall() {
        if (wallsIterator.hasNext()) {
            return wallsIterator.next();
        } else {
            return null;
        }
    }

    public Sphere getSnakeSegment() {
        if (snakeSegmentsIterator.hasNext()) {
            return snakeSegmentsIterator.next();
        } else {
            return null;
        }
    }

    public void addPoints(final int points) {
        pointsCounter.addPoints(points);
    }
}
