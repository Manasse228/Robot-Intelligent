/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.CaseVide;
import modele.Partie;
import modele.Position;
import modele.Robot;
import vue.CaseGraphique;
import vue.Images;

/**
 *
 * @author 21416699
 */
public class StgReculer implements Strategy {

    Partie partie;
    Robot robot;
    Images images;
    Position pos;

    public StgReculer(Partie partie, Robot robot) {
        images = new Images();
        this.partie = partie;
        this.robot = robot;
    }

    @Override
    public Partie renvoyerPartie() {
        switch (this.robot.getDirection()) {
            case Nord:
                pos = new Position(this.robot.getPosition().getY() + 1, this.robot.getPosition().getX());
                break;
            case Sud:
                pos = new Position(this.robot.getPosition().getY() - 1, this.robot.getPosition().getX());
                break;
            case Est:
                pos = new Position(this.robot.getPosition().getY(), this.robot.getPosition().getX() - 1);
                break;
            case Ouest:
                pos = new Position(this.robot.getPosition().getY(), this.robot.getPosition().getX() + 1);
                break;
            default:
                break;
        }
        if (robot.existPosition(pos, this.partie.getListBloc())) {
            for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {

                if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), this.robot.getPosition()) == true) {
                    this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(this.robot.getPosition()),
                            images.renvoiImages(new CaseVide(this.robot.getPosition())).getImage()));
                }

                if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), pos) == true) {
                    Robot nvoRobo = new Robot(this.robot.getEnergie() - 1, pos, this.robot.getDirection(), this.robot.getNom());
                    this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(nvoRobo, images.renvoiImages(nvoRobo).getImage()));
                }

            }
            for (int j = 0; j < this.partie.getListRobot().size(); j++) {
                if (this.partie.getListRobot().get(j).getNom().equals(this.robot.getNom())) {
                    this.partie.getListRobot().set(j, this.robot);
                }
            }
        } else {
            System.err.println("Ce robot peut pas reculer");
            //Ce robot peut pas reculer
            return this.partie;
        }
        return this.partie;
    }

}
