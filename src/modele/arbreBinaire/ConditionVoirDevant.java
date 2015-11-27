
package modele.arbreBinaire;

import java.util.ArrayList;
import controleur.Partie;
import modele.Position;
import modele.Robot;


public class ConditionVoirDevant implements Condition {

    public ArrayList<Position> listPositionRobot;
    public Robot roboti;

    @Override
    public boolean check(Partie partie, Robot robot) {
        listPositionRobot = new ArrayList<>();
        roboti = new Robot();

        Position position = roboti.nextPosition(robot.getDirection(), robot.getPosition());

        while (roboti.existPosition(position, partie.getListBloc()) == true) {
            if (partie.quiEstLa(position, partie.getTriListCaseGraphique()) instanceof Robot) {
                listPositionRobot.add(position);
            }
            position = roboti.nextPosition(robot.getDirection(), position);
        }
        /*
         Retourne true si la liste est vide c'est à dire 
         qu'il voit aucun robot devant lui jusqu'au bout 
         de la ligne
         */
        if(listPositionRobot.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

}
