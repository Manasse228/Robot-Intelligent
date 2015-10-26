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
    public int hauteur, largeur, nbreRobot;

    public Controleur(Modele modele) {
        this.modele = modele;
    }

    public void demarrer(int hauteur, int largeur, int nbreRobot) {
        modele.creationGrille(hauteur, largeur, nbreRobot);
        modele.creationCaseGraphique(hauteur, largeur);
    }

    public void verification(int hauteur, int largeur, int nbreRobot) {
        if (nbreRobot > hauteur * largeur) {
            /// One lance pas le jeu
        }
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getNbreRobot() {
        return nbreRobot;
    }

    public void setNbreRobot(int nbreRobot) {
        this.nbreRobot = nbreRobot;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

}
