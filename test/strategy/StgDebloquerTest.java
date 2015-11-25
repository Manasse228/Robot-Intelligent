/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import modele.CaseGraphique;
import modele.Direction;
import modele.Modele;
import modele.Position;
import modele.Robot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StgDebloquerTest {

    Modele modele;
    StgDebloquer stgDebloquer;

    @Before
    public void setUp() {
        modele = new Modele();
        /*
         Grille de 5x5 est crée puis 25 robots sont placés dessus
         On teste pour voir si le robot va penser
         à se débloquer qui le pousser à tourner
         */
        modele.creationGrille(5, 5, 25);
        modele.creationCaseGraphique(5, 5);
        stgDebloquer = new StgDebloquer(modele.getPartie(),
                new Robot(18, new Position(0, 5), Direction.Ouest, true, "nom"));
    }

    @Test
    public void test() {
        Position positionOriginal = new Position(0, 5);
        ArrayList<CaseGraphique> listCaseGraphiques = modele.getPartie().getTriListCaseGraphique();
        ArrayList<StgDebloquer.PositionDirection> result
                = stgDebloquer.listPositionPossible(positionOriginal, listCaseGraphiques);
        assertTrue("True", result.isEmpty());

    }

}
