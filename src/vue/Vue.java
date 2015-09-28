/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import modele.Case;
import modele.CaseVide;
import modele.Grille;
import modele.Position;
import modele.Robot;

/**
 *
 * @author sergeokov
 */
public class Vue extends JFrame implements Observer {

    Grille grille;
    ArrayList<CaseGraphique> listCaseGraphique = new ArrayList<CaseGraphique>();
    ArrayList<CaseGraphique> triListCaseGraphique = new ArrayList<CaseGraphique>();
    Images images = new Images();

    public Vue(int hauteur, int largeur, int nbreRobot) {

        this.setTitle("Robot");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6));
        this.setMinimumSize(new Dimension((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        grille = new Grille(hauteur, largeur, nbreRobot);
        menu();
        setLayout(new GridLayout(hauteur, largeur));
        afficherCase(hauteur, largeur);
        
        this.setVisible(true);
    }

    public void mouvementRobot() {
        for (Robot robot : grille.getListRobot()) {
           // System.err.println("entre "+robot.position());
            Position oldPosition = robot.getPosition();
           // robot.seDeplacer(robot.getDirection(), robot.getPosition(), grille.getListCase());
           // System.err.println("sortie "+robot.position());
            actualisationDeLaListeGrille(robot, oldPosition);
        }

    }

    public void actualisationDeLaListeGrille(Robot robot, Position oldPosition) {
        for (int i = 0; i < grille.getListCase().size(); i++) {

            if ("Robot".equals(grille.getListCase().get(i).toString())) {

                if (Position.egalite(oldPosition, grille.getListCase().get(i).position())) {
                    grille.getListCase().set(i, new CaseVide(oldPosition));
                }
                grille.getListCase().set(i, robot);
            }

        }

    }

    public void afficherCase(int hauteur, int largeur) {

        for (Case caz : grille.getListCase()) {
            listCaseGraphique.add(new CaseGraphique(caz, images.renvoiImages(caz).getImage()));
        }

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                for (CaseGraphique caseGraphique : listCaseGraphique) {

                    if (Position.egalite(caseGraphique.getCaze().position(), new Position(i, j)) == true) {
                        triListCaseGraphique.add(caseGraphique);
                    }
                }

            }
        }

        for (CaseGraphique caseGraphique : triListCaseGraphique) {
            this.getContentPane().add(caseGraphique);
        }

        for (Case caseGraphique : grille.getListCase()) {
         // System.out.println("Avant Position " + caseGraphique.position() + " et je suis " + caseGraphique.toString());
        }
        mouvementRobot();
        
        for (Case caseGraphique : grille.getListCase()) {
         // System.out.println("Après Position " + caseGraphique.position() + " et je suis " + caseGraphique.toString());
        }
    }

    public void menu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu jMenu = new JMenu("Menu");
        JMenu jMenu1 = new JMenu("Menu1");
        JMenu jMenu2 = new JMenu("Menu2");

        JMenuItem jMenuItem = new JMenuItem("cool");
        JMenuItem jMenuItem1 = new JMenuItem("cool");
        JMenuItem jMenuItem2 = new JMenuItem("cool");

        jMenu.add(jMenuItem);
        jMenu.add(jMenuItem1);

        menuBar.add(jMenu);
        menuBar.add(jMenu1);
        menuBar.add(jMenu2);
        this.setJMenuBar(menuBar);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
