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
    private int laser;
    private boolean bouclier = false;
    private boolean mort;
    private boolean seDeplace;
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
        this.seDeplace = false;
        this.bouclier = true;
        this.direction = direction;
        this.nom = nom;
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
                pos = new Position(position.getX(), position.getY() - 1);
                break;
            case Sud:
                pos = new Position(position.getX(), position.getY() + 1);
                break;
            case Est:
                pos = new Position(position.getX() + 1, position.getY());
                break;
            case Ouest:
                pos = new Position(position.getX() - 1, position.getY());
                break;
            default:
                break;
        }
        return pos;
    }
    /*
     robot peut savoir s'il ya un autre robot sur sa ligne ou sur sa colonne
     s'il ya personne il se déplace dans sa direction 
     pour tourner le robot prend l'est ou l'ouest si sa direction est le nord
     sil veut faire feu il va vérifier sil ya quelqun devant lui et si c'est le cas il lance laser sinon missile
     */

    // le robot est au repos sur son energie est < 10 
    public void changementDeDirection() {

    }

    public Robot seDeplacer(Direction direction, Position position, ArrayList<Case> listCase, Robot robot) {
        
        Robot rob = null;
        Position pos = nextPosition(direction, position);
        
        if (existPosition(pos, listCase)) {

            String element = detecteurElement(pos, listCase);
            switch(element){
                case "CaseVide":
                    System.err.println("case vide "+pos);
                   rob = new Robot(robot.getEnergie(), pos, robot.getDirection(),robot.getNom()); 
                    break;
                case "Robot":
                    
                    break;
                case "Missile":
                    
                    break;
                  
            }
        } else {
            robot.setDirection(Direction.getRandomDirection());
//            System.err.println("Nouvelle direction "+)
            seDeplacer(direction, position, listCase, robot);
            //CHangement de direction
        }
        return rob;
    }

    public void attaquer(Robot robot) {
        if (!robot.isBouclier()) {
            robot.setEnergie(0);
        } else {
            robot.setEnergie(0);
        }
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

    public boolean isSeDeplace() {
        return seDeplace;
    }

    public void setSeDeplace(boolean seDeplace) {
        this.seDeplace = seDeplace;
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
