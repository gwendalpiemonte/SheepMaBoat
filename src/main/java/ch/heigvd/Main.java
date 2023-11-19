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

        // Init partie
        Game game = new Game();

        Joueur j1 = new Joueur("Joueur 1");
        Joueur.initBateaux(j1);

        j1.getTerrainBoats().affiche();


        /*while (true){
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
        }*/
    }
}