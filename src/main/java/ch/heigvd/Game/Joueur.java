package ch.heigvd.Game;

import ch.heigvd.GameServer.ClientHandler;

public class Joueur {
    private final String username;
    private final Terrain terrainBoats = new Terrain(true);
    private final Terrain terrainShoots = new Terrain(false);

    public Joueur(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public Terrain getTerrainBoats() {
        return terrainBoats;
    }
    public Terrain getTerrainShoots() {
        return terrainShoots;
    }
    public void affiche(){
        System.out.println(this.username);
    }
    public boolean win(){
        boolean win = false;

        for(Bateau bateau : getTerrainBoats().getBateaux()){
            if(bateau.isCoule()){
                win = true;
            } else {
                win = false;
                break;
            }
        }

        return win;
    }
}
