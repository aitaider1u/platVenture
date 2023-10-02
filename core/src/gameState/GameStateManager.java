package gameState;
import com.mygdx.game.PlatVentureLuncher;
import java.util.Stack;

/**
 * idee du gameState a été prise dans un tutorial sur Youtube
 * lien : https://www.youtube.com/watch?v=A9msQK1kRgM&list=PLtrgbYRsYUHqvV7RAnN3GYSfW3dk19Q93&index=3
 *
*/


public class GameStateManager {
    private final PlatVentureLuncher app;
    private Stack<GameState>  states ;



    public GameStateManager(PlatVentureLuncher platVentureLuncher){
        this.app = platVentureLuncher;
        this.states = new Stack<GameState>();
        this.setState(StatesGame.INTRODUCTION);
    }

    /**
    * enumeration sur les etats du jeux introdution et GAME(le jeu )
    */
    public enum StatesGame{
        INTRODUCTION,
        GAME
    }

    /**
    * la methode update donc elle prend etat actuel(GameState soit Intoduction soit Game pour le moment ) du jeu
    * et elle execute la fonction update de GameState Actuel
    */
    public void update(){
        states.peek().update();
    }

    /**
    * la methode render donc elle prend etat actuel(GameState soit Intoduction soit Game pour le moment ) du jeu
    * et elle execute la fonction render de GameState Actuel
    */


    public void render(){
        states.peek().render();
    }

    /**
     * Methode qui dispose tous les GameStats qui sont stacker dans stastes (qui est un champ privé dans la class GameStateManager)
    */
    public void dispose(){
        for(GameState gameState : states){
            gameState.dispose();
        }
        states.clear();
    }


    public PlatVentureLuncher application(){
        return  this.app;
    }

    public void resize(int x,int y){
        states.peek().resize(x,y);
    }


    /**
     *Methode qui nous permet de modifier l'etat du jeux (exemple en passant de l'introdution a game )
    */

    public void setState(StatesGame statesGame){  // on utilise enumeration declarer en haut
        if(states.size()>=1) {
            states.pop().dispose();
        }
        states.push(getState(statesGame));
    }

    /**
     * elle renvoie un nouveau gameState Selon la valeur de l'enumeration donner en paramettre
     * @param statesGame valeur d'un enumaration declarer dans la classe
    */

    public GameState getState(StatesGame statesGame){
        switch (statesGame){
            case INTRODUCTION: return new Introduction(this);
            case GAME:   return new Game(this);
        }
        return null;
    }
}

