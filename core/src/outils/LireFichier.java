package outils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;



public class LireFichier {

    public static int extraireHauter(String str)
    {
        String[] ligne = str.split("\n");
        String hauteur = ligne[0].split(" ")[1];
        return Integer.parseInt(hauteur);
    }

    public static int extraireLargeur(String str)
    {
        String[] ligne = str.split("\n");
        String largeur = ligne[0].split(" ")[0];
        return Integer.parseInt(largeur);

    }

    public static int extraireTemp(String str)
    {
        String[] ligne = str.split("\n");
        String largeur = ligne[0].split(" ")[2];
        return Integer.parseInt(largeur);
    }

   public static  String[][] extraireMap(String str) {
       int largeur = extraireLargeur(str);
       int hauteur = extraireHauter(str);
       String[][] map = new String[hauteur][largeur];
       String[] tous = str.split("\n");
       for (int i = 1; i < hauteur + 1; i++) {
           String[] ligneI = tous[i].split("");
           for (int j = 0; j < largeur; j++) {
               map[i-1][j] = ligneI[j];
           }
       }
       return map;
   }

    public static String extraireBackGround(String str)
    {
        String[] ligne = str.split("\n");
        return ligne[ligne.length-1]; // on retourne la derniere ligne
    }





}
