package ch.heigvd.Game;

import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private Joueur j1;
    private Joueur j2;
    private int round = 1;

    public void nextRound(){
        this.setRound(this.getRound() + 1);
    }
    public void affiche(){
        getJ1().affiche();
        getJ1().getTerrainBoats().affiche();
        System.out.println();
        getJ2().affiche();
        getJ2().getTerrainBoats().affiche();
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
    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }

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
    public void playRound(Joueur play, Joueur wait) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nTour de " + play.getPseudo());
        System.out.println("Entrez la colonne et la ligne où tirer (ex : A3) :");

        String input = scanner.nextLine();

        if (input.length() >= 2) {
            char colonne = input.charAt(0);
            int ligne = Integer.parseInt(input.substring(1));

            Position shoot = new Position(colonne, ligne);
            wait.getTerrainBoats().insert(shoot, 'X', true);
        } else {
            System.out.println("Entrée invalide.");
        }
    }

}
