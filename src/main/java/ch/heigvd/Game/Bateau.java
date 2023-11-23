package ch.heigvd.Game;

import java.util.LinkedList;
import java.util.Random;

public class Bateau {
    private final LinkedList<Position> positions = new LinkedList<>();
    private final int taille;
    private final char symbole;

    public Bateau(int taille, char symbole){
        this.taille = taille;
        this.symbole = symbole;
        this.creeBateau(taille);
    }

    private void creeBateau(int taille) {
        Random random = new Random();
        boolean horizontal = random.nextBoolean();

        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;

        if (horizontal && x + taille - 1 > 10) {
            x = 10 - taille + 1; // Réajuster x pour qu'il reste dans la grille
        } else if (!horizontal && y + taille - 1 > 10) {
            y = 10 - taille + 1; // Réajuster y pour qu'il reste dans la grille
        }

        for (int i = 0; i < taille; i++) {
            if (horizontal) {
                positions.add(new Position((char)((x + i) + 64), y)); // Convertit x et y en lettres A-J
            } else {
                positions.add(new Position((char)(x + 64), (y + i))); // Convertit x et y en lettres A-J
            }
        }
    }
    public static Bateau[] creeBateaux() {
        boolean check = false;

        while(!check){
            Bateau[] bateaux = new Bateau[6];
            bateaux[0] = new Bateau(4,'A');
            bateaux[1] = new Bateau(4,'B');
            bateaux[2] = new Bateau(4,'C');
            bateaux[3] = new Bateau(3,'D');
            bateaux[4] = new Bateau(3,'E');
            bateaux[5] = new Bateau(3,'F');

            check = true;

            for(int i = 0; i < bateaux.length; i++){
                for(int j = 0; j < bateaux.length; j++){
                    if(i != j){
                        for(Position pos : bateaux[i].getPositions()){
                            for(Position pos2 : bateaux[j].getPositions()){
                                if(pos.getColonne() == pos2.getColonne() && pos.getLigne() == pos2.getLigne()){
                                    check = false;
                                }
                            }
                        }
                    }
                }
            }

            if(check){
                return bateaux;
            }
        }

        return null;
    }
    public LinkedList<Position> getPositions() {
        return positions;
    }
    public void deletePos(Position position, int index){
        positions.set(index, position);
    }
    public char getSymbole() {
        return symbole;
    }
    public int getTaille() {
        return taille;
    }
}
