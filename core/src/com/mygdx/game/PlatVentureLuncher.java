package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gameState.GameStateManager;
import outils.GestionaireDeSons;

	public class PlatVentureLuncher extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private GameStateManager gameStateManager;
	@Override
	public void create() {
		GestionaireDeSons.getInstance().downloadSounds();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		gameStateManager = new GameStateManager(this);
	}


	@Override
	public void render() {
		gameStateManager.update(); // dans le update 1/60f
		gameStateManager.render();
	}


	@Override
	public void resize(int width, int height) {
		gameStateManager.resize(width,height);
	}

	public void update()
	{

	}


	@Override
	public void dispose() {
		gameStateManager.dispose();
		batch.dispose();

	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public SpriteBatch getSpriteBatch() {
		return batch;
	}
}
