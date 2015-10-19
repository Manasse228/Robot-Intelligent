/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.Iterator;
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
    Robot roboti;
    Images images;

    public StgAvancer(Partie partie, Robot robot) {
        roboti = new Robot();
        images = new Images();

        this.partie = partie;
        this.robot = robot;
        oldPosition = this.robot.getPosition();
    }

    @Override
    public Partie renvoyerPartie() {

        position = roboti.nextPosition(this.robot.getDirection(), this.robot.getPosition());

        if (roboti.existPosition(position, this.partie.getListBloc())) {
            String element = roboti.detecteurElement(position, this.partie.getListBloc());
            switch (element) {
                case "CaseVide":
                    Robot robo = new Robot(this.robot.getEnergie() - 1, position, this.robot.getDirection(), this.robot.getNom());

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
                    break;
                case "Robot":
                    System.err.println("je m'arrete");
                    break;
                case "Missile":
                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), oldPosition) == true) {
                            Robot rob = (Robot) this.partie.getTriListCaseGraphique().get(i).getCaze();

                            if (rob.isBouclier() == true) {

                                Iterator<Robot> iteRobot = this.partie.getListRobot().iterator();
                                Iterator<Missile> iteMissile = this.partie.getListMissile().iterator();

                                while (iteRobot.hasNext()) {
                                    Robot r = iteRobot.next();
                                    if (Position.egalite(r.getPosition(), rob.getPosition()) == true) {
                                        iteRobot.remove();
                                    }
                                }
                                while (iteMissile.hasNext()) {
                                    Missile m = iteMissile.next();
                                    if (Position.egalite(m.getPosition(), position) == true) {
                                        iteMissile.remove();
                                    }
                                }

                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(oldPosition),
                                        images.renvoiImages(new CaseVide(oldPosition)).getImage()));
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                        images.renvoiImages(new CaseVide(position)).getImage()));
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
