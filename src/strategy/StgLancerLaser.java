/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.Iterator;
import modele.CaseVide;
import modele.Modele;
import modele.Partie;
import modele.Position;
import modele.Robot;
import vue.CaseGraphique;
import vue.Images;

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

    public StgLancerLaser(Partie partie, Robot robot) {
        roboti = new Robot();
        modele = new Modele();

        images = new Images();
        this.partie = partie;
        this.robot = robot;
    }

    @Override
    public Partie renvoyerPartie() {

        position = roboti.nextPosition(this.robot.getDirection(), this.robot.getPosition());

        if (roboti.existPosition(position, this.partie.getListBloc())) {
            for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                if ((Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true)
                        && ("Robot".equals(this.partie.getTriListCaseGraphique().get(i).getCaze().toString()))) {

                    Robot rob = (Robot) this.partie.getTriListCaseGraphique().get(i).getCaze();

                    Iterator iteRobot = this.partie.getListRobot().iterator();
                    while (iteRobot.hasNext()) {
                        if(iteRobot.next() == null){
                            System.err.println("videeeeeeeeeeeeeeeeee");
                        }
                        Robot robo = (Robot) iteRobot.next();
                        if (Position.egalite(position, robo.getPosition())) {
                            iteRobot.remove();
                        }
                    }

                    this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTueLaser().getImage()));
                    modele.notifyObserver();
                    this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(this.position),
                            images.renvoiImages(new CaseVide(this.position)).getImage()));

                }

            }
        }

        return this.partie;
    }

}
