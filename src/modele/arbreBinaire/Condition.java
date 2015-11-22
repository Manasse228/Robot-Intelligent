/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.arbreBinaire;

import controleur.Partie;
import modele.Robot;

/**
 *
 * @author 21416699
 */
public interface Condition {

    public boolean check(Partie partie, Robot robot);

}
