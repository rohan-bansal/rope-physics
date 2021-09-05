package dev.rbansal.ropephysics.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import dev.rbansal.ropephysics.Simulation;
import dev.rbansal.ropephysics.objects.Point;
import dev.rbansal.ropephysics.objects.PointManager;

public class InputCore implements InputProcessor {

    private OrthographicCamera camera;

    public InputCore(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.SPACE) {
            Simulation.toggleSimulation();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 result = camera.unproject(new Vector3(screenX, screenY, 0));
        Point p = PointManager.isOnPoint(result.x, result.y);

        if(button == Input.Buttons.LEFT) {
            if(p != null) {
                p.locked = !p.locked;
            } else {
                PointManager.createPoint(result.x, result.y, false);
            }
        } else if(button == Input.Buttons.MIDDLE) {
            if(p != null) {
                PointManager.deletePoint(p);
            }
        } else if(button == Input.Buttons.RIGHT) {
            if(p != null) {
                for(Point other : PointManager.getPoints()) {
                    if(other.selected && other != p) {
                        PointManager.createStick(other, p);
                        other.selected = false;
                        return false;
                    }
                }
                p.selected = !p.selected;
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        Gdx.app.log(screenX + "", screenY + "");
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
