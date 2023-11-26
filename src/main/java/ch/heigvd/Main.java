package ch.heigvd;

import ch.heigvd.Game.*;
//import ch.heigvd.PicoCLI.PicoCLI;
//import picocli.CommandLine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // Initialisation du jeu
        Game game = new Game();

        // Boucle de jeu
        while (true){
            if(game.getRound() == 1){
                game.firstRound();
                System.out.println(game.printGame(game.getP1()));
                System.out.println(game.printGame(game.getP2()));
            }

            if(game.getRound() % 2 != 0){
                game.playRound(game.getP1(), game.getP2());
                System.out.println(game.printGame(game.getP1()));
                System.out.println(game.printGame(game.getP2()));
            } else {
                game.playRound(game.getP2(), game.getP1());
                System.out.println(game.printGame(game.getP1()));
                System.out.println(game.printGame(game.getP2()));
            }

            game.nextRound();
        }



        /*
        CommandLine main = new CommandLine(new PicoCLI());
        main.execute(args);

        System.exit(0);
*/
    }
}