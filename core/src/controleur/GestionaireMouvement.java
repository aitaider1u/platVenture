package controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import gameElement.Player;
import outils.GestionaireTextureAnimation;

public class GestionaireMouvement implements InputProcessor {
    private Player player;




    public GestionaireMouvement(Player player) {
        this.player = player;
    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.DPAD_LEFT){
            this.player.setSpeedX(-2);
            player.setADroite(false);
            if(this.player.estSurTerre())
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationRun());
        }
        else if(keycode == Input.Keys.DPAD_RIGHT){
            this.player.setSpeedX(2);
            player.setADroite(true);
            if(this.player.estSurTerre())
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationRun());
        }
        if(keycode == Input.Keys.DPAD_UP){
            if(this.player.estSurTerre()){
                player.getBody().applyForceToCenter(1, 400, false);
                player.setEstSurTerre(false);
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationJump());
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {


        if(!Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && this.player.estSurTerre() ){
            GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationFixe());
        }

        if(keycode == Input.Keys.DPAD_LEFT  ){
            if(!Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
                this.player.setSpeedX(0);
            else {
                this.player.setSpeedX(2);
                this.player.setADroite(true);
            }
        }

        if(keycode == Input.Keys.DPAD_RIGHT  ){
            if(!Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
                this.player.setSpeedX(0);
            else {
                this.player.setSpeedX(-2);
                this.player.setADroite(false);
            }
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if( pointer == 0 && screenX < Gdx.graphics.getWidth()/2){
            this.player.setSpeedX(-2);
            player.setADroite(false);
            if(this.player.estSurTerre())
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationRun());
        }

        if(pointer == 0 && screenX > Gdx.graphics.getWidth()/2){
            this.player.setSpeedX(2);
            player.setADroite(true);
            if(this.player.estSurTerre())
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationRun());
        }

        if(pointer == 1 ){
            if(this.player.estSurTerre()){
                player.getBody().applyForceToCenter(1, 400, false);
                player.setEstSurTerre(false);
                GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationJump());
            }
        }






        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if( !Gdx.input.isTouched() && this.player.estSurTerre() ){
            GestionaireTextureAnimation.getInstance().setAnimationPlayer(GestionaireTextureAnimation.getInstance().getAnimationFixe());
        }

        if(screenX > Gdx.graphics.getWidth()/2){  // right
            if(!Gdx.input.isTouched() )
                this.player.setSpeedX(0);
            else {
                this.player.setSpeedX(-2);
                this.player.setADroite(false);
            }
        }

        if(screenX < Gdx.graphics.getWidth()/2){ // left
            if(!Gdx.input.isTouched())
                this.player.setSpeedX(0);
            else {
                this.player.setSpeedX(2);
                this.player.setADroite(true);
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
