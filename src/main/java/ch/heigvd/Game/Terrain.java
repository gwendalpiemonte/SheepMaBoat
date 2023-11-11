package ch.heigvd.Game;

public class Terrain {
    // Codes d'échappement ANSI pour les couleurs
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
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
    public void tire(int colonne, int ligne, char obj){
        if (ligne >= 0 && ligne < getTerrain().length && colonne >= 0 && colonne < getTerrain()[0].length()) {
            // Convertir la chaîne en tableau de caractères pour pouvoir modifier la lettre
            char[] ligneArray = getTerrain()[ligne].toCharArray();
            ligneArray[colonne] = obj;
            getTerrain()[ligne] = new String(ligneArray);

        } else {
            System.out.println("Position invalide. Veuillez entrer des valeurs de ligne et colonne valides.");
        }
    }

    public void affiche(){
        for (String ligne : this.getTerrain()) {
            System.out.println(ligne);
        }
    }
}
