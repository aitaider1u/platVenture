package outils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionaireBodyToDelete implements  Iterable<Body>{
    private ArrayList bodyToDelete = new ArrayList();

    public GestionaireBodyToDelete() {
    }

    public void addBodyToDelete(Body body){
        this.bodyToDelete.add(body);
    }

    public ArrayList getBodyToDelete() {
        return bodyToDelete;
    }
    public void removeBodyFromList(Body body){
        this.bodyToDelete.remove(body);
    }
    public void clearList(){
        this.bodyToDelete.clear();
    }
    @Override
    public Iterator<Body> iterator() {
        return bodyToDelete.iterator();
    }
}
