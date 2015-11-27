package strategy;

import modele.Case;
import modele.CaseVide;
import modele.Missile;
import modele.Modele;
import controleur.Partie;
import modele.Position;
import modele.Robot;
import modele.CaseGraphique;
import modele.Images;

public class StgLancerMissile implements Strategy {

    Partie partie;
    Position position;
    Robot roboti, robot;
    Missile missile;
    Images images;
    Modele modele;

    public StgLancerMissile(Partie partie, Robot robot, Modele modele) {
        missile = new Missile();
        roboti = new Robot();
        images = new Images();

        this.modele = modele;
        this.robot = robot;
        this.partie = partie;
    }

    private int compteurRobot() {
        int compteur = 0;
        for (CaseGraphique c : this.partie.getTriListCaseGraphique()) {
            if (c.getCaze() instanceof Robot) {
                compteur++;
            }
        }
        return compteur;
    }
    /*
     Lancer une missile
     */

    @Override
    public Partie renvoyerPartie() {

        if (compteurRobot() > 2) {

            position = roboti.nextPosition(this.robot.getDirection(), this.robot.getPosition());

            if (roboti.existPosition(position, this.partie.getListBloc())) {
                Case element = this.partie.quiEstLa(position, this.partie.getTriListCaseGraphique());
                this.robot.setEnergie(this.robot.getEnergie() - 1);
                switch (element.toString()) {
                    case "CaseVide":
                        /*
                         Dans le cas ou le missile veut accéder à une case vide
                         */
                        for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                            if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                                missile = new Missile(position, this.robot.getDirection(), this.robot.getNom());
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(missile, images.renvoiImages(missile).getImage()));
                            }
                        }
                        break;
                    case "Robot":
                        /*
                         Dans le cas ou le missile a un robot devant lui
                         */
                        for (int i = 0; i < this.partie.getTriListCaseGraphique().size(); i++) {
                            if (Position.egalite(this.partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                                Robot rob = (Robot) this.partie.getTriListCaseGraphique().get(i).getCaze();

                                //Le robot na pas son bouclier
                                if (rob.isBouclier() == false) {
                                    //On affiche une effet de collission entre un robot et une missile
                                    this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTue().getImage()));
                                    this.modele.notifyObserver();
                                    //On remplace l'emplacement du robot par une case vide car il a rendu l'âme
                                    this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(this.position),
                                            images.renvoiImages(new CaseVide(this.position)).getImage()));
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
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesCollisionMissile().getImage()));
                                this.modele.notifyObserver();
                                /*une pause d'affichage à ce niveau et après on affiche du vide*/
                                this.partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                        images.renvoiImages(new CaseVide(position)).getImage()));
                            }

                        }

                        break;
                    default:
                        break;
                }
            } else {//le missile sort de la grille
                this.roboti.setEnergie(this.roboti.getEnergie() - 1);
                System.err.println("Missile sort de la grille ..");

            }

        } else {// Le robot ne lance pas de missile il avance
            this.robot.setEnergie(this.robot.getEnergie() - 1);
            Strategy s = new StgAvancer(this.partie, this.robot, this.modele);
            s.renvoyerPartie();
        }
        return this.partie;
    }

}
