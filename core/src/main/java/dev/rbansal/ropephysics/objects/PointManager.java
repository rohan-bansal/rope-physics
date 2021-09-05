package dev.rbansal.ropephysics.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import dev.rbansal.ropephysics.Constants;

public class PointManager {

    private static DelayedRemovalArray<Point> points = new DelayedRemovalArray<>();
    private static DelayedRemovalArray<Stick> sticks = new DelayedRemovalArray<>();

    public static DelayedRemovalArray<Point> getPoints() {
        return points;
    }

    public static DelayedRemovalArray<Stick> getSticks() {
        return sticks;
    }

    public static Point createPoint(float x, float y, boolean locked) {
        Point p = new Point(new Vector2(x, y), locked);
        points.add(p);
        return p;
    }

    public static Stick createStick(Point p1, Point p2) {
        Stick s = new Stick(p1, p2, p1.position.dst(p2.position));
        sticks.add(s);
        return s;
    }

    public static void deleteStick(Stick s) {
        sticks.removeValue(s, true);
    }

    public static void deletePoint(Point p) {
        for(Stick s : sticks) {
            if (s.hasPoint(p)) {
                deleteStick(s);
            }
        }
        points.removeValue(p, true);
    }

    public static Point isOnPoint(float x, float y) {
        for(Point p : points) {
            if(Math.pow((x - p.position.x), 2) + Math.pow((y - p.position.y), 2) < Math.pow(Constants.POINT_RADIUS, 2)) {
                return p;
            }
        }
        return null;
    }

    public static void renderPoints(ShapeRenderer renderer) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for(Stick s : sticks) {
            s.render(renderer);
        }

        for(Point p : points) {
            p.render(renderer);
        }

        renderer.end();
    }
}
