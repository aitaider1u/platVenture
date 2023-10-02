package outils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GestionaireTextureAnimation {
    private static GestionaireTextureAnimation instance = new GestionaireTextureAnimation();
    private Animation animationGem1;
    private Animation animationGem2;
    private Animation animationFixe;
    private Animation animationJump;
    private Animation animationRun;
    private Animation animationPlayer;
    private TextureAtlas run;
    private TextureAtlas jump;
    private TextureAtlas idle;
    private Texture backGround;



    private GestionaireTextureAnimation() {

    }

    public Animation getAnimationGem1(){
        return animationGem1;
    }

    public Animation getAnimationGem2(){
        return animationGem2;
    }
    public static GestionaireTextureAnimation getInstance() {
        return instance;

    }

    public void initialiserLesAnimation(){
        initGem1();
        initGem2();
        initPlayerIdle();
        initPlayerJump();
        initPlayerRun();
        initBackground("");
        animationPlayer = animationFixe;
    }

    public Animation getAnimationFixe() {
        return animationFixe;
    }

    public Animation getAnimationJump() {
        return animationJump;
    }

    public Animation getAnimationRun() {
        return animationRun;
    }

    private void initGem1(){
        Texture texGlobale = new Texture(Gdx.files.internal("images/Gem_1.png"));
        int largIm = texGlobale.getWidth();
        int nbIms = texGlobale.getHeight() / largIm;
        TextureRegion[][] grille = TextureRegion.split(texGlobale, largIm, largIm);
        Array<TextureRegion> tabEnLigne = new Array<TextureRegion>();
        for (int i = 0; i < nbIms; i++) {
            tabEnLigne.add(grille[i][0]);
        }
        animationGem1 = new Animation(10/60f, tabEnLigne, Animation.PlayMode.LOOP);
    }

    private void initGem2(){
        Texture texGlobale = new Texture(Gdx.files.internal("images/Gem_2.png"));
        int largIm = texGlobale.getWidth();
        int nbIms = texGlobale.getHeight() / largIm;
        TextureRegion[][] grille = TextureRegion.split(texGlobale, largIm, largIm);
        Array<TextureRegion> tabEnLigne = new Array<TextureRegion>();
        for (int i = 0; i < nbIms; i++) {
            tabEnLigne.add(grille[i][0]);
        }
        animationGem2 = new Animation(10/60f, tabEnLigne, Animation.PlayMode.LOOP);
    }

    private void initPlayerIdle(){
        idle = new TextureAtlas(Gdx.files.internal("images/idlePlayer.atlas"));
        animationFixe = new Animation(10/60f, idle.getRegions(), Animation.PlayMode.LOOP);
    }

    private void initPlayerJump(){
        jump = new TextureAtlas(Gdx.files.internal("images/JumpPlayer.atlas"));
        animationJump = new Animation(10/60f,jump.getRegions(), Animation.PlayMode.LOOP_PINGPONG);
    }

    private void initPlayerRun(){
        run = new TextureAtlas(Gdx.files.internal("images/runPlayer.atlas"));
        animationRun = new Animation(10/60f, run.getRegions(), Animation.PlayMode.LOOP);
    }

    public Animation getAnimationPlayer() {
        return animationPlayer;
    }

    public void setAnimationPlayer(Animation animationPlayer) {
        this.animationPlayer = animationPlayer;
    }

    private void initBackground(String path){
        backGround = new Texture(Gdx.files.internal("images/Back.png"));
    }


    public void drawBackground(SpriteBatch spriteBatch, float w, float h){
        spriteBatch.draw(this.backGround,0,0,w, h);
    }
    
    public void dispose(){
        this.run.dispose();
        this.jump.dispose();
        this.backGround.dispose();
        this.idle.dispose();

    }


}
