/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Controleur;
import java.util.ArrayList;
import java.util.Iterator;
import vue.CaseGraphique;
import vue.Images;

/**
 *
 * @author 21416699
 */
public class Missile implements Case {

    Position position, nvlePosition;
    Grille grille;
    Robot robot = new Robot();
    Direction direction;
    String nom;
    ArrayList<Missile> listMissileForDelete = new ArrayList<>();

    @Override
    public Position position() {
        return position;
    }

    public Missile(Position position, Direction direction, String nom) {
        this.position = position;
        this.direction = direction;
        this.nom = nom;
    }

    public Missile() {
    }

    public String detecteurElement(Position pos, ArrayList<CaseGraphique> listCaseGraphique) {
        String element = "";
        for (CaseGraphique c : listCaseGraphique) {
            if (Position.egalite(pos, c.getCaze().position()) == true) {
                element = c.getCaze().toString();
            }
        }
        return element;
    }

    /*
     Si un missile se déplace faut effacer ces traces c'est à dire ces traces lol
     */
    public void effacerCesTracesArriere(Direction direction, Position position, Partie partie, Images images, String nom) {
        Position pos = new Position(0, 0);
        switch (direction) {
            case Nord:
                pos = robot.nextPosition(Direction.Sud, position);
                break;
            case Sud:
                pos = robot.nextPosition(Direction.Nord, position);
                break;
            case Est:
                pos = robot.nextPosition(Direction.Ouest, position);
                break;
            case Ouest:
                pos = robot.nextPosition(Direction.Est, position);
                break;
        }
        if ("Missile".equals(detecteurElement(pos, partie.getTriListCaseGraphique()))) {
            for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), pos) == true) {
                    Missile missile = (Missile) partie.getTriListCaseGraphique().get(i).getCaze();
                    if ((missile.direction == direction) && (missile.getNom().equals(nom))) {
                        partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(pos), images.renvoiImages(new CaseVide(pos)).getImage()));
                    }
                }
            }
        }

    }

    /*
     Déplacement d'une missile Fonction déja utilisée au niveau de la vue donc ne pas toucher Merci
     */
    public ArrayList<Missile> seDeplacer(Direction direction, Position position, Partie partie, Images images, String nom, Controleur controleur) {
        listMissileForDelete.clear();
        nvlePosition = robot.nextPosition(direction, position);
        if (robot.existPosition(nvlePosition, partie.getListBloc())) {
            String element = detecteurElement(nvlePosition, partie.getTriListCaseGraphique());
            switch (element) {
                case "CaseVide":
                    /*
                     Dans le cas ou le missile veut accéder à une case vide
                     */
                    for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), nvlePosition) == true) {
                            Missile missile = new Missile(nvlePosition, direction, nom);
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(missile, images.renvoiImages(missile).getImage()));
                            //Actualisation de la liste des missiles
                            for (int j = 0; j < partie.getListMissile().size(); j++) {
                                if (partie.getListMissile().get(j).getNom().equals(missile.getNom())) {
                                    partie.getListMissile().set(j, missile);
                                }
                            }
                        }

                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                    images.renvoiImages(new CaseVide(position)).getImage()));
                        }
                    }
                    controleur.getModele().notifyObserver();
                    break;
                case "Robot":
                    /*
                     Dans le cas ou le missile atterit sur robot
                     */
                    for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {

                        //Le missile disparait
                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                    images.renvoiImages(new CaseVide(position)).getImage()));
                        }
                        controleur.getModele().notifyObserver();

                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), nvlePosition) == true) {
                            Robot rob = (Robot) partie.getTriListCaseGraphique().get(i).getCaze();

                            //Le robot na pas activé son bouclier
                            if (rob.isBouclier() == true) {
                                //On affiche une effet de collission entre un robot et une missile
                                partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTue().getImage()));
                                controleur.getModele().notifyObserver();
                                //On remplace l'emplacement du robot par une case vide car il a rendu l'âme
                                partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(nvlePosition),
                                        images.renvoiImages(new CaseVide(nvlePosition)).getImage()));
                                controleur.getModele().notifyObserver();

                                Iterator<Robot> iteRobot = partie.getListRobot().iterator();

                                for (Missile mis : partie.getListMissile()) {
                                    if (mis.getNom().equals(nom)) {
                                        listMissileForDelete.add(mis);
                                    }
                                }

                                while (iteRobot.hasNext()) {
                                    Robot robo = iteRobot.next();
                                    if (Position.egalite(nvlePosition, robo.getPosition())) {
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
                    for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {

                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                            Missile mis = (Missile) partie.getTriListCaseGraphique().get(i).getCaze();
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                    images.renvoiImages(new CaseVide(position)).getImage()));
                            //Destruction de la seconde missile
                            for (Missile m : partie.getListMissile()) {
                                if (m.getNom().equals(mis.getNom())) {
                                    listMissileForDelete.add(m);
                                }
                            }
                        }
                        controleur.getModele().notifyObserver();

                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), nvlePosition) == true) {
                            Missile mis = (Missile) partie.getTriListCaseGraphique().get(i).getCaze();
                            //Affichage d'une image de collision de deux missile
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesCollisionMissile().getImage()));
                            controleur.getModele().notifyObserver();
                            /*une pause d'affichage à ce niveau et après on affiche du vide*/
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(nvlePosition),
                                    images.renvoiImages(new CaseVide(nvlePosition)).getImage()));
                            controleur.getModele().notifyObserver();

                            for (Missile m : partie.getListMissile()) {
                                if (m.getNom().equals(mis.getNom())) {
                                    listMissileForDelete.add(m);
                                }
                            }
                        }
                        controleur.getModele().notifyObserver();

                    }
                    break;
                default:
                    break;
            }
        } else {//le missile sort de al grille
            System.err.println("Missile sort avant operation");
            for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                    partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                            images.renvoiImages(new CaseVide(position)).getImage()));
                }
            }
            controleur.getModele().notifyObserver();
            /*
             On retire de la liste des missiles le missile qui vient de quitter la grille
             */
            for (Missile mis : partie.getListMissile()) {
                if (mis.getNom().equals(nom)) {
                    listMissileForDelete.add(mis);
                }
            }

            System.err.println("Missile sort après operation");
        }
        return listMissileForDelete;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Missile";
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
