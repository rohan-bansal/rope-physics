package dev.rbansal.ropephysics.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.rbansal.ropephysics.Simulation;
import dev.rbansal.ropephysics.listeners.InputCore;
import dev.rbansal.ropephysics.objects.PointManager;

public class MainWindow implements Screen {

	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	Viewport viewport;
	OrthographicCamera camera;

	BitmapFont font;

	@Override
	public void show() {

		font = new BitmapFont(Gdx.files.internal("ari2.fnt"));
		font.getData().setScale(0.5f);

		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
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

		batch.begin();

		font.draw(batch, "[left click] create point", 20, Gdx.graphics.getHeight() - 20);
		font.draw(batch, "[middle click] delete point", 20, Gdx.graphics.getHeight() - 40);
		font.draw(batch, "left click a point to fix it in place", 20, Gdx.graphics.getHeight() - 60);
		font.draw(batch, "right click two points to constrain them with a stick", 20, Gdx.graphics.getHeight() - 80);

		batch.end();
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