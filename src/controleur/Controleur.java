/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import modele.Modele;

/**
 *
 * @author sergeokov
 */
public class Controleur {

    public Modele modele;

    public Controleur(Modele modele) {
        this.modele = modele;
    }

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
