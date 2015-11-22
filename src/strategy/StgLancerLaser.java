/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.CaseVide;
import modele.Modele;
import controleur.Partie;
import modele.Position;
import modele.Robot;
import modele.CaseGraphique;
import modele.Images;

/**
 *
 * @author 21416699
 */
public class StgLancerLaser implements Strategy {
    
    Partie partie;
    Robot robot;
    Robot roboti;
    Position position;
    Images images;
    Modele modele;
    
    public StgLancerLaser(Partie partie, Robot robot, Modele modele) {
        roboti = new Robot();
        this.modele = modele;
        
        images = new Images();
        this.partie = partie;
        this.robot = robot;
    }
    
    @Override
    public Partie renvoyerPartie() {
        
        position = roboti.nextPosition(this.robot.getDirection(), this.robot.getPosition());
        this.robot.setEnergie(this.robot.getEnergie() - 1);
        if (roboti.existPosition(position, this.partie.getListBloc()) == true) {
            for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                if ((Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true)
                        && (this.partie.getTriListCaseGraphique().get(i).getCaze()  instanceof Robot)) {
                    
                    Robot rob = (Robot) this.partie.getTriListCaseGraphique().get(i).getCaze();
                    if (rob.isBouclier() == false) {
                        this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTueLaser().getImage()));
                        this.modele.notifyObserver();
                        this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                images.renvoiImages(new CaseVide(position)).getImage()));
                    }
                    
                }
                
            }
        }
        
        return this.partie;
    }
    
}
