/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.Partie;
import modele.Position;
import modele.Robot;
import vue.CaseGraphique;

/**
 *
 * @author 21416699
 */
public class StgRienFaire implements Strategy {

    Partie partie;
    Robot robot;

    public StgRienFaire(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;
    }

    /*
     Augmenter l'énergie du robot
     */
    @Override
    public Partie renvoyerPartie() {

        if (this.robot.getEnergie() == 0) {
            this.robot.setEnergie(19);

            for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                CaseGraphique caz = this.partie.getTriListCaseGraphique().get(i);
                if ("Robot".equals(caz.getCaze().toString())) {
                    Robot rob = (Robot) caz.getCaze();
                    if (Position.egalite(this.robot.getPosition(), rob.getPosition())) {
                        this.partie.getTriListCaseGraphique().get(i).setCaze(this.robot);
                    }
                }
            }

            for (int j = 0; j < this.partie.getListRobot().size(); j++) {
                if (this.partie.getListRobot().get(j).getNom().equals(this.robot.getNom())) {
                    this.partie.getListRobot().set(j, this.robot);
                }
            }
        } else {
            System.err.println("on fait rien");
            //On fait rien
        }

        return this.partie;
    }

}
