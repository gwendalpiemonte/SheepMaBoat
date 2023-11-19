package ch.heigvd.Game;

import java.util.HashMap;
import java.util.Map;

public class Bateau {
    private Position posTete;
    private int taille;
    private String direction;
    private char nom;

    public Bateau(Position posTete, int taille, String direction, char nom){
        this.posTete = posTete;
        this.taille = taille;
        this.direction = direction;
        this.nom = nom;
    }
    public Bateau(Position posTete, int taille, String direction){
        this.posTete = posTete;
        this.taille = taille;
        this.direction = direction;
        this.nom = 'B';
    }


    public Position getPosTete() {
        return posTete;
    }
    public int getTaille() {
        return taille;
    }
    public String getDirection() {
        return direction;
    }
    public char getNom() {
        return nom;
    }
}
