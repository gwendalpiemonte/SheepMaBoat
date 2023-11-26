package ch.heigvd;

import ch.heigvd.Game.*;
import ch.heigvd.PicoCLI.PicoCLI;
import picocli.CommandLine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*

        // Initialisation du jeu
        Game game = new Game();

        // Boucle de jeu
        while (true){
            if(game.getRound() == 1){
                game.firstRound();
                game.affiche();
            }

            if(game.getRound() % 2 != 0){
                game.playRound(game.getJ1(), game.getJ2());
                game.affiche();
            } else {
                game.playRound(game.getJ2(), game.getJ1());
                game.affiche();
            }

            game.nextRound();
        }

        */

        CommandLine main = new CommandLine(new PicoCLI());
        main.execute(args);

        System.exit(0);

    }
}