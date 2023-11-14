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

        //CommandLine main = new CommandLine(new PicoCLI());
        //main.execute(args);

        // Init partie
        Game game = new Game();
        Terrain terrainJ1 = game.getTerrainJ1();
        Terrain terrainJ2 = game.getTerrainJ2();
        Scanner scanner = new Scanner(System.in);
        int round = 1;
        char colonne;
        int ligne;

        // Bateaux
        Bateau b1 = new Bateau('A',1,3,"bas", 'A');
        Bateau b2 = new Bateau('J',5,2,"gauche", 'B');
        Bateau b3 = new Bateau('B',8,4,"droite", 'C');
        Bateau b4 = new Bateau('H',6,4,"bas", 'D');
        Bateau b5 = new Bateau('D',3,4,"droite", 'E');
        Bateau[] bateaux ={b1, b2, b3, b4, b5};

        terrainJ1.insert(bateaux);
        terrainJ2.insert(bateaux);

        //terrainJ1.insert('A',1,'X', true);



        while (true){
            if(game.getRound() == 1){
                // Saisie du nom du Joueur 1
                System.out.print("Entrez le nom du Joueur 1 : ");
                String nomJoueur1 = scanner.nextLine();
                game.setJ1(new Joueur(nomJoueur1));

                // Saisie du nom du Joueur 2
                System.out.print("Entrez le nom du Joueur 2 : ");
                String nomJoueur2 = scanner.nextLine();
                game.setJ2(new Joueur(nomJoueur2));

                game.setJ1(new Joueur(nomJoueur1));
                game.setJ2(new Joueur(nomJoueur2));

                game.affiche();
            }

            // Tour du Joueur 1
            if(game.getRound() % 2 != 0){
                System.out.println("\nTour de " + game.getJ1().getPseudo());
                System.out.println("Entrez la colonne où tirer  (A,B,C,D,E,F,G,H,I,J) :");
                colonne = scanner.next().charAt(0);
                System.out.println("Entrez la ligne où tirer   (1,2,3,4,5,6,7,8,9,10) :");
                ligne = scanner.nextInt();

                terrainJ2.insert(colonne,ligne,'X',true);
                game.affiche();
            }
            // Tour du Joueur 2
            else{
                System.out.println("\nTour de " + game.getJ2().getPseudo());
                System.out.println("Entrez la colonne où tirer  (A,B,C,D,E,F,G,H,I,J) :");
                colonne = scanner.next().charAt(0);
                System.out.println("Entrez la ligne où tirer   (1,2,3,4,5,6,7,8,9,10) :");
                ligne = scanner.nextInt();

                terrainJ1.insert(colonne,ligne,'X',true);
                game.affiche();
            }
            game.nextRound();
        }

        //System.exit(0);

    }
}