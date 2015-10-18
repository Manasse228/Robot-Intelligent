/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import vue.CaseGraphique;
import vue.Images;

/**
 *
 * @author 21416699
 */
public class Modele extends Observable {

    Partie partie = new Partie();
    Grille grille;
    Position position;
    Robot robot = new Robot();
    Images images = new Images();
    Missile missile;
    ArrayList<CaseGraphique> listCaseGraphique = new ArrayList<>();
    ArrayList<CaseGraphique> triListCaseGraphique = new ArrayList<>();
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    public Modele() {
        super();
    }

    public void creationGrille(int hauteur, int largeur, int nbreRobot) {
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

    public void lancerMissile(Direction direction, Position position, Partie partie, Images images, Missile missileParam) throws InterruptedException {
        this.position = robot.nextPosition(direction, position);

        if (robot.existPosition(this.position, partie.getListBloc())) {
            String element = missileParam.detecteurElement(this.position, partie.getTriListCaseGraphique());
            switch (element) {
                case "CaseVide":
                    /*
                     Dans le cas ou le missile veut accéder à une case vide
                     */
                    for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), this.position) == true) {
//                            Missile missile = new Missile(this.position, direction);
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(missile, images.renvoiImages(missile).getImage()));
//                            missileParam.effacerCesTracesArriere(direction, this.position, partie, images);
                            notifyObserver();
                            lancerMissile(direction, this.position, partie, images, missileParam);
                        }
                    }
                    break;
                case "Robot":
                    /*
                     Dans le cas ou le missile a un robot devant lui
                     */
                    for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), this.position) == true) {
                            //le robot en question peut mourir et on change sa position par une cae vide
//                            missileParam.effacerCesTracesArriere(direction, this.position, partie, images);
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTue().getImage()));
                            notifyObserver();
                            Thread.sleep(2000);
                            
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(this.position),
                                    images.renvoiImages(new CaseVide(this.position)).getImage()));
                            partie.getListBloc().set(i, new CaseVide(this.position));
                            notifyObserver();

                            /*
                             Le robot qui vient de nous quitter (c'est à dire mort) doit quitter la liste des robots
                             */
                            for (int j = 0; j < partie.getListRobot().size(); j++) {
                                if (Position.egalite(partie.getListRobot().get(j).getPosition(), this.position) == true) {
                                    partie.getListRobot().get(j).setMort(true);
                                }
                            }
                            
                        }
                    }
                    break;
                case "Missile":
                    /*
                     Dans le cas ou deux missiles se rencontrent
                     */
                    for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(partie.getListBloc().get(i).position(), this.position) == true) {
                            //deux missiles se rencontre 
                            partie.getListBloc().set(i, new CaseVide(this.position));
                            partie.getListBloc().set(i, new CaseVide(position));
                        }
                    }
                    break;
                default:
                    break;
            }
        } else {//le missile sort de al grille
//            missileParam.effacerCesTracesArriere(direction, this.position, partie, images);
            notifyObserver();
            System.err.println("Missile sort");
        }
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
