package ch.heigvd.Game;

import java.util.Random;

public class Joueur {
    private final String pseudo;
    private final Terrain terrainBoats = new Terrain(true);
    private final Terrain terrainShoots = new Terrain(false);

    public Joueur(String pseudo){
        this.pseudo = pseudo;
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

}
