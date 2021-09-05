package dev.rbansal.ropephysics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import dev.rbansal.ropephysics.objects.Point;
import dev.rbansal.ropephysics.objects.PointManager;
import dev.rbansal.ropephysics.objects.Stick;

public class Simulation {

    private static boolean simulating = false;

    public static boolean isSimulating() {
        return simulating;
    }

    public static void toggleSimulation() {
        simulating = !simulating;
    }

    // using verlet integration
    public static void simulate() {
        for(Point p : PointManager.getPoints()) {
            if(!p.locked) {

                Vector2 positionBeforeUpdate = p.position;

                p.position = VectorTools.add(p.position, VectorTools.mult(VectorTools.sub(p.position, p.prevPosition), Constants.FRICTION));
                float gravity = 9.8f * Gdx.graphics.getDeltaTime();
                p.position = VectorTools.add(p.position, VectorTools.mult(new Vector2(0, -1), gravity));

                p.prevPosition = positionBeforeUpdate;

                // bouncing off walls
                if(p.position.y < 0) {
                    p.position.y = 0;
                    p.prevPosition.y = p.position.y + VectorTools.sub(p.position, p.prevPosition).y;
                } else if(p.position.y > Gdx.graphics.getHeight()) {
                    p.position.y = Gdx.graphics.getHeight();
                    p.prevPosition.y = p.position.y + VectorTools.sub(p.position, p.prevPosition).y;
                }

                if(p.position.x < 0) {
                    p.position.x = 0;
                    p.prevPosition.x = p.position.x + VectorTools.sub(p.position, p.prevPosition).x;
                } else if(p.position.x > Gdx.graphics.getWidth()) {
                    p.position.x = Gdx.graphics.getWidth();
                    p.prevPosition.x = p.position.x + VectorTools.sub(p.position, p.prevPosition).x;
                }
            }
        }

        for(int i = 0; i < 10; i++) {
            for(Stick stick : PointManager.getSticks()) {
                Vector2 stickCenter = VectorTools.divide(VectorTools.add(stick.pointA.position, stick.pointB.position), 2);
                Vector2 stickDirection = VectorTools.sub(stick.pointA.position, stick.pointB.position).nor();

                if(!stick.pointA.locked) {
                    stick.pointA.position = stickCenter.cpy().add(VectorTools.mult(stickDirection, stick.length / 2));
                }
                if(!stick.pointB.locked) {
                    stick.pointB.position = stickCenter.cpy().sub(VectorTools.mult(stickDirection, stick.length / 2));
                }
            }
        }

    }
}
