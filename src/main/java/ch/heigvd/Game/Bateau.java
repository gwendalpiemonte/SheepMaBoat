package ch.heigvd.Game;

import java.util.HashMap;
import java.util.Map;

public class Bateau {
    private static Map<Character, Integer> mapPositions = new HashMap<>();
    private char colonne;
    private int ligne;
    private int taille;
    private String direction;

    public Bateau(char colonne, int ligne, int taille, String direction){
        this.colonne = colonne;
        this.ligne = ligne;
        this.taille = taille;
        this.direction = direction;
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

    public static void insert(Terrain terrain,Bateau[] bateaux){
        mapPositions.put('A', 1);
        mapPositions.put('B', 2);
        mapPositions.put('C', 3);
        mapPositions.put('D', 4);
        mapPositions.put('E', 5);
        mapPositions.put('F', 6);
        mapPositions.put('G', 7);
        mapPositions.put('H', 8);
        mapPositions.put('I', 9);
        mapPositions.put('J', 10);

        for(Bateau b : bateaux){
            int ligne = b.getLigne();
            char colonne = b.getColonne();


            for(int i = 0; i < b.getTaille(); ++ i){

                int x = mapPositions.get(colonne);
                x = x * 4 + 1;

                int y = ligne;
                y += y;

                terrain.tire(x,y,'B');

                switch (b.getDirection()){
                    case "haut":
                        ligne -= 1;
                        break;
                    case "bas":
                        ligne += 1;
                        break;
                    case "gauche":
                        colonne -= 1;
                        break;
                    case "droite":
                        colonne += 1;
                        break;
                }
            }
        }
    }
}
