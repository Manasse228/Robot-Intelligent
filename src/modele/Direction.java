
package modele;

import java.util.Random;

public enum Direction {
    /*
     Cette classe gère les quatres directions
     */

    Nord,
    Sud,
    Est,
    Ouest;

    /*
     Cette méthode retourne une direction au hasard
     */
    public static Direction getRandomDirection() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
