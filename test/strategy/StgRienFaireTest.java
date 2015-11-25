/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.Direction;
import modele.Modele;
import modele.Position;
import modele.Robot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StgRienFaireTest {

    Modele modele;
    StgRienFaire stgRienFaire;
    Robot robot;

    @Before
    public void setUp() {
        modele = new Modele();
        modele.creationGrille(5, 4, 20);
        /*
         On crée un robot et on lui dit de se charger en energie
         */
        robot = new Robot(0, new Position(0, 5), Direction.Ouest, true, "nom");
        stgRienFaire = new StgRienFaire(null, robot);

    }

    @Test
    public void test() {
        stgRienFaire.renvoyerPartie();
        assertEquals(19, robot.getEnergie());

    }

}
