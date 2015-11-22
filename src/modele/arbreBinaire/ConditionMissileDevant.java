/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.arbreBinaire;

import java.util.ArrayList;
import modele.Direction;
import modele.Missile;
import controleur.Partie;
import modele.Position;
import modele.Robot;

/**
 *
 * @author 21416699
 */
public class ConditionMissileDevant implements Condition {

    public Robot roboti;
    Missile missile;
    public ArrayList<Position> listPositionMissile;

    @Override
    public boolean check(Partie partie, Robot robot) {
        missile = new Missile();
        roboti = new Robot();
        listPositionMissile = new ArrayList<>();

        Position position = roboti.nextPosition(robot.getDirection(), robot.getPosition());

        if (roboti.existPosition(position, partie.getListBloc()) == true) {
            if (partie.quiEstLa(position, partie.getTriListCaseGraphique()) instanceof Missile) {
                Missile mis = missile.renvoiMissile(position, partie.getTriListCaseGraphique());
                switch (mis.getDirection()) {
                    case Est:
                        if (robot.getDirection() == Direction.Ouest) {
                            listPositionMissile.add(position);
                        }
                        break;
                    case Nord:
                        if (robot.getDirection() == Direction.Sud) {
                            listPositionMissile.add(position);
                        }
                        break;
                    case Ouest:
                        if (robot.getDirection() == Direction.Est) {
                            listPositionMissile.add(position);
                        }
                        break;
                    case Sud:
                        if (robot.getDirection() == Direction.Nord) {
                            listPositionMissile.add(position);
                        }
                        break;
                }

            }
        }
        /*
         Si la liste est vide c'est qu'il n'ya aucun missile qui arrive tout
         droit sur lui et il renvoi true dans ce cas
         */
        if(listPositionMissile.isEmpty()){
           return false; 
        }else{
            return true;
        }
    }

}
