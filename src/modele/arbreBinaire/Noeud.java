/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.arbreBinaire;

import java.util.ArrayList;
import modele.Partie;
import modele.Robot;

/**
 *
 * @author sergeokov
 */
public class Noeud extends ArbreBinaire {

    public ArbreBinaire feuilleG, feuilleD;
    ArrayList<ArbreBinaire> listArbreBinaire;
    String nom;
    ConstructionArbreBinaire constructionArbreBinaire;
    Robot robot;
    Partie partie;
    public Feuille feuille;

    public Noeud(String nom, Robot robot, Partie partie) {
        this.nom = nom;
        this.robot = robot;
        this.partie = partie;
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
        switch (constructionArbreBinaire.verification(this, this.robot, this.partie)) {
            case 1:
                resultat = this.getFeuilleD().parcourir();
                break;
            case 0:
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
