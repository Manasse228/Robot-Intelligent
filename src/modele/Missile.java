/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author 21416699
 */
public class Missile implements Case {

    Position position;
    Grille grille;
    Robot robot;
    Direction direction;

    @Override
    public Position position() {
        return position;
    }

    public Missile(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;

    }

    public void effacerCesTracesArriere(Direction direction, Position position, ArrayList<Case> listCase) {
        Position pos = new Position(0, 0);
        switch (direction) {
            case Nord:
                pos = robot.nextPosition(direction.Sud, position);
                break;
            case Sud:
                pos = robot.nextPosition(direction.Nord, position);
                break;
            case Est:
                pos = robot.nextPosition(direction.Ouest, position);
                break;
            case Ouest:
                pos = robot.nextPosition(direction.Est, position);
                break;
        }
        if ("Missile".equals(robot.detecteurElement(pos, listCase))) {
            for (int i = 0; i < grille.getListCase().size(); i++) {
                if (Position.egalite(grille.getListCase().get(i).position(), pos) == true) {
                    Missile missile = (Missile) grille.getListCase().get(i);
                    if (missile.direction == direction) {
                        grille.getListCase().set(i, new CaseVide(position));
                    }
// si par hasard deux missiles se suivent le dernier peut être 
                }
            }
        }
    }

    public void seDeplacer(Direction direction, Position position, ArrayList<Case> listCase) {
        String nom = "";
        this.position = robot.nextPosition(direction, position);

        if (robot.existPosition(position, listCase)) {
            nom = robot.detecteurElement(position, listCase);
            switch (nom) {
                case "CaseVide":
                    for (int i = 0; i < grille.getListCase().size(); i++) {
                        if (Position.egalite(grille.getListCase().get(i).position(), position) == true) {
                            grille.getListCase().set(i, new Missile(position, direction));
                            effacerCesTracesArriere(direction, position, listCase);
                            seDeplacer(direction, this.position, listCase);
                        }
                    }
                    break;
                case "Robot":
                    for (int i = 0; i < grille.getListCase().size(); i++) {
                        if (Position.egalite(grille.getListCase().get(i).position(), position) == true) {
                            //le robot en question peut mourir et on change sa position par une cae vide
                            grille.getListCase().set(i, new CaseVide(position));
                        }
                    }
                    break;
                case "Missile":
                    for (int i = 0; i < grille.getListCase().size(); i++) {
                        if (Position.egalite(grille.getListCase().get(i).position(), position) == true) {
                            //deux missiles se rencontre 
                            grille.getListCase().set(i, new CaseVide(position));
                        }
                    }
                    break;
                default:
                    break;
            }
        } else {//le missile sort de al grille

        }
    }

    public void feu() {

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Missile";
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    

}
