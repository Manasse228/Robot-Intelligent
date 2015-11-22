/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.Case;
import modele.CaseVide;
import modele.Missile;
import modele.Modele;
import controleur.Partie;
import modele.Position;
import modele.Robot;
import modele.CaseGraphique;
import modele.Images;

/**
 *
 * @author 21416699
 */
public class StgAvancer implements Strategy {

    Partie partie;
    Robot robot;
    Position position, oldPosition;
    Robot roboti;
    Images images;
    Missile missile;
    Modele modele;

    public StgAvancer(Partie partie, Robot robot, Modele modele) {
        roboti = new Robot();
        images = new Images();
        missile = new Missile();

        this.partie = partie;
        this.robot = robot;
        this.modele = modele;
        oldPosition = robot.getPosition();
    }

    @Override
    public Partie renvoyerPartie() {

        position = roboti.nextPosition(this.robot.getDirection(), this.robot.getPosition());

        if (roboti.existPosition(position, this.partie.getListBloc()) == true) {
            Case element = this.partie.quiEstLa(position, this.partie.getTriListCaseGraphique());
            switch (element.toString()) {
                case "CaseVide":
                    Robot robo = new Robot(this.robot.getEnergie() - 1, position, this.robot.getDirection(), false, this.robot.getNom());

                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        //On remplace l'ancienne position par une case vide
                        if (this.partie.getTriListCaseGraphique().get(i).getCaze() instanceof Robot) {
                            if (Position.egalite(oldPosition, this.partie.getTriListCaseGraphique().get(i).getCaze().position()) == true) {
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(oldPosition),
                                        images.renvoiImages(new CaseVide(oldPosition)).getImage()));
                            }
                        }

                        //Le robot se met en place sur sa nouvelle position
                        if (Position.egalite(position, this.partie.getTriListCaseGraphique().get(i).getCaze().position()) == true) {
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(robo, images.renvoiImages(robo).getImage()));
                        }

                    }

                    break;
                case "Robot":
                    System.err.println("je m'arrete " + this.robot.getPosition());
                    break;
                case "Missile":
                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), oldPosition) == true) {
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(oldPosition),
                                    images.renvoiImages(new CaseVide(oldPosition)).getImage()));
                        }
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {

                            if (this.robot.isBouclier() == false) {
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTue().getImage()));
                                this.modele.notifyObserver();
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                        images.renvoiImages(new CaseVide(position)).getImage()));
                            } else {
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(this.robot,
                                        images.renvoiImages(this.robot).getImage()));
                            }

                        }
                    }
                    break;
            }

        } else {//La position à laquelle on veut accéder n'existe pas

        }

        return this.partie;
    }

}
