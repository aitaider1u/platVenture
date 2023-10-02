package gameElement.element;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import outils.GestionaireTextureAnimation;

public class Gem1 extends Gem{


    public Gem1(Body body, Sprite sprite, int valeur) {
        super(body, sprite, valeur);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        tempsAnim += 1/60f;
        this.sprite.setRegion((TextureRegion) GestionaireTextureAnimation.getInstance().getAnimationGem1().getKeyFrame(tempsAnim));
        this.sprite.draw(spriteBatch);
    }
}
