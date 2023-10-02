package gameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

import outils.GestionaireDeSons;

public class Introduction extends GameState{
    private static Texture backgroundTexture;
    private int time =3;
    public Introduction(GameStateManager gsm) {
        super(gsm);
        backgroundTexture = new Texture(Gdx.files.internal("images/Intro.png"));
          Timer.Task timer = new Timer.Task() {       // lancer le timer
            @Override
            public void run() {
                if(time>=1){
                    time--;
                    if (time == 2)
                    GestionaireDeSons.getInstance().playSoundWin();
                    System.out.println("hello ::: "+ time);
                }else{
                    System.out.println("introdution fini");
                    this.cancel(); // stop the thread of th timer
                    gameStateManager.setState(GameStateManager.StatesGame.GAME);
                }
            }
        };
        Timer.schedule(timer,1F,1F); // lancer le timer

    }


    @Override
    public void update() {
        updateCamera();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        batch.draw(backgroundTexture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        this.batch.end();
    }

    private void updateCamera(){
        this.camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void dispose(){
        backgroundTexture.dispose();
    }
}
