package outils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class FabriqueSprite {

     private static FabriqueSprite instance = new FabriqueSprite();

    private FabriqueSprite() { }

    public static FabriqueSprite getInstance() {
        return instance;
    }



    public Sprite createSpriteBrique(float x, float y,String str){
        Sprite sprite =  new Sprite(new Texture(Gdx.files.internal("images/Brick_"+str+".png")));
        sprite.setBounds(x-Constants.UNITY_world/2,y-Constants.UNITY_world/2,Constants.UNITY_world,Constants.UNITY_world);
        return sprite;
    }

    public Sprite createSpritePlatefrome(float x, float y,String str){
        Sprite sprite =  new Sprite(new Texture(Gdx.files.internal("images/Platform_"+str+".png")));
        sprite.setBounds(x-Constants.UNITY_world/2,y-Constants.UNITY_world*3/8,Constants.UNITY_world,Constants.UNITY_world*3/4);
        return sprite;
    }

    public Sprite createSpriteWater(float x, float y){
        Sprite sprite =  new Sprite(new Texture(Gdx.files.internal("images/Water.png")));
        sprite.setBounds(x-Constants.UNITY_world/2,y-Constants.UNITY_world*3/8,Constants.UNITY_world,Constants.UNITY_world*3/4);
        return sprite;
    }

    public Sprite createSpritePlayer(float x, float y){
        Sprite sprite =  new Sprite((TextureRegion) GestionaireTextureAnimation.getInstance().getAnimationRun().getKeyFrame(1));
        sprite.setBounds(x,y,Constants.UNITY_world/2,Constants.UNITY_world);
        return sprite;
    }
  public Sprite createSpriteSortie(float x, float y){
        Sprite sprite =  new Sprite(new Texture(Gdx.files.internal("images/Exit_Z.png")));
        sprite.setBounds(x-Constants.UNITY_world/2,y-Constants.UNITY_world/2,Constants.UNITY_world,Constants.UNITY_world);
        return sprite;
    }
  public Sprite createSpriteGem(float x, float y,String valeur){


        Animation monAnim;
        Texture texGlobale = new Texture(Gdx.files.internal("images/Gem_"+valeur+".png"));
        int largIm = texGlobale.getWidth();
        int nbIms = texGlobale.getHeight() / largIm;
        TextureRegion[][] grille = TextureRegion.split(texGlobale, largIm, largIm);
        Array<TextureRegion> tabEnLigne = new Array<TextureRegion>();
        for (int i = 0; i < nbIms; i++) {
            tabEnLigne.add(grille[i][0]);
        }
        monAnim = new Animation(1, tabEnLigne, Animation.PlayMode.LOOP);
        TextureRegion im = (TextureRegion) GestionaireTextureAnimation.getInstance().getAnimationFixe().getKeyFrame(1);
        Sprite sprite =  new Sprite(im);
        sprite.setBounds(x-Constants.UNITY_world/4,y-Constants.UNITY_world/4,Constants.UNITY_world/2,Constants.UNITY_world/2);
        return sprite;
    }
}
