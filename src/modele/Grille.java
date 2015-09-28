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
public class Grille {

    int hauteur, largeur;
    Random rand = new Random();
    ArrayList<Case> listCase = new ArrayList<Case>();
    ArrayList<Robot> listRobot = new ArrayList<Robot>();
    ArrayList<Position> listPositionRobots = new ArrayList<Position>();
    Position robotPosition;
    Direction direction;

    public final void creationRobot(int hauteur, int largeur, int nbreRobots) {
        for (int i = 0; i < nbreRobots; i++) {
            emplacementRobot(hauteur, largeur);
        }
        for (Position list : listPositionRobots) {
            Case caseRobot = new Robot(12, list, Direction.getRandomDirection(), Utils.nomRobot());
            listRobot.add((Robot) caseRobot);
            listCase.add(caseRobot);
        }
    }

    public Position emplacementRobot(int hauteur, int largeur) {
        Position position;
        boolean verif = false;

        int x = rand.nextInt(((largeur - 1) + 1) - 0);
        int y = rand.nextInt(((hauteur - 1) + 1) - 0);
        position = new Position(x, y);

        for (Position pos : listPositionRobots) {
            if (Position.egalite(pos, position)) {
                verif = true;
            }
        }
        if (verif == true) {
            emplacementRobot(hauteur, largeur);
        } else {
            listPositionRobots.add(position);
        }
        return position;
    }

    public boolean verifNbreRobot(int hauteur, int largeur, int nbreRobot) {
        return (nbreRobot > hauteur * largeur);
    }

    public Grille(int hauteur, int largeur, int nbreRobots) {

        creationRobot(hauteur, largeur, nbreRobots);
        int pointeur = 0;

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {

                for (Position posi : listPositionRobots) {
                    if (Position.egalite(posi, new Position(i, j)) == false) {
                        pointeur++;
                    }
                }
                if (pointeur == listPositionRobots.size()) {
                    Case caseVide = new CaseVide(new Position(i, j));
                    listCase.add(caseVide);
                }
                pointeur = 0;
            }
        }

    }

    public ArrayList<Case> getListCase() {
        return listCase;
    }

    public void setListCase(ArrayList<Case> listCase) {
        this.listCase = listCase;
    }

    public ArrayList<Position> getListPositionRobots() {
        return listPositionRobots;
    }

    public void setListPositionRobots(ArrayList<Position> listPositionRobots) {
        this.listPositionRobots = listPositionRobots;
    }

    public ArrayList<Robot> getListRobot() {
        return listRobot;
    }

    public void setListRobot(ArrayList<Robot> listRobot) {
        this.listRobot = listRobot;
    }

}
