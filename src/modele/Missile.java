/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Partie;
import controleur.Controleur;
import java.util.ArrayList;


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

    private Case detecteurElement(Position pos, ArrayList<CaseGraphique> listCaseGraphique) {
        Case element =null;
        for (CaseGraphique c : listCaseGraphique) {
            if (Position.egalite(pos, c.getCaze().position()) == true) {
                element = c.getCaze();
            }
        }
        return element;
    }

    public Missile renvoiMissile(Position pos, ArrayList<CaseGraphique> listCaseGraphique) {
        Missile missile = new Missile();
        for (CaseGraphique c : listCaseGraphique) {
            if (Position.egalite(pos, c.getCaze().position()) == true) {
                missile = (Missile) c.getCaze();
            }
        }
        return missile;
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
     Déplacement d'une missile 
     */
    public void seDeplacer(Direction direction, Position position, Partie partie, Images images,
            String nom, Controleur controleur) {
        nvlePosition = robot.nextPosition(direction, position);
        if (robot.existPosition(nvlePosition, partie.getListBloc())) {
            Case element =  detecteurElement(nvlePosition, partie.getTriListCaseGraphique());
            switch (element.toString()) {
                case "CaseVide":
                    /*
                     Dans le cas ou le missile veut accéder à une case vide
                     */
                    for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), nvlePosition) == true) {
                            Missile missile = new Missile(nvlePosition, direction, nom);
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(missile, images.renvoiImages(missile).getImage()));
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

                        // Le robot atterit sur le robot
                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), nvlePosition) == true) {
                            Robot rob = (Robot) partie.getTriListCaseGraphique().get(i).getCaze();

                            //Le robot na pas activé son bouclier
                            if (rob.isBouclier() == false) {
                                //On affiche une effet de collission entre un robot et une missile
                                partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesRobotTue().getImage()));
                                controleur.getModele().notifyObserver();
                                //On remplace l'emplacement du robot par une case vide car il a rendu l'âme
                                partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(nvlePosition),
                                        images.renvoiImages(new CaseVide(nvlePosition)).getImage()));
                            } else {
                                // Le robot a activé son bouclier rien ne se passe  
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
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                                    images.renvoiImages(new CaseVide(position)).getImage()));
                        }

                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), nvlePosition) == true) {

                            //Affichage d'une image de collision de deux missile
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(images.renvoiImagesCollisionMissile().getImage()));
                            controleur.getModele().notifyObserver();
                            /*une pause d'affichage à ce niveau et après on affiche du vide*/
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(nvlePosition),
                                    images.renvoiImages(new CaseVide(nvlePosition)).getImage()));
                        }

                    }
                    break;
                default:
                    break;
            }
        } else {//le missile sort de al grille
            System.err.println("Missile sort de la grille");
            for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), position) == true) {
                    partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(position),
                            images.renvoiImages(new CaseVide(position)).getImage()));
                }
            }
            controleur.getModele().notifyObserver();
        }
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
