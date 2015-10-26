/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.arbreBinaire;

import java.awt.PageAttributes;
import java.util.ArrayList;
import modele.Direction;
import modele.Missile;
import modele.Partie;
import modele.Position;
import modele.Robot;

/**
 *
 * @author sergeokov
 */
public class ConstructionArbreBinaire {

    public Partie partie;
    public Robot robot;
    public Robot roboti = new Robot();

    public ArrayList<Position> listPositionRobot;
    public ArrayList<Position> listPositionMissile;

    public Arbre arbre;

    public Noeud noeudVérification_Sante;
    public Noeud noeudCoincer;
    public Noeud noeudVoirDevant;
    public Noeud noeudUneCase;
    public Noeud noeudMissileDevant;

    public Feuille feuille;

    public ConstructionArbreBinaire() {
    }

    public ConstructionArbreBinaire(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;

        noeudVérification_Sante = new Noeud("Vérification_Sante", robot, partie);
        noeudCoincer = new Noeud("Coincer", robot, partie);
        noeudVoirDevant = new Noeud("Voir_Devant", robot, partie);
        noeudUneCase = new Noeud("Une_Case", robot, partie);
        noeudMissileDevant = new Noeud("Missile_Devant", robot, partie);
        feuille = new Feuille();
        arbre = new Arbre(robot, partie);

        noeudVérification_Sante.createNoeud(new Feuille(feuille.rienFaire()), noeudCoincer);
        noeudCoincer.createNoeud(new Feuille(feuille.Tourner()), noeudVoirDevant);
        noeudVoirDevant.createNoeud(noeudUneCase, noeudMissileDevant);
        noeudUneCase.createNoeud(new Feuille(feuille.lancerLaser()), new Feuille(feuille.lancerMissile()));
        noeudMissileDevant.createNoeud(new Feuille(feuille.avancer()), new Feuille(feuille.aleatoire()));

        arbre.add(noeudVérification_Sante);

    }

    public String action() {
        return arbre.parcourir();
    }

    public int verification(Noeud noeud, Robot robot, Partie partie) {
        int result = 0;
        switch (noeud.getNom()) {
            case "Vérification_Sante":
                result = checkSante(robot);
                break;
            case "Coincer":
                result = checkCoincer(robot, partie);
                break;
            case "Voir_Devant":
                result = checkVoirDevant(robot, partie);
                break;
            case "Une_Case":
                result = checkUneCase(robot, partie);
                break;
            case "Missile_Devant":
                checkMissileDevant(robot, partie);
                break;
            default:
                break;
        }
        return result;
    }

    public int checkSante(Robot robot) {
        return (robot.getEnergie() <= 0) ? 1 : 0;
    }

    public int checkCoincer(Robot robot, Partie partie) {
        Position position = roboti.nextPosition(robot.getDirection(), robot.getPosition());
        return (roboti.existPosition(position, partie.getListBloc()) == true) ? 1 : 0;
    }

    public int checkVoirDevant(Robot robot, Partie partie) {
        Missile missile = new Missile();
        listPositionRobot = new ArrayList<>();
        Position position = roboti.nextPosition(robot.getDirection(), robot.getPosition());
        while (roboti.existPosition(position, partie.getListBloc()) == true) {
            if ("Robot".equals(missile.detecteurElement(position, partie.getTriListCaseGraphique()))) {
                listPositionRobot.add(position);
            }
            position = roboti.nextPosition(robot.getDirection(), position);
        }
        return (listPositionRobot.isEmpty()) ? 1 : 0;
    }

    public int checkMissileDevant(Robot robot, Partie partie) {
        Position position = roboti.nextPosition(robot.getDirection(), robot.getPosition());
        Missile missile = new Missile();
        listPositionMissile = new ArrayList<>();
        if (roboti.existPosition(position, partie.getListBloc()) == true) {
            if ("Missile".equals(missile.detecteurElement(position, partie.getTriListCaseGraphique()))) {
                Missile mis = missile.renvoiMissile(position, partie.getTriListCaseGraphique());
                switch (missile.getDirection()) {
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
        return (listPositionMissile.isEmpty()) ? 1 : 0;
    }

    public int checkUneCase(Robot robot, Partie partie) {
        boolean check = false;
        Missile missile = new Missile();
        Position position = roboti.nextPosition(robot.getDirection(), robot.getPosition());
        if ("Robot".equals(missile.detecteurElement(position, partie.getTriListCaseGraphique()))) {
            check = true;
        }
        return (check == true) ? 0 : 1;
    }

    public ArrayList<Position> getListPositionRobot() {
        return listPositionRobot;
    }

    public void setListPositionRobot(ArrayList<Position> listPositionRobot) {
        this.listPositionRobot = listPositionRobot;
    }

    public ArrayList<Position> getListPositionMissile() {
        return listPositionMissile;
    }

    public void setListPositionMissile(ArrayList<Position> listPositionMissile) {
        this.listPositionMissile = listPositionMissile;
    }

}
