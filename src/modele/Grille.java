/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Random;
import vue.CaseGraphique;
import vue.Images;

/**
 *
 * @author 21416699
 */
public class Grille {

    int hauteur, largeur;
    Random rand = new Random();
    ArrayList<Case> listCase;
    ArrayList<Robot> listRobot;
    ArrayList<Position> listPositionRobots;
    Position robotPosition;
    Direction direction;

    public final void creationRobot(int hauteur, int largeur, int nbreRobots) {
        for (int i = 0; i < nbreRobots; i++) {
            emplacementRobot(hauteur, largeur);
        }
        for (Position list : listPositionRobots) {
            Case caseRobot = new Robot(20, list, Direction.getRandomDirection(), Utils.nomRobot());
            listRobot.add((Robot) caseRobot);
            listCase.add(caseRobot);
        }
    }

    public Position emplacementRobot(int hauteur, int largeur) {
        Position position;
        boolean verif = false;

        int x = rand.nextInt(((largeur - 1) + 1) - 0);
        int y = rand.nextInt(((hauteur - 1) + 1) - 0);
        position = new Position(y, x);

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

    public Grille(int hauteur, int largeur, int nbreRobots) {

        rand = new Random();
        listCase = new ArrayList<>();
        listRobot = new ArrayList<>();
        listPositionRobots = new ArrayList<>();

        creationRobot(hauteur, largeur, nbreRobots);
        int pointeur = 0;

        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {

                for (Position posi : listPositionRobots) {
                    if (Position.egalite(posi, new Position(j, i)) == false) {
                        pointeur++;
                    }
                }
                if (pointeur == listPositionRobots.size()) {
                    Case caseVide = new CaseVide(new Position(j, i));
                    listCase.add(caseVide);
                }
                pointeur = 0;
            }
        }

    }

    public Grille() {

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
