package outils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class GestionaireDeSons {
    private static GestionaireDeSons instance = new GestionaireDeSons();
    //private Sound alert;
    private Sound collision;
    private Sound gem;
    private Sound loose;
    private Sound plouf;
    private Sound win;


    private GestionaireDeSons() {


    }

    public void downloadSounds(){
        this.collision = Gdx.audio.newSound(Gdx.files.internal("sounds/collision.ogg"));
        this.gem = Gdx.audio.newSound(Gdx.files.internal("sounds/gem.ogg"));
        this.loose = Gdx.audio.newSound(Gdx.files.internal("sounds/loose.ogg"));
        this.plouf = Gdx.audio.newSound(Gdx.files.internal("sounds/plouf.ogg"));
        this.win = Gdx.audio.newSound(Gdx.files.internal("sounds/win.ogg"));
    }
    public static GestionaireDeSons getInstance() {
        return instance;
    }

    public void playSoundCollision()
    {
        this.collision.play();
        //long i = this.collision.play(1f);
        //this.collision.setPitch(i,2);
        //this.collision.setLooping(i,false);
    }

    public void playSoundGem()
    {
        this.gem.play();
        //long i = this.gem.play(1f);
        //this.gem.setPitch(i,2);
        //this.gem.setLooping(i,false);
    }

    public void playSoundLoose()
    {
       this.loose.play();
        // long i = this.loose.play(1f);
       // this.loose.setPitch(i,2);
       // this.loose.setLooping(i,false);
    }

    public void playSoundPlouf()
    {
        this.plouf.play();
        //long i = this.plouf.play(1f);
        //this.plouf.setPitch(i,2);
        //this.plouf.setLooping(i,false);
    }

    public void playSoundWin()
    {
        this.win.play();
        //long i = this.win.play(1f);
        //this.win.setPitch(i,2);
        //this.win.setLooping(i,false);
    }

    public void  disposeSounds(){
        //this.alert.dispose();
        this.collision.dispose();
        this.gem.dispose();
        this.win.dispose();
        this.loose.dispose();
        this.plouf.dispose();
    }


}
