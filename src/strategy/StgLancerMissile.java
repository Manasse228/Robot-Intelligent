/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.Iterator;
import modele.CaseVide;
import modele.Direction;
import modele.Missile;
import modele.Partie;
import modele.Position;
import modele.Robot;
import vue.CaseGraphique;
import vue.Images;

/**
 *
 * @author 21416699
 */
public class StgLancerMissile implements Strategy {

    Partie partie;
    Direction direction;
    Position position, positionInitiale;
    String nom;
    Robot roboti;
    Missile missile;
    Images images = new Images();

    public StgLancerMissile(Partie partie, Robot robot) {
        missile = new Missile();

        this.partie = partie;
        this.roboti = robot;
        this.nom = this.roboti.getNom();
        this.direction = this.roboti.getDirection();
        positionInitiale = robot.getPosition();
    }

    @Override
    public Partie renvoyerPartie() {

        position = roboti.nextPosition(this.direction, positionInitiale);

        if (roboti.existPosition(position, this.partie.getListBloc())) {
            String element = missile.detecteurElement(position, this.partie.getTriListCaseGraphique());
            this.roboti.setEnergie(this.roboti.getEnergie() - 1);
            switch (element) {
                case "CaseVide":
                    /*
                     Dans le cas ou le missile veut accéder à une case vide
                     */
                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                            missile = new Missile(position, this.direction, this.nom);
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(missile, images.renvoiImages(missile).getImage()));
                            this.partie.getListMissile().add(missile);
                        }
                    }
                    break;
                case "Robot":
                    /*
                     Dans le cas ou le missile a un robot devant lui
                     */
                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), this.position) == true) {
                            Robot rob = (Robot) this.partie.getTriListCaseGraphique().get(i).getCaze();

                            //Le robot na pas activé son bouclier
                            if (rob.isBouclier() == false) {
                                //On affiche une effet de collission entre un robot et une missile
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTue().getImage()));
                                //On remplace l'emplacement du robot par une case vide car il a rendu l'âme
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(this.position),
                                        images.renvoiImages(new CaseVide(this.position)).getImage()));

                                Iterator<Robot> iteRobot = this.partie.getListRobot().iterator();
                                while (iteRobot.hasNext()) {
                                    Robot robo = iteRobot.next();
                                    if (Position.egalite(this.position, robo.getPosition())) {
                                        iteRobot.remove();
                                    }
                                }

                            } else {
                                // Le robot a activé son bouclier   
                            }
                        }
                    }
                    break;
                case "Missile":
                    /*
                     Dans le cas ou deux missiles se rencontrent
                     */
                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                            //Affichage d'une image de collision de deux missile
//                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesCollisionMissile().getImage()));
                            /*une pause d'affichage à ce niveau et après on affiche du vide*/
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                    images.renvoiImages(new CaseVide(position)).getImage()));

                            Iterator<Missile> iteMisssile = partie.getListMissile().iterator();
                            while (iteMisssile.hasNext()) {
                                Missile mis = iteMisssile.next();
                                if (Position.egalite(position, mis.getPosition()) == true) {
                                    iteMisssile.remove();
                                }
                            }
                        }

                    }

                    break;
                default:
                    break;
            }
        } else {//le missile sort de la grille
            System.err.println("Missile sort de la grille ..");

        }
        return this.partie;
    }

}
