/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import controleur.Controleur;
import controleur.Partie;
import java.util.ArrayList;
import modele.CaseGraphique;
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
public class StgDebloquerTest {

    Modele modele;
    Controleur controleur;
    StgDebloquer stgDebloquer;

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
        stgDebloquer = new StgDebloquer(modele.getPartie(),
                new Robot(18, new Position(0, 5), Direction.Ouest, true, "nom"));
    }

    /**
     * Test of listPositionPossible method, of class StgDebloquer.
     */
    @Test
    public void testListPositionPossible() {
        Position positionOriginal = new Position(0, 5);
        ArrayList<CaseGraphique> listCaseGraphiques = modele.getPartie().getTriListCaseGraphique();
        ArrayList<StgDebloquer.PositionDirection> result
                = stgDebloquer.listPositionPossible(positionOriginal, listCaseGraphiques);
        assertTrue("True", result.isEmpty());

    }

}
