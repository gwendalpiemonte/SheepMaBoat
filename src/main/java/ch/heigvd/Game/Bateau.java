package ch.heigvd.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    public static Bateau[] initBateaux(){
        // Chaque joueur a 5 bateaux
        // 3 de 4 de long
        // 2 de 3 de long
        // Numéroté de A a E
        Bateau[] bateaux = new Bateau[5];

        Random random = new Random();
        int randomColonne;
        int randomLigne;
        int randomDirection;
        boolean check;

        for(int i = 0; i <= 4; i++){
            check = true;

            randomColonne     = random.nextInt(10) + 1;
            randomLigne       = random.nextInt(10) + 1;

            while (check){
                randomDirection   = random.nextInt(4) + 1;

                if(randomDirection == 1 && randomLigne >= 4){ // haut
                    bateaux[i] = new Bateau(new Position((char) (randomColonne + 65), randomLigne), 4, "haut");
                    check = false;
                } else if(randomDirection == 2 && randomLigne <= 7){ // bas
                    bateaux[i] = new Bateau(new Position((char) (randomColonne + 65), randomLigne), 4, "bas");
                    check = false;
                } else if(randomDirection == 3 && randomColonne >= 4){ //gauche
                    bateaux[i] = new Bateau(new Position((char) (randomColonne + 65), randomLigne), 4, "gauche");
                    check = false;
                } else if(randomDirection == 4 && randomColonne <= 4){ //droite
                    bateaux[i] = new Bateau(new Position((char) (randomColonne + 65), randomLigne), 4, "droite");
                    check = false;
                }
            }
        }

        return bateaux;
    }
}
