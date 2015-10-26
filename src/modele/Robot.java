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
public class Robot implements Case {

    Position position;
    Missile missile;
    Direction direction;
    private int energie;

    private boolean bouclier = false;
    private boolean mort;
    private boolean repos;
    public String nom;

    @Override
    public Position position() {
        return position;
    }

    /*
     Création d'un robot 
     */
    public Robot(int energie, Position position, Direction direction, String nom) {

        this.energie = energie;
        this.position = position;
        this.mort = false;
        this.repos = false;
        this.bouclier = true;
        this.direction = direction;
        this.nom = nom;
    }

    public Robot() {

    }

    public boolean etatRobot(ArrayList<Robot> listRobot, Robot robot) {
        boolean etat = false;
        for (Robot r : listRobot) {
            if (r.getNom().equals(robot.getNom())) {
                etat = r.isMort();
            }
        }
        return etat;
    }

    public boolean existPosition(Position position, ArrayList<Case> listCase) {
        boolean casePresent = false;
        for (Case c : listCase) {
            if (Position.egalite(position, c.position()) == true) {
                casePresent = true;
            }
        }
        return casePresent;
    }

    public String detecteurElement(Position pos, ArrayList<Case> listCase) {
        String element = "";
        if (existPosition(pos, listCase) == true) {
            for (Case c : listCase) {
                if (Position.egalite(pos, c.position()) == true) {
                    element = c.toString();
                }
            }
        }
        return element;
    }
    /* cette classe si on laisse le Robot en parametre et si le robot mourait suite à un missile recu 
     de la part de quelqun l'application va beuger car l'objet robot disparaitre 
     */

    public Position nextPosition(Direction direction, Position position) {
        Position pos = new Position(0, 0);
        switch (direction) {
            case Nord:
                pos = new Position(position.getY() - 1, position.getX());
                break;
            case Sud:
                pos = new Position(position.getY() + 1, position.getX());
                break;
            case Est:
                pos = new Position(position.getY(), position.getX() + 1);
                break;
            case Ouest:
                pos = new Position(position.getY(), position.getX() - 1);
                break;
            default:
                break;
        }
        return pos;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
        if (energie == 0) {
            mort = true;
        }
    }

    public boolean isBouclier() {
        return bouclier;
    }

    public void setBouclier(boolean bouclier) {
        this.bouclier = bouclier;
    }

    public boolean isMort() {
        return mort;
    }

    public void setMort(boolean mort) {
        this.mort = mort;
    }

    public boolean isRepos() {
        return repos;
    }

    public void setRepos(boolean repos) {
        this.repos = repos;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Robot";
    }
}
