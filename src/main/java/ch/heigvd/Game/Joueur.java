package ch.heigvd.Game;

import java.util.Random;

public class Joueur {
    private String pseudo;
    private Terrain terrainBoats = new Terrain();
    private Terrain terrainShoots = new Terrain();
    Bateau[] bateaux = new Bateau[5];

    public Joueur(String pseudo){
        this.pseudo = pseudo;
        //this.initBateaux();
    }
    public String getPseudo() {
        return pseudo;
    }
    public Terrain getTerrainBoats() {
        return terrainBoats;
    }
    public Terrain getTerrainShoots() {
        return terrainShoots;
    }
    public void affiche(){
        System.out.println(this.pseudo);
    }
    public static void initBateaux(Joueur j){
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

        for(int i = 0; i < 4; i++){
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
        j.getTerrainBoats().insert(bateaux);
    }
}
