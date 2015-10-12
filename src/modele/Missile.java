/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import vue.CaseGraphique;
import vue.Images;

/**
 *
 * @author 21416699
 */
public class Missile implements Case {

    Position position;
    Grille grille;
    Robot robot = new Robot();
    Direction direction;

    @Override
    public Position position() {
        return position;
    }

    public Missile(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
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
    public void effacerCesTracesArriere(Direction direction, Position position, Partie partie, Images images) {
        Position pos = new Position(0, 0);
        switch (direction) {
            case Nord:
                pos = robot.nextPosition(direction.Sud, position);
                break;
            case Sud:
                pos = robot.nextPosition(direction.Nord, position);
                break;
            case Est:
                pos = robot.nextPosition(direction.Ouest, position);
                break;
            case Ouest:
                pos = robot.nextPosition(direction.Est, position);
                break;
        }

        if ("Missile".equals(detecteurElement(pos, partie.getTriListCaseGraphique()))) {
            for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), pos) == true) {
                    Missile missile = (Missile) partie.getTriListCaseGraphique().get(i).getCaze();
                    if (missile.direction == direction) {
                        partie.getTriListCaseGraphique().set(i, new CaseGraphique(new CaseVide(pos), images.renvoiImages(new CaseVide(pos)).getImage()));
                    }
                }
            }
        }
    }

    /*
     Déplacement d'une missile Fonction déja utilisée au niveau de la vue donc ne pas toucher Merci
     */
    public void seDeplacer(Direction direction, Position position, Partie partie, Images images) {
        this.position = robot.nextPosition(direction, position);
        
        if (robot.existPosition(this.position, partie.getListBloc())) {
            String element = detecteurElement(this.position, partie.getTriListCaseGraphique());
            switch (element) {
                case "CaseVide":
                    /*
                     Dans le cas ou le missile veut accéder à une case vide
                     */
                    for (int i = 0; i < partie.getTriListCaseGraphique().size(); i++) {
                        if (Position.egalite(partie.getTriListCaseGraphique().get(i).getCaze().position(), this.position) == true) {
                            Missile missile = new Missile(this.position, direction);
                            partie.getTriListCaseGraphique().set(i, new CaseGraphique(missile, images.renvoiImages(missile).getImage()));
                            
                            effacerCesTracesArriere(direction, this.position, partie, images);
                            seDeplacer(direction, this.position, partie, images);
                        }
                    }
                    break;
                case "Robot":
                    /*
                     Dans le cas ou le missile a un robot devant lui
                     */
                    for (int i = 0; i < partie.getListBloc().size(); i++) {
                        if (Position.egalite(partie.getListBloc().get(i).position(), this.position) == true) {
                            //le robot en question peut mourir et on change sa position par une cae vide
                            partie.getListBloc().set(i, new CaseVide(this.position));
                        }
                    }
                    break;
                case "Missile":
                    /*
                     Dans le cas ou deux missiles se rencontrent
                     */
                    for (int i = 0; i < partie.getListBloc().size(); i++) {
                        if (Position.egalite(partie.getListBloc().get(i).position(), this.position) == true) {
                            //deux missiles se rencontre 
                            partie.getListBloc().set(i, new CaseVide(this.position));
                            partie.getListBloc().set(i, new CaseVide(position));
                        }
                    }
                    break;
                default:
                    break;
            }
        } else {//le missile sort de al grille
            System.err.println("Missile sort");
        }
    }

    public void feu() {

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

}
