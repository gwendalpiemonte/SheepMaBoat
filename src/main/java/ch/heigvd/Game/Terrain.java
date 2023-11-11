package ch.heigvd.Game;

import java.util.HashMap;
import java.util.Map;

public class Terrain {
    private static Map<Character, Integer> mapPositions = new HashMap<>();
    private String[] terrain = {
            "     A   B   C   D   E   F   G   H   I   J",
            "   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗",
            " 1 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            " 2 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            " 3 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            " 4 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            " 5 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            " 6 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            " 7 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            " 8 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            " 9 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣",
            "10 ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║ ",
            "   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝"
    };

    public String[] getTerrain() {
        return terrain;
    }
    public void insert(char colonne, int ligne, char obj){
        mapPositions.put('A', 1);
        mapPositions.put('B', 2);
        mapPositions.put('C', 3);
        mapPositions.put('D', 4);
        mapPositions.put('E', 5);
        mapPositions.put('F', 6);
        mapPositions.put('G', 7);
        mapPositions.put('H', 8);
        mapPositions.put('I', 9);
        mapPositions.put('J', 10);

        int x = mapPositions.get(colonne);
        x = x * 4 + 1;

        int y = ligne;
        y += y;

        if (y >= 0 && y < getTerrain().length && x >= 0 && x < getTerrain()[0].length()) {
            char[] ligneArray = getTerrain()[y].toCharArray();
            ligneArray[x] = obj;
            getTerrain()[y] = new String(ligneArray);

        } else {
            System.out.println("Error");
        }
    }
    public void insert(Bateau bateau){
        char colonne = bateau.getColonne();
        int ligne = bateau.getLigne();
        char obj = bateau.getNom();

        for(int i = 0; i < bateau.getTaille(); ++ i){

            this.insert(colonne, ligne, obj);

            switch (bateau.getDirection()){
                case "haut":
                    ligne -= 1;
                    break;
                case "bas":
                    ligne += 1;
                    break;
                case "gauche":
                    colonne -= 1;
                    break;
                case "droite":
                    colonne += 1;
                    break;
            }
        }
    }
    public void insert(Bateau[] bateaux){
        for(Bateau b : bateaux){
            this.insert(b);
        }
    }
    public void affiche(){
        for (String ligne : this.getTerrain()) {
            System.out.println(ligne);
        }
    }
}
