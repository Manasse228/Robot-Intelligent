/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.CaseVide;
import modele.Missile;
import modele.Partie;
import modele.Position;
import modele.Robot;
import vue.CaseGraphique;
import vue.Images;

/**
 *
 * @author 21416699
 */
public class StgAvancer implements Strategy {

    Partie partie;
    Robot robot;
    Position position, oldPosition;
    Robot roboti = new Robot();
    Images images = new Images();

    public StgAvancer(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;
        oldPosition = this.robot.getPosition();
    }

    @Override
    public Partie renvoyerPartie() {

        this.position = roboti.nextPosition(this.robot.getDirection(), this.robot.getPosition());

        if (roboti.existPosition(this.position, this.partie.getListBloc())) {
            String element = roboti.detecteurElement(this.position, this.partie.getListBloc());
            switch (element) {
                case "CaseVide":
                    Robot robo = new Robot(this.robot.getEnergie() - 1, this.position, this.robot.getDirection(), this.robot.getNom());

                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {

                        //On remplace l'ancienne position par une case vide
                        if ("Robot".equals(this.partie.getTriListCaseGraphique().get(i).getCaze().toString())) {
                            if (Position.egalite(oldPosition, this.partie.getTriListCaseGraphique().get(i).getCaze().position()) == true) {
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(oldPosition),
                                        images.renvoiImages(new CaseVide(oldPosition)).getImage()));
                            }
                        }

                        //Le robot se met en place sur sa nouvelle position
                        if (Position.egalite(this.position, this.partie.getTriListCaseGraphique().get(i).getCaze().position()) == true) {
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(robo, images.renvoiImages(robo).getImage()));
                            for (int j = 0; j < this.partie.getListRobot().size(); j++) {
                                if (robot.getNom().equals(this.partie.getListRobot().get(j).getNom())) {
                                    this.partie.getListRobot().set(j, robot);
                                }
                            }
                        }

                    }
//                  
                    break;
                case "Robot":

                    break;
                case "Missile":

                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), this.position) == true) {
                            Robot rob = (Robot) this.partie.getTriListCaseGraphique().get(i).getCaze();

                            //Le robot na pas activé son bouclier
                            if (rob.isBouclier() == false) {
                                //On affiche une effet de collission entre un robot et une missile
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTue().getImage()));
                                //On remplace l'emplacement du robot par une case vide car il a rendu l'âme
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(this.position),
                                        images.renvoiImages(new CaseVide(this.position)).getImage()));

                                for (Robot r : this.partie.getListRobot()) {
                                    if (Position.egalite(this.position, rob.getPosition())) {
                                        this.partie.getListRobot().remove(r);
                                    }
                                }

                                for (Missile m : partie.getListMissile()) {
                                    if (m.getNom().equals(robot.getNom())) {
                                        partie.getListMissile().remove(m);
                                    }
                                }

                            } else {
                                // Le robot a activé son bouclier   
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
