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


    Random rand = new Random();
    ArrayList<Case> listCase = new ArrayList<Case>();
    ArrayList<Case> listCaseVide = new ArrayList<Case>();
    ArrayList<Case> listCaseRobot = new ArrayList<Case>();
    ArrayList<Position> listPositionRobots = new ArrayList<Position>();
    Position robotPosition;
    Direction direction;

    public final void creationRobot(int x, int y, int nbreRobots) {
        for (int i = 0; i < nbreRobots; i++) {
            emplacementRobot(x, y);
        }
        for (Position list : listPositionRobots) {
            Case caseRobot = new Robot(12, list, Direction.getRandomDirection(), Utils.nomRobot());
            listCase.add(caseRobot);
            listCaseRobot.add(caseRobot);
        }
    }

    public Position emplacementRobot(int x, int y) {
        Position position;
        boolean verif = false;

        int x1 = rand.nextInt(((x - 1) + 1) - 0);
        int y1 = rand.nextInt(((y - 1) + 1) - 0);
        position = new Position(x1, y1);

        for (Position pos : listPositionRobots) {
            if (Position.egalite(pos, position)) {
                verif = true;
            }
        }
        if (verif == true) {
            emplacementRobot(x, y);
        } else {
            listPositionRobots.add(position);
        }
        return position;
    }

    public boolean verifNbreRobot(int x, int y, int nbreRobot) {
        return (nbreRobot > x * y);
    }

    public Grille(int x, int y, int nbreRobots) {

        creationRobot(x, y, nbreRobots);
        int pointeur = 0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

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

    public ArrayList<Case> getListCaseVide() {
        return listCaseVide;
    }

    public void setListCaseVide(ArrayList<Case> listCaseVide) {
        this.listCaseVide = listCaseVide;
    }

    public ArrayList<Case> getListCaseRobot() {
        return listCaseRobot;
    }

    public void setListCaseRobot(ArrayList<Case> listCaseRobot) {
        this.listCaseRobot = listCaseRobot;
    }

}
