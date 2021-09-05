package dev.rbansal.ropephysics.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.rbansal.ropephysics.Constants;

public class Stick {

    public Point pointA, pointB;
    public float length;

    public Stick(Point pointA, Point pointB, float length) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.length = length;
    }

    public boolean hasPoint(Point p) {
        return p == pointA || p == pointB;
    }

    public void render(ShapeRenderer renderer) {

        renderer.setColor(Constants.STICK_COLOR);
        renderer.rectLine(pointA.position, pointB.position, Constants.STICK_WIDTH);
    }
}
