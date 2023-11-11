package ch.heigvd.Game;

import java.util.HashMap;
import java.util.Map;

public class Bateau {
    private char colonne;
    private int ligne;
    private int taille;
    private String direction;
    private char nom;

    public Bateau(char colonne, int ligne, int taille, String direction, char nom){
        this.colonne = colonne;
        this.ligne = ligne;
        this.taille = taille;
        this.direction = direction;
        this.nom = nom;
    }

    public char getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
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
