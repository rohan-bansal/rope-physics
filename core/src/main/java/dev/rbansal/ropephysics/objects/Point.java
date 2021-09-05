package dev.rbansal.ropephysics.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import dev.rbansal.ropephysics.Constants;

public class Point {

    public Vector2 position, prevPosition;
    public boolean locked;
    public boolean selected;

    public Point(Vector2 position, boolean locked) {
        this.position = position;
        this.prevPosition = position.cpy();
        this.locked = locked;
        this.selected = false;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(selected ? Constants.POINT_SELECTED : (locked ? Constants.POINT_LOCKED : Constants.POINT_UNLOCKED));
        renderer.circle(position.x, position.y, Constants.POINT_RADIUS);
    }
}
