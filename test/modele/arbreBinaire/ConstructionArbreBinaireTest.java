package modele.arbreBinaire;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import modele.Case;
import modele.CaseVide;
import modele.Direction;
import modele.Missile;
import modele.Modele;
import modele.Position;
import modele.Robot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import strategy.StgRienFaire;

public class ConstructionArbreBinaireTest {

    Modele modele;
    StgRienFaire stgRienFaire;
    Robot robotE, robotO, robotS, robotN;
    ArrayList<Robot> listRobot;
    ArrayList<Case> listCase, triListCase;
    ConstructionArbreBinaire constructionArbreBinaire;

    @Before
    public void setUp() {
        /*
         Initialisations
         */
        modele = new Modele();
        listRobot = new ArrayList<>();
        listCase = new ArrayList<>();
        triListCase = new ArrayList();
        /*
         Création des robots pour les tests 
         */
        robotE = new Robot(10, new Position(0, 3), Direction.Est, true, "est");
        robotO = new Robot(17, new Position(3, 0), Direction.Ouest, true, "ouest");
        robotN = new Robot(0, new Position(2, 2), Direction.Nord, true, "nord1");
        robotS = new Robot(10, new Position(1, 1), Direction.Sud, true, "sud");
        /*
         Rangement des robots pour les tests dans une liste
         */
        listRobot.add(robotE);
        listRobot.add(robotO);
        listRobot.add(robotN);
        listRobot.add(robotS);
        /*
         Création d'une grille virtuelle dans listcase
         */
        int pointeur = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                for (Robot posi : listRobot) {
                    if (Position.egalite(posi.getPosition(), new Position(j, i)) == false) {
                        pointeur++;
                    }
                }
                if (pointeur == listRobot.size()) {
                    Case caseVide = new CaseVide(new Position(j, i));
                    listCase.add(caseVide);
                }
                pointeur = 0;
            }
        }
        for (Robot r : listRobot) {
            listCase.add(r);
        }
        /*
         Insertion du missile dans la liste
         */
        for (int i = 0; i < listCase.size(); i++) {
            if (Position.egalite(listCase.get(i).position(), new Position(2, 1)) == true) {
                listCase.set(i, new Missile(new Position(2, 1), Direction.Nord, "est"));
            }
        }
        /*
         Renvoie de la grille virtuelle à la partie virtuelle
         */
        /*
         Creation de la Vue virtuelle 
         */
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                for (Case c : listCase) {
                    if (Position.egalite(c.position(), new Position(j, i)) == true) {
                        triListCase.add(c);
                    }
                }
            }
        }
        modele.getPartie().setListBloc(triListCase);
    }

    @Test
    public void test() {
        /*
         Test pour savoir si le robot est vraiment à cette position sur la grille
         */
        String result = modele.getPartie().testQuiEstLa(robotE.getPosition(),
                modele.getPartie().getListBloc()).toString();
        assertEquals("Robot", result);
        /*
         Test pour parcourir l'arbre et retourner la feuille 
         * Le robot Ouest Tourne
         */
        constructionArbreBinaire = new ConstructionArbreBinaire(modele.getPartie(), robotO);
        String feuille = constructionArbreBinaire.action();
        assertEquals("Tourner", feuille);
        /*
         Le robot Est Tourne
         */
        constructionArbreBinaire = new ConstructionArbreBinaire(modele.getPartie(), robotE);
        feuille = constructionArbreBinaire.action();
        assertEquals("Tourner", feuille);
        /*
         Le robot Sud va avancer car il ya une missile qui arrive tout droit sur lui 
         */
        constructionArbreBinaire = new ConstructionArbreBinaire(modele.getPartie(), robotS);
        feuille = constructionArbreBinaire.action();
        assertEquals("Avancer", feuille);
        /*
         Le robot Nord ne fait rien car son énergie est à 0 ..
         il va se charger 
         */
        constructionArbreBinaire = new ConstructionArbreBinaire(modele.getPartie(), robotN);
        feuille = constructionArbreBinaire.action();
        assertEquals("RienFaire", feuille);
    }

}
