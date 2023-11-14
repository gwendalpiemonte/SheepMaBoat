package ch.heigvd.Game;

public class Game {
    private Terrain terrainJ1 = new Terrain();
    private Terrain terrainJ2 = new Terrain();
    private Joueur j1;
    private Joueur j2;
    private int round = 1;

    public void nextRound(){
        this.setRound(this.getRound() + 1);
    }
    public void affiche(){
        getJ1().affiche();
        getTerrainJ1().affiche();
        System.out.println();
        getJ2().affiche();
        getTerrainJ2().affiche();
    }
    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }
    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }
    public Joueur getJ1() {
        return j1;
    }
    public Joueur getJ2() {
        return j2;
    }
    public Terrain getTerrainJ1() {
        return terrainJ1;
    }
    public Terrain getTerrainJ2() {
        return terrainJ2;
    }
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
