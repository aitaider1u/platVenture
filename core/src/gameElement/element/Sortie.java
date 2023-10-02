package gameElement.element;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

import gameElement.Element;

public class Sortie extends Element {

    public Sortie(Body body) {
        super(body);
    }

    public Sortie(Body body, Sprite sprite) {
        super(body, sprite);
    }


}
