
package modele.arbreBinaire;

import controleur.Partie;
import modele.Robot;


public class ConditionVerificationSante implements Condition {

    @Override
    public boolean check(Partie partie, Robot robot) {
        /*
         Retourne true si l'energie du robot est inférieur ou égale à zéro
         */
        return (robot.getEnergie() <= 0);
    }

}
