/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.arbreBinaire;

import java.util.ArrayList;
import controleur.Partie;
import modele.Robot;

/**
 *
 * @author sergeokov
 */
public class Noeud extends ArbreBinaire {

    public ArbreBinaire feuilleG, feuilleD;
    ArrayList<ArbreBinaire> listArbreBinaire;
    Condition condition;
    ConstructionArbreBinaire constructionArbreBinaire;
    Robot robot;
    Partie partie;
    public Feuille feuille;

    public Noeud(Condition condition, Robot robot, Partie partie) {
        this.robot = robot;
        this.partie = partie;
        this.condition = condition;
        feuille = new Feuille();
        constructionArbreBinaire = new ConstructionArbreBinaire();
        listArbreBinaire = new ArrayList<>();
    }

    @Override
    public void createNoeud(ArbreBinaire arbreGauche, ArbreBinaire arbreDroite) {
        this.setFeuilleG(arbreGauche);
        this.setFeuilleD(arbreDroite);
    }

    @Override
    public String parcourir() {
        String resultat = "";
        switch (constructionArbreBinaire.verification(condition, this.robot, this.partie)) {
            case 0:
                resultat = this.getFeuilleD().parcourir();
                break;
            case 1:
                resultat = this.getFeuilleG().parcourir();
                break;
        }
        return resultat;
    }

    public ArbreBinaire getFeuilleG() {
        return this.feuilleG;
    }

    public void setFeuilleG(ArbreBinaire feuilleG) {
        this.feuilleG = feuilleG;
    }

    public ArbreBinaire getFeuilleD() {
        return this.feuilleD;
    }

    public void setFeuilleD(ArbreBinaire feuilleD) {
        this.feuilleD = feuilleD;
    }

    public ArrayList<ArbreBinaire> getListArbreBinaire() {
        return listArbreBinaire;
    }

    public void setListArbreBinaire(ArrayList<ArbreBinaire> listArbreBinaire) {
        this.listArbreBinaire = listArbreBinaire;
    }

    @Override
    public String toString() {
        return "Noeud";
    }

}
