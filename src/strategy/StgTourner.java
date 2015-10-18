/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.Direction;
import modele.Partie;
import modele.Position;
import modele.Robot;
import vue.CaseGraphique;
import vue.Images;

/**
 *
 * @author 21416699
 */
public class StgTourner implements Strategy {
    
    Partie partie;
    Robot robot;
    Images images = new Images();
    
    public StgTourner(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;
    }
    
    @Override
    public Partie renvoyerPartie() {
        
        switch (this.robot.getDirection()) {
            case Nord:
                this.robot.setDirection(Direction.Sud);
                break;
            case Sud:
                this.robot.setDirection(Direction.Nord);
                break;
            case Est:
                this.robot.setDirection(Direction.Ouest);
                break;
            case Ouest:
                this.robot.setDirection(Direction.Est);
                break;
            default:
                break;
        }
        this.robot.setEnergie(this.robot.getEnergie() - 1);
        for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
            CaseGraphique caz = this.partie.getTriListCaseGraphique().get(i);
            if ("Robot".equals(caz.getCaze().toString())) {
                Robot rob = (Robot) caz.getCaze();
                if (Position.egalite(this.robot.getPosition(), rob.getPosition()) == true) {
                    this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(rob, images.renvoiImages(rob).getImage()));
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
