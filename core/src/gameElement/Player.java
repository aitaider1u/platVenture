package gameElement;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import outils.Constants;
import outils.FabriqueSprite;
import outils.GestionaireTextureAnimation;

public class Player {
    private Body body;
    private World  world;
    private boolean surTerre = true;
    private boolean ADroite = true;
    private String dirctionDeSaut ="";
    private int score = 0;
    private boolean afiniLeLevel = false;
    private boolean dead = false;
    private Sprite sprite;
    private int speedX = 0;
    private boolean passerParLaSortie = false;
    private float tempsAnim = 0;


    public Player(World world, float x , float y ) {
        this.world =world;
        this.speedX = 0;
        createBody(world,x,y);
        this.sprite = FabriqueSprite.getInstance().createSpritePlayer(x,y);
    }



    /**
     * creation du body du player
     * @param world pour ajouter le body dans le monde du jeu
     * @param x la position du player en X
     * @param y la position du player en Y
     */
    public void createBody(World world ,  float x, float y)
    {
        BodyDef bd = new BodyDef();
        bd.position.set(x,y);
        bd.type = BodyDef.BodyType.DynamicBody;
        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        float l1 = Constants.UNITY_world/2;
        float h1 = Constants.UNITY_world;
        vertices[0] = new Vector2(l1/2,0);
        vertices[1] = new Vector2(0,-h1/4);
        vertices[2] = new Vector2(-l1/2,0);
        vertices[3] = new Vector2(0,h1/2);
        shape.set(vertices);
        this.body = world.createBody(bd);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        FixtureDef fixtureDef2 = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(h1/8);
        circleShape.setPosition(new Vector2(0,-h1/4-h1/8));
        fixtureDef2.shape = circleShape;
        this.body.createFixture(fixtureDef).setUserData("player");//
        fixtureDef.density = 0.5F;
        fixtureDef.friction = 0.5F;
        fixtureDef.restitution = 0.1F;
        this.body.createFixture(fixtureDef2).setUserData("playerFoot");
        fixtureDef2.density = 0.5F;
        fixtureDef2.friction = 0.5F;
        fixtureDef2.restitution = 0.1F;
        shape.dispose();
        circleShape.dispose();
    }

    public Body getBody() {
        return body;
    }


    public boolean estSurTerre()
    {
        return this.surTerre;
    }

    public void setEstSurTerre(boolean estSurTerre)
    {
        this.surTerre = estSurTerre;
    }


    public void setADroite(boolean ADroite) {
        this.ADroite = ADroite;
    }


    public void setDirctionDeSaut(String dirctionDeSaut) {
        this.dirctionDeSaut = dirctionDeSaut;
    }

    /**
     * Methode pour incrementer le score du player
     * @param i la valeur a ajouter pour le score du player
     **/

    public void incrementerScore(int i ){
        this.score= this.score+ i;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean afiniLeLevel() {
        return this.afiniLeLevel;
    }

    public void setAfiniLeLevel(boolean afiniLeLevel) {
        this.afiniLeLevel = afiniLeLevel;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }


    /**
     * Methode pour dessiner le player dans le spriteBatch. La position du draw il sera relier a la postion du body du player
     * Cette methode fait appel a un singleton qui fait la gestion de animation des texture pour renvoyer l'image correspandante a l'instant t
     * @param spriteBatch c'est le SpriteBatch de la fonction runder qui tourne le jeu
    **/

    public void drawPlayer(SpriteBatch spriteBatch){
            tempsAnim += 3/60f;
            this.sprite.setPosition(body.getPosition().x-Constants.UNITY_world/4,body.getPosition().y-Constants.UNITY_world/2);
            this.sprite.setRegion((TextureRegion) GestionaireTextureAnimation.getInstance().getAnimationPlayer().getKeyFrame(tempsAnim));
            this.sprite.flip(!this.ADroite,false);
            this.sprite.draw(spriteBatch);
    }


    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public boolean estPasserParLaSortie() {
        return passerParLaSortie;
    }

    public void setPasserParLaSortie(boolean passerParLaSortie) {
        this.passerParLaSortie = passerParLaSortie;
    }
}
