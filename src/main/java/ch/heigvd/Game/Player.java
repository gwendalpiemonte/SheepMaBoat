package ch.heigvd.Game;

import ch.heigvd.GameClient.GameClient;

public class Player {

    /*
    public static void main(String[] args) {
        GameClient.start("127.0.0.1", 3333);
    }
     */

    private final String username;
    private final PlayBoard terrainBoats = new PlayBoard(true);
    private final PlayBoard terrainShoots = new PlayBoard(false);

    public Player(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public PlayBoard getPlayBoardBoats() {
        return terrainBoats;
    }
    public PlayBoard getPlayBoardShoots() {
        return terrainShoots;
    }
    public void affiche(){
        System.out.println(this.username);
    }
    public boolean lose(){
        boolean win = false;

        for(Boat boat : getPlayBoardBoats().getBoats()){
            if(boat.isDrowned()){
                win = true;
            } else {
                win = false;
                break;
            }
        }

        return win;
    }
}
