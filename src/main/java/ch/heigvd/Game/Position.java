package ch.heigvd.Game;

public class Position {
    private char colonne; // A B C D E F G H I J
    private int ligne;    // 1 2 3 4 5 6 7 8 9 10

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

    public void setColonne(char colonne) {
        this.colonne = colonne;
    }
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }
}
