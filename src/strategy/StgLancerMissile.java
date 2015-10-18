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
    Robot robot = new Robot();
    Robot roboti;
    Missile missile = new Missile();
    Images images = new Images();

    public StgLancerMissile(Partie partie, Robot robot) {
        this.partie = partie;
        this.roboti = robot;

        this.direction = this.roboti.getDirection();
        this.position = this.roboti.getPosition();
        positionInitiale = this.position;
        this.nom = this.roboti.getNom();
    }

    @Override
    public Partie renvoyerPartie() {

        this.position = robot.nextPosition(this.direction, this.position);

        if (robot.existPosition(this.position, this.partie.getListBloc())) {
            String element = missile.detecteurElement(this.position, this.partie.getTriListCaseGraphique());
            this.roboti.setEnergie(this.roboti.getEnergie() - 1);
            switch (element) {
                case "CaseVide":
                    /*
                     Dans le cas ou le missile veut accéder à une case vide
                     */
                    for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), this.position) == true) {
                            missile = new Missile(this.position, this.direction, this.nom);
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

                        if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), this.position) == true) {

                            //Affichage d'une image de collision de deux missile
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesCollisionMissile().getImage()));
                            /*une pause d'affichage à ce niveau et après on affiche du vide*/
                            this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(this.position),
                                    images.renvoiImages(new CaseVide(this.position)).getImage()));

                            Iterator<Missile> iteMisssile = partie.getListMissile().iterator();
                            while (iteMisssile.hasNext()) {
                                Missile mis = iteMisssile.next();
                                if (mis.getNom().equals(nom)) {
                                    iteMisssile.remove();
                                } else {
                                    if (Position.egalite(positionInitiale, mis.getPosition())) {
                                        iteMisssile.remove();
                                        this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(positionInitiale),
                                                images.renvoiImages(new CaseVide(positionInitiale)).getImage()));
                                    }
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
