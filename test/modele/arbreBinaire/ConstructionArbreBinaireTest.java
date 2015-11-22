package modele.arbreBinaire;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controleur.Controleur;
import java.util.ArrayList;
import modele.Case;
import modele.CaseGraphique;
import modele.CaseVide;
import modele.Direction;
import modele.Images;
import modele.Modele;
import modele.Position;
import modele.Robot;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import strategy.StgRienFaire;

/**
 *
 * @author scarmel
 */
public class ConstructionArbreBinaireTest {

    Modele modele;
    Controleur controleur;
    StgRienFaire stgRienFaire;
    Robot robotE, robotO, robotS, robotN;
    ArrayList<Robot> listRobot;
    ArrayList<Case> listCase;
    Images images;
    ArrayList<CaseGraphique> listCaseGraphique;
    ArrayList<CaseGraphique> triListCaseGraphique;
    ConstructionArbreBinaire constructionArbreBinaire;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        /*
         Initialisations
         */
        images = new Images();
        listCaseGraphique = new ArrayList<>();
        triListCaseGraphique = new ArrayList<>();
        modele = new Modele();
        listRobot = new ArrayList<>();
        listCase = new ArrayList<>();

        /*
         Création des robots pour les tests 
         */
        robotE = new Robot(10, new Position(0, 3), Direction.Est, true, "est");
        robotO = new Robot(17, new Position(3, 0), Direction.Ouest, true, "ouest");
        robotN = new Robot(0, new Position(2, 2), Direction.Nord, true, "nord");
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
         Renvoie de la grille virtuelle à la partie virtuelle
         */
        modele.getPartie().setListBloc(listCase);
        /*
         Creation de la Vue virtuelle 
         */
        for (Case caz : listCase) {
            listCaseGraphique.add(new CaseGraphique(caz, images.renvoiImages(caz).getImage()));
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                for (CaseGraphique caseGraphique : listCaseGraphique) {
                    if (Position.egalite(caseGraphique.getCaze().position(), new Position(j, i)) == true) {
                        triListCaseGraphique.add(caseGraphique);
                    }
                }

            }
        }

        modele.getPartie().setListCaseGraphique(listCaseGraphique);
        modele.getPartie().setTriListCaseGraphique(triListCaseGraphique);

        controleur = new Controleur(modele);
    }

    /**
     * Test of action method, of class ConstructionArbreBinaire.
     */
    @Test
    public void testAction() {
        /*
         Test pour savoir si le robot est vraiment à cette position sur la grille
         */
        String result = modele.getPartie().quiEstLa(robotE.getPosition(),
                modele.getPartie().getTriListCaseGraphique()).toString();
        assertEquals("Robot", result);
        /*
         Test pour parcourir l'arbre et retourner la feuille 
         * Le robot Ouest Tourne
         */
        constructionArbreBinaire = new ConstructionArbreBinaire(controleur.getModele().getPartie(), robotO);
        String feuille = constructionArbreBinaire.action();
        assertEquals("Tourner", feuille);
        /*
         Le robot Est Tourne
         */
        constructionArbreBinaire = new ConstructionArbreBinaire(controleur.getModele().getPartie(), robotE);
        feuille = constructionArbreBinaire.action();
        assertEquals("Tourner", feuille);
        /*
         Le robot Sud
         */
        constructionArbreBinaire = new ConstructionArbreBinaire(controleur.getModele().getPartie(), robotS);
        feuille = constructionArbreBinaire.action();
        assertEquals("Tourner", feuille);
        /*
         Le robot Nord ne fait rien car son énergie est à 0 ..
         il va se charger 
         */
        constructionArbreBinaire = new ConstructionArbreBinaire(controleur.getModele().getPartie(), robotN);
        feuille = constructionArbreBinaire.action();
        assertEquals("RienFaire", feuille);
    }

    /**
     * Test of verification method, of class ConstructionArbreBinaire.
     */
////    @Test
////    public void testVerification() {
////        System.out.println("verification");
////        Condition condition = null;
////        Robot robot = null;
////        Partie partie = null;
////        ConstructionArbreBinaire instance = new ConstructionArbreBinaire();
////        int expResult = 0;
////        int result = instance.verification(condition, robot, partie);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
}
