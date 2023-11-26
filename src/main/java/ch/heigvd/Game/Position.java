package ch.heigvd.Game;

public class Position {
    private final char colonne; // A B C D E
    private final int ligne;    // 1 2 3 4 5

    public Position(char colonne, int ligne){
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public char getColonne() {
        return colonne;
    }
    public int getLigne() {
        return ligne;
    }
}
