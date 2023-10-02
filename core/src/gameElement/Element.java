package gameElement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Element {
    protected Body body;
    protected Sprite sprite;
    protected boolean visible;

    /**
     *  Constucteur d'un element du monde
     *  @param body le body
    **/

    public Element(Body body) {
        this.body = body;
    }

    /**
     *  Constucteur d'un element du monde
     *  @param body le body.
     *  @param sprite le spite de l'element du monde.
    **/
    public Element(Body body,Sprite sprite) {
        this.body = body;
        this.sprite =sprite;
    }


    public Element(Body body,int valeur)
    {
        this.body = body;
    }



    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * Methode pour dessiner l'element du monde dans le spriteBatch
     * @param spriteBatch c'est le SpriteBatch de la fonction runder qui tourne le jeu
    **/

    public void draw(SpriteBatch spriteBatch){
        this.sprite.draw(spriteBatch);
    }


}
