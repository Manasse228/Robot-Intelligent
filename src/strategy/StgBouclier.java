/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import controleur.Partie;
import modele.Position;
import modele.Robot;
import modele.CaseGraphique;

public class StgBouclier implements Strategy {

    Partie partie;
    Robot robot;

    public StgBouclier(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;
    }

    @Override
    public Partie renvoyerPartie() {

        for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
            CaseGraphique caz = this.partie.getTriListCaseGraphique().get(i);
            if ("Robot".equals(caz.getCaze().toString())) {
                Robot rob = (Robot) caz.getCaze();
                // Changement de l'état du bouclier
                this.robot.setBouclier(!this.robot.isBouclier());
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

        return this.partie;
    }

}
