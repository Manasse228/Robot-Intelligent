
package controleur;

import modele.Modele;

public class Controleur {

    public Modele modele;

    public Controleur(Modele modele) {
        this.modele = modele;
    }
    /*
     Au démarrage 
     */

    public void demarrer(int hauteur, int largeur, int nbreRobot) {
        this.modele.creationGrille(hauteur, largeur, nbreRobot);
        this.modele.creationCaseGraphique(hauteur, largeur);
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

}
