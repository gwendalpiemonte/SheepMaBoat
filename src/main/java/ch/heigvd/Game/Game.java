package ch.heigvd.Game;

import ch.heigvd.GameClient.GameClient;

import java.util.Scanner;

public class Game {
/*
    public static void main(String[] args) {
        GameClient.start("127.0.0.1", 3333);
    }

 */
    private final Scanner scanner = new Scanner(System.in);
    private final Player[] players = new Player[2];
    private int round = 1;

    private boolean hasWinner = false;

    public void nextRound(){
        this.setRound(this.getRound() + 1);
    }

    public String printGame(Player player){
        return  "Player : " + player.getUsername() + "\n" +
                "Boats playboard \n" +
                player.getPlayBoardBoats().affiche()+ "\n" +
                "Shoots playboard \n" +
                player.getPlayBoardShoots().affiche();
    }

    public void setPlayer(Player j) {
        if(players[0] == null)
            players[0] = j;
        else
            players[1] = j;
    }

    public boolean isHasWinner() {
        return hasWinner;
    }

    public Player getP1() {
        return players[0];
    }
    public Player getP2() {
        return players[1];
    }
    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public boolean isReady(){
        return getP1() != null && getP2() != null;
    }


    public void firstRound(){
        // Saisie du nom du Joueur 1
        System.out.print("Enter player1's name : ");
        String nomJoueur1 = scanner.nextLine();
        setPlayer(new Player(nomJoueur1));

        // Saisie du nom du Joueur 2
        System.out.print("Enter player2's name  : ");
        String nomJoueur2 = scanner.nextLine();
        setPlayer(new Player(nomJoueur2));
    }

    public String playRound(Player play, Player wait, char column, int row){
        Position shoot = new Position(column, row);
        String result = "";

        result = wait.getPlayBoardBoats().insert(shoot, play.getPlayBoardShoots());

        if(wait.isLosing()){
            result = play.getUsername() + " won !";
            hasWinner = true;
            //System.exit(0);
        }

        return result;
    }
}
