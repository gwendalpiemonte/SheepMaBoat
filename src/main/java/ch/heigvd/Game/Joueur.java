package ch.heigvd.Game;

import java.util.Random;

public class Joueur {
    private String pseudo;
    private Terrain terrainBoats = new Terrain();
    private Terrain terrainShoots = new Terrain();

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
