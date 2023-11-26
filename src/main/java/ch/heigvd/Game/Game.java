package ch.heigvd.Game;

import ch.heigvd.GameClient.GameClient;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Player[] players = new Player[2];
    private int round = 1;

    public void nextRound(){
        this.setRound(this.getRound() + 1);
    }

    public String printGame(Player player){
        return  player.getUsername() + "\n" +
                player.getPlayBoardBoats().affiche()+ "\n" +
                player.getPlayBoardShoots().affiche();
    }

    public void setPlayer(Player j) {
        if(players[0] == null)
            players[0] = j;
        else
            players[1] = j;
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

    public void playRound(Player play, Player wait, char column, int row){
        Position shoot = new Position(column, row);
        wait.getPlayBoardBoats().insert(shoot, play.getPlayBoardShoots());

        if(wait.win()){
            System.out.println(play.getUsername() + " won !");
            System.exit(0);
        }
    }
}
