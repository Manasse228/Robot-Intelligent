/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Partie;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author 21416699
 */
public class Modele extends Observable {

    Partie partie;
    Grille grille;
    Position position;
    Robot robot = new Robot();
    Images images = new Images();
    Missile missile;
    ArrayList<CaseGraphique> listCaseGraphique;
    ArrayList<CaseGraphique> triListCaseGraphique;
    private ArrayList<Observer> listObserver;

    public Modele() {
        super();
        listCaseGraphique = new ArrayList<>();
        triListCaseGraphique = new ArrayList<>();
        listObserver = new ArrayList<>();
        partie = new Partie();
    }

    public void creationGrille(int hauteur, int largeur, int nbreRobot) {
        partie = new Partie();
        grille = new Grille(hauteur, largeur, nbreRobot);
        partie.setListBloc(grille.getListCase());
    }

    public void creationCaseGraphique(int hauteur, int largeur) {
        for (Case caz : this.partie.getListBloc()) {
            listCaseGraphique.add(new CaseGraphique(caz, images.renvoiImages(caz).getImage()));
        }

        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                for (CaseGraphique caseGraphique : listCaseGraphique) {
                    if (Position.egalite(caseGraphique.getCaze().position(), new Position(j, i)) == true) {
                        triListCaseGraphique.add(caseGraphique);
                    }
                }

            }
        }

        partie.setListCaseGraphique(listCaseGraphique);
        partie.setListRobot(grille.getListRobot());
        partie.setTriListCaseGraphique(triListCaseGraphique);

    }

    public void notifyObserver() {
        for (Observer obs : this.listObserver) {
            obs.update(this, partie);
        }
    }

    @Override
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public ArrayList<Observer> getListObserver() {
        return listObserver;
    }

    public void setListObserver(ArrayList<Observer> listObserver) {
        this.listObserver = listObserver;
    }

}
