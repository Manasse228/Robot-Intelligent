package strategy;

import controleur.Partie;
import modele.Position;
import modele.Robot;
import modele.CaseGraphique;

public class StgActiverBouclier implements Strategy {

    Partie partie;
    Robot robot;

    public StgActiverBouclier(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;
    }

    /*
     Activer un bouclier
     */
    @Override
    public Partie renvoyerPartie() {

        for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
            CaseGraphique caz = this.partie.getTriListCaseGraphique().get(i);
            if (caz.getCaze() instanceof Robot) {
                Robot rob = (Robot) caz.getCaze();
                // Changement de l'état du bouclier
                if (Position.egalite(this.robot.getPosition(), rob.getPosition())) {
                    this.robot.setBouclier(true);
                    this.robot.setEnergie(this.robot.getEnergie() - 1);
                    this.partie.getTriListCaseGraphique().get(i).setCaze(this.robot);
                }
            }
        }

        for (int j = 0; j < this.partie.getListRobot().size(); j++) {
            if (this.partie.getListRobot().get(j).getNom().equals(this.robot.getNom())) {
                this.partie.getListRobot().set(j, this.robot);
            }
        }

        return this.partie;
    }

}
