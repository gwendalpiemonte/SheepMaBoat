package ch.heigvd.Game;

public class Joueur {
    private String pseudo;

    public Joueur(String pseudo){
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void affiche(){
        System.out.println(this.pseudo);
    }
}
