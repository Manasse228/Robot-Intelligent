/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.arbreBinaire;

import java.util.ArrayList;
import java.util.Iterator;
import controleur.Partie;
import modele.Robot;

/**
 *
 * @author sergeokov
 */
public class Arbre extends ArbreBinaire {

    ArrayList listArbreBinaire;
    ConstructionArbreBinaire constructionArbreBinaire;
    public Feuille feuille;
    Robot robot;
    Partie partie;

    public Arbre(Robot robot, Partie partie) {
        this.robot = robot;
        this.partie = partie;
        listArbreBinaire = new ArrayList();
        constructionArbreBinaire = new ConstructionArbreBinaire();
        feuille = new Feuille();
    }

    @Override
    public String parcourir() {
        String resultat = "";
        Iterator iterator = listArbreBinaire.iterator();
        while (iterator.hasNext()) {
            ArbreBinaire arbreBinaire = (ArbreBinaire) iterator.next();
            Noeud noeud = (Noeud) arbreBinaire;
            switch (constructionArbreBinaire.verification(noeud.condition, this.robot, this.partie)) {
                case 0:
                    resultat = noeud.getFeuilleD().parcourir();
                    break;
                case 1:
                    resultat = noeud.getFeuilleG().parcourir();
                    break;
            }
        }
        return resultat;
    }

    @Override
    public void add(ArbreBinaire arbreBinaire) {
        listArbreBinaire.add(arbreBinaire);
    }

    public ArrayList getListArbreBinaire() {
        return listArbreBinaire;
    }

    public void setListArbreBinaire(ArrayList listArbreBinaire) {
        this.listArbreBinaire = listArbreBinaire;
    }

}
