package modele;

public class CaseVide implements Case {
    /*
     Cette clase représentera la case vide de couleur blanche
     sur la grille
     */

    Position position;

    public CaseVide(Position position) {
        this.position = position;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public String toString() {
        return "CaseVide";
    }

}
