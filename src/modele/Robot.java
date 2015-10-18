/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 21416699
 */
public class Robot implements Case {

    Position position;
    Missile missile;
    Direction direction;
    private int energie;
    private int laser;
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

    Robot rob = null;

    public Robot seDeplacer(Direction direction, Position position, ArrayList<Case> listCase, Robot robot) {

        Position pos = nextPosition(direction, position);
        rob = new Robot(robot.getEnergie(), position, direction, robot.getNom());
        if (existPosition(pos, listCase)) {

            String element = detecteurElement(pos, listCase);
            switch (element) {
                case "CaseVide":
                    rob = new Robot(robot.getEnergie() - 5, pos, direction, robot.getNom());
//                    System.err.println("Robot position: " + rob.getPosition() + " direction: " + rob.getDirection() + " energie: " + rob.getEnergie() + " nom: " + rob.getNom());
                    break;
                case "Robot":

                    break;
                case "Missile":

                    break;

            }
        } else {
//            for (PositionDirection positionDirection : listPositionPossible(position, listCase)) {
//                System.err.println("Me voici " + positionDirection);
//            }
// Random rand = new Random();
// System.out.println("taille "+listPositionPossible(position, listCase).size() );
//            int i = rand.nextInt(((listPositionPossible(position, listCase).size() - 1) + 1) - 0);
//            System.out.println("sort "+i);
//            PositionDirection positionDirection = listPositionPossible(position, listCase).get(i);
//            return rob = new Robot(robot.getEnergie(), positionDirection.position, positionDirection.direction, robot.getNom());
            return rob = new Robot(robot.getEnergie(), position, direction, robot.getNom());
        }

        return rob;
    }

    protected class PositionDirection {

        Direction direction;
        Position position;

        public PositionDirection(Direction direction, Position position) {
            this.direction = direction;
            this.position = position;
        }

        @Override
        public String toString() {
            return "Direction " + direction + "Position " + position;
        }

    }

    public ArrayList<PositionDirection> listPositionPossible(Position positionOriginal, ArrayList<Case> listCase) {
        Position position;
        ArrayList<PositionDirection> list = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            position = nextPosition(dir, positionOriginal);
            if (existPosition(position, listCase) == true) {
                list.add(new PositionDirection(dir, position));
            }
        }
        return list;
    }

    public void attaquer(Robot robot) {
        if (!robot.isBouclier()) {
            robot.setEnergie(0);
        } else {
            robot.setEnergie(0);
        }
    }

    public int recuperationEnergie(Robot robot) throws InterruptedException {
        System.err.println("Energie robot " + robot.getEnergie());
        if (robot.getEnergie() < 15) {
            Thread.sleep(6000);
        } else {
            return 2;
        }
        return 1;
    }

    public void activerBouclier(Robot robot) {
        this.bouclier = true;
    }

    public void desactiverBouclier(Robot robot) {
        this.bouclier = false;
    }

    public void reconstitutionEnergie(Robot robot) {

    }

    public void attaquer(Robot a, Robot b) {

    }

    public void lancerMissile(Robot robot) {
//        missile = new Missile(robot.getPosition());

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
