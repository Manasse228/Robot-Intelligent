
package modele.arbreBinaire;

import controleur.Partie;
import modele.Robot;


public interface Condition {

    public boolean check(Partie partie, Robot robot);

}
