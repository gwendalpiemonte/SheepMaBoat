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
    public void insert(Position position, char obj, boolean tire){
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

        int x = mapPositions.get(position.getColonne());
        x = x * 4 + 1;

        int y = position.getLigne();
        y += y;

        if (y >= 0 && y < getTerrain().length && x >= 0 && x < getTerrain()[0].length()) {
            char[] ligneArray = getTerrain()[y].toCharArray();

            if (ligneArray[x] != ' ') {
                System.out.println("Touché");
                //Ckeck si le bateau touché est coulé ou pas
            }
            else {
                System.out.println("Raté");
            }

            ligneArray[x] = obj;
            getTerrain()[y] = new String(ligneArray);

        } else {
            System.out.println("Error");
        }

    }
    public void insert(Bateau bateau){

        for(int i = 0; i < bateau.getTaille(); ++ i){

            this.insert(bateau.getPosTete(), bateau.getNom(), false);

            switch (bateau.getDirection()){
                case "haut":
                    bateau.getPosTete().setLigne(bateau.getPosTete().getLigne() - 1);
                    break;
                case "bas":
                    bateau.getPosTete().setLigne(bateau.getPosTete().getLigne() + 1);
                    break;
                case "gauche":
                    bateau.getPosTete().setColonne((char) (bateau.getPosTete().getColonne() - 1));
                    break;
                case "droite":
                    bateau.getPosTete().setColonne((char) (bateau.getPosTete().getColonne() + 1));
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
