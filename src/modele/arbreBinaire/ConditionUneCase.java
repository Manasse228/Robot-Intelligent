/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.arbreBinaire;

import controleur.Partie;
import modele.Position;
import modele.Robot;

/**
 *
 * @author 21416699
 */
public class ConditionUneCase implements Condition {

    public Robot roboti;

    @Override
    public boolean check(Partie partie, Robot robot) {

        boolean check = false;
        roboti = new Robot();

        Position position = roboti.nextPosition(robot.getDirection(), robot.getPosition());

        if (partie.quiEstLa(position, partie.getTriListCaseGraphique()) instanceof Robot) {
            check = true;
        }
        /*
         Il renvoi true or false 
         */
        return check;
    }

}
