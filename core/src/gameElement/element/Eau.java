package gameElement.element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

import gameElement.Element;

public class Eau extends Element {

    public Eau(Body body){
        super(body);
    }

    public Eau(Body body, Sprite sprite) {
        super(body, sprite);
    }

}
