package gameElement.element;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import gameElement.Element;


public  class Gem extends Element {
    protected int valeur; // la valeur du gem
    protected float tempsAnim = 0;


    public Gem(Body body, int valeur) {
        super(body, valeur);
        this.valeur = valeur;
        this.tempsAnim = 0;
        this.body.setAwake(true);
    }

    public Gem(Body body, Sprite sprite,int valeur) {
        super(body, sprite);
        this.valeur = valeur;
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {

    }
}
