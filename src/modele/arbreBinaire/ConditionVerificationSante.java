/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
