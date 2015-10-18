/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.Partie;
import modele.Position;
import modele.Robot;

/**
 *
 * @author 21416699
 */
public class StgLancerLaser implements Strategy {

    Partie partie;
    Robot robot;
    Robot roboti = new Robot();
    Position position;

    public StgLancerLaser(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;
    }

    @Override
    public Partie renvoyerPartie() {

        this.position = roboti.nextPosition(this.roboti.getDirection(), this.roboti.getPosition());

        if (roboti.existPosition(this.position, this.partie.getListBloc())) {
            for(int i=0;i<this.partie.getTriListCaseGraphique().size();i++){
                
            }
        }

        return this.partie;
    }

}
