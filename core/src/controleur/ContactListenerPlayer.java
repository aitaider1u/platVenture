package controleur;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Timer;

import gameElement.Player;
import gameState.Game;
import outils.GestionaireTextureAnimation;
import outils.GestionaireBodyToDelete;
import outils.GestionaireDeSons;

public class ContactListenerPlayer implements ContactListener {
    private Player player;
    private GestionaireBodyToDelete gestionaieBodyToDelete ;
    private Game game;
    private int time1 = 2;
    private int time2 = 2;


    public ContactListenerPlayer(Player player, Game game, GestionaireBodyToDelete gets)
    {
        this.player = player;
        this.gestionaieBodyToDelete = gets;
        this.game = game;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA.getUserData() == "player" && (fixtureB.getUserData()== "1" ||fixtureB.getUserData()== "2"  )){
            System.out.println(fixtureA.getUserData()+"++"+fixtureB.getUserData());
            gestionaieBodyToDelete.addBodyToDelete(fixtureB.getBody());
            this.player.incrementerScore( Integer.parseInt(fixtureB.getUserData().toString()) ); // ajouter la valeur
            GestionaireDeSons.getInstance().playSoundGem();
        }

        if(fixtureB.getUserData() == "player" && (fixtureA.getUserData() == "1" || fixtureA.getUserData()== "2" )  ) {
            System.out.println(fixtureA.getUserData() + "++" + fixtureB.getUserData());
            gestionaieBodyToDelete.addBodyToDelete(fixtureB.getBody());
            this.player.incrementerScore(Integer.parseInt(fixtureA.getUserData().toString()));
            GestionaireDeSons.getInstance().playSoundGem();
        }


        if(fixtureA.getUserData() == "player" && fixtureB.getUserData()== "water"){
            System.out.println(fixtureA.getUserData()+"++"+fixtureB.getUserData());
            this.player.setDead(true);
            GestionaireDeSons.getInstance().playSoundLoose();
            Timer.Task timer = new Timer.Task() {       // lancer le timer
                @Override
                public void run(){
                    if(time1>=2){
                        time1--;
                    }else{
                        GestionaireDeSons.getInstance().playSoundLoose();
                        this.cancel();
                    }
                }
            };
            Timer.schedule(timer,1F,1F);
        }


        if(fixtureB.getUserData() == "player" && fixtureA.getUserData() == "water" ){
            System.out.println(fixtureA.getUserData() + "++" + fixtureB.getUserData());
            this.player.setDead(true);
            GestionaireDeSons.getInstance().playSoundPlouf();
            Timer.Task timer = new Timer.Task() {       // lancer le timer
                @Override
                public void run() {
                    if(time2>=1)
                        time2--;
                    else{
                        GestionaireDeSons.getInstance().playSoundLoose();
                        this.cancel();
                    }
                }
            };
            Timer.schedule(timer,1F,1F);
        }

    /*----------------------------------------------------------------------------------------*/
        if(fixtureA.getUserData() == "playerFoot" && fixtureB.getUserData()== "land"){
            this.player.setEstSurTerre(true);
            this.player.setDirctionDeSaut("");
            Vector2 vector2 = this.player.getBody().getLinearVelocity();
            double vitesse = Math.sqrt(0+ vector2.y*vector2.y);
            if(vitesse > 3){
                GestionaireDeSons.getInstance().playSoundCollision();
            }

            if(this.player.getSpeedX()==0 )
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationFixe());
            else
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationRun());
        }

        if(fixtureB.getUserData() == "playerFoot" && fixtureA.getUserData() == "land" ) {
            System.out.println(fixtureA.getUserData() + "++" + fixtureB.getUserData());
            this.player.setEstSurTerre(true);
            this.player.setDirctionDeSaut("");
            Vector2 vector2 = this.player.getBody().getLinearVelocity();
            double vitesse = Math.sqrt(0+ vector2.y*vector2.y);
            if(vitesse > 3){
                GestionaireDeSons.getInstance().playSoundCollision();
            }

            if(this.player.getSpeedX()==0)
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationFixe());
            else
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationRun());

        }

        if(fixtureA.getUserData() == "player"  && fixtureB.getUserData()== "sortie"){
            this.player.setPasserParLaSortie(true);
        }

        if(fixtureB.getUserData() == "player" && fixtureA.getUserData() == "sortie" ){
            this.player.setPasserParLaSortie(true);

        }

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();


        if(fixtureA.getUserData() == "player"  && fixtureB.getUserData()== "sortie"){
            System.out.println(fixtureA.getUserData()+"++"+fixtureB.getUserData());
            if(this.player.getBody().getPosition().x> this.game.getWeidthMap()) {
                this.player.setAfiniLeLevel(true);
                GestionaireDeSons.getInstance().playSoundWin();
            }else
                this.player.setPasserParLaSortie(false);
        }

        if(fixtureB.getUserData() == "player" && fixtureA.getUserData() == "sortie" ){
            System.out.println(fixtureA.getUserData() + "++" + fixtureB.getUserData());
            if(this.player.getBody().getPosition().x> this.game.getWeidthMap()) {
                this.player.setAfiniLeLevel(true);
            }else
                this.player.setPasserParLaSortie(false);
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }





}
