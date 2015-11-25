/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.Case;
import modele.CaseVide;
import modele.Modele;
import controleur.Partie;
import modele.Position;
import modele.Robot;
import modele.CaseGraphique;
import modele.Images;

public class StgReculer implements Strategy {

    Partie partie;
    Robot robot;
    Images images;
    Position pos,oldPosition;
    Modele modele;
    

    public StgReculer(Partie partie, Robot robot, Modele modele) {
        images = new Images();
        this.partie = partie;
        this.robot = robot;
        oldPosition = robot.getPosition();
        this.modele = modele;
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

            Case element = this.partie.quiEstLa(pos, this.partie.getTriListCaseGraphique());
            switch (element.toString()) {
                case "CaseVide":
                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {

                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), this.robot.getPosition()) == true) {
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(this.robot.getPosition()),
                                    images.renvoiImages(new CaseVide(this.robot.getPosition())).getImage()));
                        }

                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), pos) == true) {
                            Robot nvoRobo = new Robot(this.robot.getEnergie() - 1, pos, this.robot.getDirection(), this.robot.isBouclier(),
                                    this.robot.getNom());
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(nvoRobo, images.renvoiImages(nvoRobo).getImage()));
                        }

                    }
                    break;
                case "Robot": // Il va rien faire 
                    Strategy stg = new StgRienFaire(this.partie, this.robot);
                    stg.renvoyerPartie();
                    break;
                case "Missile": // Il meurt si son bouclier n'est pas activé
                    
                     for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), oldPosition) == true) {
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(oldPosition),
                                    images.renvoiImages(new CaseVide(oldPosition)).getImage()));
                        }
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), pos) == true) {

                            if (this.robot.isBouclier() == false) {
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTue().getImage()));
                                this.modele.notifyObserver();
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(pos),
                                        images.renvoiImages(new CaseVide(pos)).getImage()));
                            }else{
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(this.robot,
                                        images.renvoiImages(this.robot).getImage()));
                            }

                        }
                    }
                    break;
                    
                default:
                    break;
            }

        } else {
            System.err.println("Ce robot peut pas reculer");
            //Ce robot peut pas reculer
            return this.partie;
        }
        return this.partie;
    }

}
