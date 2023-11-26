package ch.heigvd.Game;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Joueur[] players = new Joueur[2];
    private int round = 1;

    public void nextRound(){
        this.setRound(this.getRound() + 1);
    }

    public String printGame(Joueur player){
        return  player.getUsername() + "\n" +
                player.getTerrainBoats().affiche()+ "\n" +
                player.getTerrainShoots().affiche();
    }

    public void setPlayer(Joueur j) {
        if(players[0] == null)
            players[0] = j;
        else
            players[1] = j;
    }
    public Joueur getJ1() {
        return players[0];
    }
    public Joueur getJ2() {
        return players[1];
    }
    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }

    public boolean isReady(){
        return getJ1() != null && getJ2() != null;
    }

    /*
    public void firstRound(){
        // Saisie du nom du Joueur 1
        System.out.print("Entrez le nom du Joueur 1 : ");
        String nomJoueur1 = scanner.nextLine();
        setJ1(new Joueur(nomJoueur1));

        // Saisie du nom du Joueur 2
        System.out.print("Entrez le nom du Joueur 2 : ");
        String nomJoueur2 = scanner.nextLine();
        setJ2(new Joueur(nomJoueur2));

        setJ1(new Joueur(nomJoueur1));
        setJ2(new Joueur(nomJoueur2));
    }
     */
    public void playRound(Joueur play, Joueur wait) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nTour de " + play.getUsername());
        System.out.println("Entrez la colonne et la ligne où tirer (ex : A3) :");

        String input = scanner.nextLine();

        if (input.length() >= 2) {
            char colonne = input.charAt(0);
            int ligne = Integer.parseInt(input.substring(1));

            Position shoot = new Position(colonne, ligne);
            wait.getTerrainBoats().insert(shoot, play.getTerrainShoots());

            if(wait.win()){
                System.out.println(play.getUsername() + " a gagné !");
                System.exit(0);
            }
        } else {
            System.out.println("Entrée invalide.");
        }
    }
}
