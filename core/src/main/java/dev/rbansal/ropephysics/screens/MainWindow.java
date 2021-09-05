package dev.rbansal.ropephysics.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.rbansal.ropephysics.Simulation;
import dev.rbansal.ropephysics.listeners.InputCore;
import dev.rbansal.ropephysics.objects.PointManager;

public class MainWindow implements Screen {

	ShapeRenderer shapeRenderer;
	Viewport viewport;
	OrthographicCamera camera;

	@Override
	public void show() {

		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);

		Gdx.input.setInputProcessor(new InputMultiplexer(new InputCore(camera)));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(46 / 255f, 52 / 255f, 64 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		shapeRenderer.setProjectionMatrix(camera.combined);
		PointManager.renderPoints(shapeRenderer);

		if(Simulation.isSimulating()) {
			Simulation.simulate();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}
}