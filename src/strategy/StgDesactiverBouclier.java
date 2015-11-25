
package strategy;

import controleur.Partie;
import modele.Position;
import modele.Robot;
import modele.CaseGraphique;

/**
 *
 * @author sergeokov
 */
public class StgDesactiverBouclier implements Strategy{
    
    Partie partie;
    Robot robot;

    public StgDesactiverBouclier(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;
    }
    
    

    @Override
    public Partie renvoyerPartie() {
        
        for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
            CaseGraphique caz = this.partie.getTriListCaseGraphique().get(i);
            if (caz.getCaze() instanceof Robot) {
                Robot rob = (Robot) caz.getCaze();
                // Changement de l'état du bouclier
                if (Position.egalite(this.robot.getPosition(), rob.getPosition())) {
                    this.robot.setBouclier(false);
                    this.robot.setEnergie(this.robot.getEnergie() - 1);
//                    this.partie.getTriListCaseGraphique().get(i).setCaze(this.robot);
                }
            }
        }

        return this.partie;
    }
    
}
