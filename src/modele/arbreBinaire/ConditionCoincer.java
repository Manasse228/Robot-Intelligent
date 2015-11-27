
package modele.arbreBinaire;

import controleur.Partie;
import modele.Position;
import modele.Robot;


public class ConditionCoincer implements Condition {

    public Robot roboti;

    @Override
    public boolean check(Partie partie, Robot robot) {
        roboti = new Robot();
        Position position = roboti.nextPosition(robot.getDirection(), robot.getPosition());
        /*
         Il renvoie false s'il est coincer
         */
        if (roboti.existPosition(position, partie.getListBloc()) == false) {
            return true;
        } else {
            return false;
        }
    }

}
