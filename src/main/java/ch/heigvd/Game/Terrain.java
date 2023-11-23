package ch.heigvd.Game;

import java.util.HashMap;
import java.util.Map;

public class Terrain {
    private static final Map<Character, Integer> mapPositions = new HashMap<>();
    private Bateau[] bateaux = new Bateau[5];
    private final String[] terrain = {
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

    public Terrain(boolean bat){
        if(bat){
            bateaux = Bateau.creeBateaux();
            assert bateaux != null;
            for(Bateau bateau : bateaux){
                for(Position position : bateau.getPositions()){
                    insert(position, bateau.getSymbole());
                }
            }
        }
    }

    public String[] getTerrain() {
        return terrain;
    }
    private int parsePos(int val){
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
        return mapPositions.get(val);
    }
    public void insert(Position position, char obj){
        int x = parsePos(position.getColonne());
        x = x * 4 + 1;

        int y = position.getLigne();
        y += y;

        if (y >= 0 && y < getTerrain().length && x >= 0 && x < getTerrain()[0].length()) {
            char[] ligneArray = getTerrain()[y].toCharArray();
            ligneArray[x] = obj;
            getTerrain()[y] = new String(ligneArray);

        } else {
            System.out.println("Error");
        }
    }
    public void insert(Position position, char obj, Terrain autreTerrain){
        int x = parsePos(position.getColonne());
        x = x * 4 + 1;

        int y = position.getLigne();
        y += y;

        if (y >= 0 && y < getTerrain().length && x >= 0 && x < getTerrain()[0].length()) {
            char[] ligneArray = getTerrain()[y].toCharArray();
            ligneArray[x] = obj;
            getTerrain()[y] = new String(ligneArray);

        } else {
            System.out.println("Error");
        }
        boolean touch = false;
        for(Bateau bateau : bateaux){
            for(Position pos : bateau.getPositions()) {
                if (pos.getColonne() == position.getColonne() && pos.getLigne() == position.getLigne()) {
                    touch = true;
                    System.out.println("Touché");
                    insert(pos, '0');
                    autreTerrain.insert(pos, '0');
                    bateau.deletePos(new Position('A', 0), bateau.getPositions().indexOf(pos));
                    int count = 0;
                    for (Position pos2 : bateau.getPositions()) {
                        if (pos2.getColonne() == 'A' && pos2.getLigne() == 0) {
                            ++count;
                        }
                    }
                    if (count == bateau.getTaille()) {
                        System.out.println("Coulé");
                    }
                }
            }
        }
        if(!touch){
            System.out.println("Raté");
            autreTerrain.insert(position,'X');
        }
    }
    public void affiche(){
        for (String ligne : this.getTerrain()) {
            System.out.println(ligne);
        }
    }
}
