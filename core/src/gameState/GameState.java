package gameState;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PlatVentureLuncher;



/**
 * idee du gameState a été prise dans un tutorial sur Youtube
 * lien : https://www.youtube.com/watch?v=A9msQK1kRgM&list=PLtrgbYRsYUHqvV7RAnN3GYSfW3dk19Q93&index=3
 * */


public abstract class GameState {

    protected GameStateManager gameStateManager;
    protected PlatVentureLuncher app;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;


    public GameState(GameStateManager gsm){
        this.gameStateManager = gsm;
        this.app = this.gameStateManager.application();
        batch = this.app.getSpriteBatch();
        camera = this.app.getCamera();
    }

	public void resize(int width, int height) {
        camera.setToOrtho(false , width,height);
    }

    public abstract void update();
    public abstract void render();
    public abstract void dispose();

}
