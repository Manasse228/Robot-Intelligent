package modele;

import java.util.ArrayList;

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
    public Robot(int energie, Position position, Direction direction, boolean bouclier, String nom) {

        this.energie = energie;
        this.position = position;
        this.mort = false;
        this.repos = false;
        this.bouclier = bouclier;
        this.direction = direction;
        this.nom = nom;
    }

    public Robot() {

    }

    /*
     Renvoi l'état d'un robot
     */
    public boolean etatRobot(ArrayList<Robot> listRobot, Robot robot) {
        boolean etat = false;
        for (Robot r : listRobot) {
            if (r.getNom().equals(robot.getNom())) {
                etat = r.isMort();
            }
        }
        return etat;
    }

    /*
     Verification de l'existence d'une position pour éviter des sorties de grille
     */
    public boolean existPosition(Position position, ArrayList<Case> listCase) {
        boolean casePresent = false;
        for (Case c : listCase) {
            if (Position.egalite(position, c.position()) == true) {
                casePresent = true;
            }
        }
        return casePresent;
    }

    /*
     Détcteur d'élement et connaitre la nature d'une case par un robot
     */
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

    /*
     Renvoi de la position suivante en fonction de la direction
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
