package ch.heigvd;

import ch.heigvd.Game.Bateau;
import ch.heigvd.Game.Joueur;
import ch.heigvd.Game.Terrain;
import ch.heigvd.Game.Game;
import ch.heigvd.PicoCLI.PicoCLI;
import picocli.CommandLine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CommandLine main = new CommandLine(new PicoCLI());
        main.execute(args);

        Game game = new Game();

        Scanner scanner = new Scanner(System.in);

        char colonne;
        int ligne;
        int round = 1;

        Terrain terrainJ1 = game.getTerrainJ1();
        Terrain terrainJ2 = game.getTerrainJ2();

        // Saisie du nom du Joueur 1
        System.out.print("Entrez le nom du Joueur 1 : ");
        String nomJoueur1 = scanner.nextLine();
        game.setJ1(new Joueur(nomJoueur1));

        // Saisie du nom du Joueur 2
        System.out.print("Entrez le nom du Joueur 2 : ");
        String nomJoueur2 = scanner.nextLine();
        game.setJ2(new Joueur(nomJoueur2));

        // Bateaux
        Bateau b1 = new Bateau('A',1,3,"bas");
        Bateau b2 = new Bateau('J',5,2,"haut");
        Bateau b3 = new Bateau('B',8,4,"droite");
        Bateau b4 = new Bateau('H',6,4,"bas");
        Bateau[] bts ={b1, b2, b3, b4};

        Bateau.insert(terrainJ1,bts);

        game.affiche();
/*
        System.out.println("\nTour de " + game.getJ1().getPseudo());
        System.out.println("Entrez la colonne où tirer  (A,B,C,D,E,F,G,H,I,J) :");
        colonne = scanner.next().charAt(0);
        System.out.println("Entrez la ligne où tirer   (1,2,3,4,5,6,7,8,9,10) :");
        ligne = scanner.nextInt();

        Canon.tire(terrainJ2, colonne, ligne, 'X');
        game.affiche();*/

        // Boucle de jeux
        /*
        while (true){
            if(round == 1){
                // Saisie du nom du Joueur 1
                System.out.print("Entrez le nom du Joueur 1 : ");
                String nomJoueur1 = scanner.nextLine();

                // Saisie du nom du Joueur 2
                System.out.print("Entrez le nom du Joueur 2 : ");
                String nomJoueur2 = scanner.nextLine();

                game.setJ1(new Joueur(nomJoueur1));
                game.setJ2(new Joueur(nomJoueur2));

                game.affiche();
            }

            // Tour du Joueur 1
            if(round % 2 != 0){
                System.out.println("\nTour de " + game.getJ1().getPseudo());
                System.out.println("Entrez la colonne où tirer  (A,B,C,D,E,F,G,H,I,J) :");
                colonne = scanner.next().charAt(0);
                System.out.println("Entrez la ligne où tirer   (1,2,3,4,5,6,7,8,9,10) :");
                ligne = scanner.nextInt();

                Canon.tire(terrainJ2, colonne, ligne);
                game.affiche();
            }
            // Tour du Joueur 2
            else{
                System.out.println("\nTour de " + game.getJ2().getPseudo());
                System.out.println("Entrez la colonne où tirer  (A,B,C,D,E,F,G,H,I,J) :");
                colonne = scanner.next().charAt(0);
                System.out.println("Entrez la ligne où tirer   (1,2,3,4,5,6,7,8,9,10) :");
                ligne = scanner.nextInt();

                Canon.tire(terrainJ1, colonne, ligne);
                game.affiche();
            }
            ++round;
        }*/

        System.exit(0);

    }
}