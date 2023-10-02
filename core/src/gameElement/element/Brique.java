package gameElement.element;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

import gameElement.Element;

public class Brique extends Element {
    public Brique(Body body) {
        super(body);
    }
    public Brique(Body body, Sprite sprite) {
        super(body, sprite);
    }

}
