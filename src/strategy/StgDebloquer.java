package strategy;

import java.util.ArrayList;
import java.util.Random;
import modele.CaseVide;
import modele.Direction;
import modele.Missile;
import controleur.Partie;
import modele.Position;
import modele.Robot;
import modele.CaseGraphique;
import modele.Images;

public class StgDebloquer implements Strategy {

    Partie partie;
    Robot robot;
    Position position1;
    Robot robot1;
    Missile missile1;
    Images images;

    public StgDebloquer(Partie partie, Robot robot) {
        robot1 = new Robot();
        missile1 = new Missile();
        images = new Images();

        this.partie = partie;
        this.robot = robot;
    }

    protected class PositionDirection {

        Direction direction;
        Position position;

        public PositionDirection(Direction direction, Position position) {
            this.direction = direction;
            this.position = position;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "Direction " + direction + "Position " + position;
        }

    }

    public ArrayList<PositionDirection> listPositionPossible(Position positionOriginal, ArrayList<CaseGraphique> listCaseGraphiques) {
        Position position;
        ArrayList<PositionDirection> list = new ArrayList<>();
        //On parcours toutes les directions NSEO
        for (Direction dir : Direction.values()) {
            //Calcul de la position voisine
            position = robot1.nextPosition(dir, positionOriginal);
            if (robot1.existPosition(position, this.partie.getListBloc()) == true) {
                //si cette position existe on va vérifier qu'elle contient une case vide
                for (CaseGraphique c : this.partie.getTriListCaseGraphique()) {
                    if ((Position.egalite(c.getCaze().position(), position) == true) && (c.getCaze() instanceof CaseVide)) {
                        list.add(new PositionDirection(dir, position));
                    }
                }

            }
        }
        return list;
    }

    /*
     Si un robot est coincé 
     il se débloquera tout seul
     */
    @Override
    public Partie renvoyerPartie() {

        ArrayList<PositionDirection> list = listPositionPossible(this.robot.getPosition(), this.partie.getTriListCaseGraphique());
        if (list.isEmpty()) {//Dans le cas ou il n'y a aucune position possible à accéder aux alentours
            //Le robot tourne car il n'ya aucune solution pour lui pour le moment
            this.partie = new StgTourner(this.partie, this.robot).renvoyerPartie();
        } else {
            Random rand = new Random();
            int i = rand.nextInt(((list.size() - 1) + 1) - 0);
            PositionDirection positionDirection = list.get(i);

            for (int j = 0; j < this.partie.getTriListCaseGraphique().size(); j++) {
                if (Position.egalite(this.partie.getTriListCaseGraphique().get(j).getCaze().position(), positionDirection.getPosition())) {
                    Robot r = new Robot(this.robot.getEnergie() - 1, positionDirection.getPosition(),
                            positionDirection.getDirection(), this.robot.isBouclier(), this.robot.getNom());
                    this.partie.getTriListCaseGraphique().set(j, new CaseGraphique(r, images.renvoiImages(r).getImage()));

                    for (int k = 0; k < this.partie.getListRobot().size(); k++) {
                        if (this.partie.getListRobot().get(k).getNom().equals(r.getNom())) {
                            this.partie.getListRobot().set(k, r);
                        }
                    }
                }

                if (Position.egalite(this.partie.getTriListCaseGraphique().get(j).getCaze().position(), this.robot.getPosition())) {
                    this.partie.getTriListCaseGraphique().set(j, new CaseGraphique(new CaseVide(this.robot.getPosition()),
                            images.renvoiImages(new CaseVide(this.robot.getPosition())).getImage()));
                }
            }

        }

        return this.partie;
    }

}
