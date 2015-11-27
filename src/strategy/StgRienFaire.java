package strategy;

import controleur.Partie;
import modele.Robot;

public class StgRienFaire implements Strategy {

    Partie partie;
    Robot robot;

    public StgRienFaire(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;
    }

    /*
     Augmenter l'énergie du robot dans le cas où
     son energie venait à manquer
     */
    @Override
    public Partie renvoyerPartie() {
        if (this.robot.getEnergie() <= 0) {
            this.robot.setEnergie(19);
        } else {
            //On fait rien
        }

        return this.partie;
    }

}
