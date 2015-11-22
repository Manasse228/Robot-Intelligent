/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import controleur.Controleur;
import modele.Direction;
import modele.Modele;
import modele.Position;
import modele.Robot;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author scarmel
 */
public class StgRienFaireTest {

    Modele modele;
    Controleur controleur;
    StgRienFaire stgRienFaire;
    Robot robot;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        modele = new Modele();
        controleur = new Controleur(modele);
        controleur.demarrer(5, 5, 25);
        robot = new Robot(0, new Position(0, 5), Direction.Ouest, true, "nom");
        stgRienFaire = new StgRienFaire(modele.getPartie(), robot);

    }

    /**
     * Test of renvoyerPartie method, of class StgRienFaire.
     */
    @Test
    public void testRenvoyerPartie() {
        stgRienFaire.renvoyerPartie();
        assertEquals(19, robot.getEnergie());

    }

}
