package outils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class FabriqueBriqueBody {

    private static FabriqueBriqueBody instance = new FabriqueBriqueBody();

    private FabriqueBriqueBody() { }

    public static FabriqueBriqueBody getInstance() {
        return instance;
    }


    public Body createBriqueBody(World world , float x , float y){
        Body body;
        BodyDef bd = new BodyDef();
        bd.position.set(x,y);
        bd.type = BodyDef.BodyType.StaticBody;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.UNITY_world/2,Constants.UNITY_world/2);
        body = world.createBody(bd);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("land");
        fixtureDef.density =1f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.1f;
        shape.dispose();
        return body;
    }


    public Body createPlateformeJBody(World world , float x , float y){
        Body body;
        BodyDef bd = new BodyDef();
        bd.position.set(x,y);
        bd.type = BodyDef.BodyType.StaticBody;
        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[5];
        float l1 = Constants.UNITY_world;
        float h1 = Constants.UNITY_world*3/4;
        vertices[0] = new Vector2(l1/2-l1/2,0-h1/2);
        vertices[1] = new Vector2(0-l1/2,h1-h1/2);
        vertices[2] = new Vector2(l1-l1/2,h1-h1/2);
        vertices[3] = new Vector2(l1-l1/2, 0-h1/2);
        vertices[4] = new Vector2(0-l1/2, h1/2-h1/2);
        shape.set(vertices);
        body = world.createBody(bd);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density =1f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("land");
        shape.dispose();
        return body;
    }
    public Body createPlateformeKBody(World world , float x , float y){
        Body body;
        BodyDef bd = new BodyDef();
        bd.position.set(x,y);
        bd.type = BodyDef.BodyType.StaticBody;
        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        float l1 = Constants.UNITY_world;
        float h1 = Constants.UNITY_world*3/4;
        vertices[0] = new Vector2(l1/2-0,h1/2-0);
        vertices[1] = new Vector2(l1/2-0,h1/2-h1);
        vertices[2] = new Vector2(l1/2-l1,h1/2-h1);
        vertices[3] = new Vector2(l1/2-l1, h1/2-0);
        shape.set(vertices);
        body = world.createBody(bd);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density =1f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.1f;
        body.createFixture(fixtureDef).setUserData("land");
        shape.dispose();
        return body;
    }

    public Body createPlateformeLBody(World world , float x , float y){
        Body body;
        BodyDef bd = new BodyDef();
        bd.position.set(x,y);
        bd.type = BodyDef.BodyType.StaticBody;
        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[5];
        float l1 = Constants.UNITY_world;
        float h1 = Constants.UNITY_world*3/4;
        vertices[0] = new Vector2(0-l1/2,0-h1/2);
        vertices[1] = new Vector2(0-l1/2,h1-h1/2);
        vertices[2] = new Vector2(l1-l1/2,h1/2-h1/2);
        vertices[3] = new Vector2(l1-l1/2,h1-h1/2);
        vertices[4] = new Vector2(l1/2-l1/2,0-h1/2);
        shape.set(vertices);
        body = world.createBody(bd);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density =1f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.1f;
        body.createFixture(fixtureDef).setUserData("land");
        shape.dispose();
        return body;
    }



    public Body createWaterBody(World world , float x , float y){
        Body body;
        BodyDef bd = new BodyDef();
        bd.position.set(x,y);
        bd.type = BodyDef.BodyType.StaticBody;
        PolygonShape shape = new PolygonShape();
        float l1 = Constants.UNITY_world;
        float h1 = Constants.UNITY_world*3/4;
        shape.setAsBox(l1/2,h1/2);
        body = world.createBody(bd);
        //world.createJoint();
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density =1f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("water");
        shape.dispose();
        return body;
    }

        public Body createSortieBody(World world , float x , float y){
        Body body;
        BodyDef bd = new BodyDef();
        bd.position.set(x,y);
        bd.type = BodyDef.BodyType.StaticBody;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.UNITY_world/2,Constants.UNITY_world/2);
        body = world.createBody(bd);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density =1f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("sortie");
        shape.dispose();
        return body;
    }

    public Body createGemBody(World world , float x , float y,int valeur){
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.UNITY_world/4);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        String userdata ;
        if(valeur == 1) {
            userdata = "1";
        }else{
            userdata = "2";
        }
        body.createFixture(fixtureDef).setUserData(userdata);
        shape.dispose();
        return body;
    }


}
