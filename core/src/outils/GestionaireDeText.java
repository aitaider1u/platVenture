package outils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GestionaireDeText {
    private OrthographicCamera cameraText;
    private FreeTypeFontGenerator generator;
    //private FreeTypeFontGenerator.FreeTypeFontParameter params ;
    private BitmapFont time;
    private BitmapFont score;
    private BitmapFont state;
    private SpriteBatch batchText;



    public GestionaireDeText() {
        batchText = new SpriteBatch();
        cameraText = new OrthographicCamera();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Comic_Sans_MS_Bold.ttf"));
        cameraText.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.borderColor = Color.BLACK;
		params.color = new Color(1f,1f,0f,0.75f);
        params.size = (Gdx.graphics.getWidth()*60)/1024;
		params.borderWidth = (Gdx.graphics.getWidth()*3)/1024;
		batchText.setProjectionMatrix(cameraText.combined);
        cameraText.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        time=generator.generateFont(params);
        score=generator.generateFont(params);
        state=generator.generateFont(params);

    }


    public void updateCamera(){
        cameraText.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    public void updateFontState(String str){
        this.state.draw(batchText,str,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,str.length(),1,true);
    }

    public void updateFontScore(int score){
        String str = "Score : "+score;
        this.score.draw(batchText,str,Gdx.graphics.getWidth()-10,Gdx.graphics.getHeight(),str.length(),0,true);
    }


    public void updateFontTime(int time){
        String str = ""+time;
        this.time.draw(batchText,str,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),str.length(),1,false);
    }

    public void dispose() {
        time.dispose();
        score.dispose();
        this.state.dispose();

    }

    public SpriteBatch getBatchText() {
        return batchText;
    }

    public OrthographicCamera getCameraText() {
        return cameraText;
    }

}
