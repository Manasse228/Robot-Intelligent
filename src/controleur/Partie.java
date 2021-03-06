
package controleur;

import java.util.ArrayList;
import modele.Case;
import modele.CaseGraphique;
import modele.Missile;
import modele.Position;
import modele.Robot;

public class Partie {

    ArrayList<Case> listBloc;
    ArrayList<Robot> listRobot;
    ArrayList<CaseGraphique> listCaseGraphique;
    ArrayList<CaseGraphique> triListCaseGraphique;
    ArrayList<Missile> listMissile;

    private int notif;

    public Partie() {
        listBloc = new ArrayList<>();
        listRobot = new ArrayList<>();
        listCaseGraphique = new ArrayList<>();
        triListCaseGraphique = new ArrayList<>();
        listMissile = new ArrayList<>();
    }

    public Partie(ArrayList<Case> listBloc, ArrayList<Robot> listRObot, ArrayList<CaseGraphique> listCaseGraphique,
            ArrayList<CaseGraphique> triListCaseGraphique) {
        this.listBloc = listBloc;
        this.listRobot = listRObot;
        this.listCaseGraphique = listCaseGraphique;
        this.triListCaseGraphique = triListCaseGraphique;
    }
    /*
     Cette méthode permet de renvoyer le type de case en fonction de la 
     position 
     */

    public Case quiEstLa(Position pos, ArrayList<CaseGraphique> listCaseGraphique) {
        Case element = null;
        for (CaseGraphique c : listCaseGraphique) {
            if (Position.egalite(pos, c.getCaze().position()) == true) {
                element = c.getCaze();
            }
        }
        return element;
    }

    public Case testQuiEstLa(Position pos, ArrayList<Case> list) {
        Case element = null;
        for (Case c : list) {
            if (Position.egalite(pos, c.position()) == true) {
                element = c;
            }
        }
        return element;
    }

    public ArrayList<Case> getListBloc() {
        return listBloc;
    }

    public void setListBloc(ArrayList<Case> listBloc) {
        this.listBloc = listBloc;
    }

    public ArrayList<Robot> getListRobot() {
        return listRobot;
    }

    public void setListRobot(ArrayList<Robot> listRobot) {
        this.listRobot = listRobot;
    }

    public int getNotif() {
        return notif;
    }

    public void setNotif(int notif) {
        this.notif = notif;
    }

    public ArrayList<CaseGraphique> getListCaseGraphique() {
        return listCaseGraphique;
    }

    public void setListCaseGraphique(ArrayList<CaseGraphique> listCaseGraphique) {
        this.listCaseGraphique = listCaseGraphique;
    }

    public ArrayList<CaseGraphique> getTriListCaseGraphique() {
        return triListCaseGraphique;
    }

    public void setTriListCaseGraphique(ArrayList<CaseGraphique> triListCaseGraphique) {
        this.triListCaseGraphique = triListCaseGraphique;
    }

    public ArrayList<Missile> getListMissile() {
        return listMissile;
    }

    public void setListMissile(ArrayList<Missile> listMissile) {
        this.listMissile = listMissile;
    }

}
