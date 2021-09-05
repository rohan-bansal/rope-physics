package dev.rbansal.ropephysics;

import com.badlogic.gdx.Game;
import dev.rbansal.ropephysics.screens.MainWindow;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
	@Override
	public void create() {
		setScreen(new MainWindow());
	}
}