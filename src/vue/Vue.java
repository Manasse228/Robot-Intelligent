/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.Controleur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import modele.Case;
import modele.CaseVide;
import modele.Direction;
import modele.Grille;
import modele.Missile;
import modele.Partie;
import modele.Position;
import modele.Robot;

/**
 *
 * @author sergeokov
 */
public class Vue extends JFrame implements Observer {

    Grille grille = new Grille();
    Position position;
    Robot robot = new Robot();
    Images images = new Images();
    Controleur controleur;
    JPanel jpanelGauche, jpanelDroite;
    JPanel contentPanel = new JPanel();

    public Vue(Controleur controleur) throws InterruptedException {
        this.controleur = controleur;

        this.controleur.demarrer(2, 2, 2);

        this.setTitle("Robot");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6));
        this.setMinimumSize(new Dimension((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        menu();

        jpanelDroite = new JPanel();
        jpanelDroite.setLayout(new GridLayout(2, 2, 2, 2));
        afficherCase(controleur);

        jpanelGauche = new JPanel();
        JButton bb = new JButton("missile");
        jpanelGauche.add(bb);
        jpanelGauche.setBackground(Color.red);

        contentPanel.add(jpanelGauche);
        contentPanel.add(jpanelDroite);
        contentPanel.setLayout(new GridLayout(1, 2));

        this.setContentPane(contentPanel);

        this.setVisible(true);

//        for (Robot roboti : this.controleur.getModele().getPartie().getListRobot()) {
//            Missile missile = new Missile(roboti.getPosition(), roboti.getDirection());
//            missile.seDeplacer(roboti.getDirection(), roboti.getPosition(), this.controleur.getModele().getPartie(), images);
//
//        }
//        for (int i = 0; i < 6; i++) {
//
//            mouvementRobot(this.controleur.modele.getPartie());
//            this.controleur.getModele().notifyObserver(2);
//            this.revalidate();
//            this.repaint();
//            Thread.sleep(1000);
//            this.jpanelDroite.removeAll();
//            for (CaseGraphique caseGraphique : this.controleur.modele.getPartie().getTriListCaseGraphique()) {
//                this.jpanelDroite.add(caseGraphique);
//            }
//
//        }
    }

    public void voirlo() throws InterruptedException {

        /*
         Je parcours la liste des robots avec un itérateur parce que la liste 
         peut se modifier à tout moment c'est à dire si un robot tue un robot faut 
         que le robot mort sort de la liste
         */
        Iterator it = this.controleur.getModele().getPartie().getListRobot().iterator();
        while (it.hasNext()) {
            Robot roboti = (Robot) it.next();
//        }
//        for (Robot roboti : this.controleur.getModele().getPartie().getListRobot()) {
            if (robot.etatRobot(this.controleur.getModele().getPartie().getListRobot(), roboti) == true) {
                it.remove();
            } else {
                Missile missile = new Missile(roboti.getPosition(), roboti.getDirection());
                this.controleur.getModele().lancerMissile(roboti.getDirection(), roboti.getPosition(), this.controleur.getModele().getPartie(), images, missile);
                this.controleur.getModele().notifyObserver();
                
            }

        }
        it = this.controleur.getModele().getPartie().getListRobot().iterator();
        while (it.hasNext()) {
            Robot roboti = (Robot) it.next();
            System.err.println("Robot position "+roboti.getPosition()+" et il est "+roboti.isMort());
        }
        

//        for (int i = 0; i < 6; i++) {
//            mouvementRobot(this.controleur.modele.getPartie());
//            this.controleur.getModele().notifyObserver();
//        }
    }

    public void mouvementRobot(Partie partie) throws InterruptedException {

//        try {
        //  while (listRobot.size() >= 1) {
        for (Robot roboti : partie.getListRobot()) {

//                if ((roboti.getEnergie() > 5) && (roboti.isRepos() == false)) {
            Position oldPosition = roboti.getPosition();
            roboti = roboti.seDeplacer(roboti.getDirection(), roboti.getPosition(), partie.getListBloc(), roboti);

            if (roboti.getPosition() != oldPosition) {
                grille.actualisationDeLaListeGrille(roboti, oldPosition,
                        controleur.modele.getPartie().getTriListCaseGraphique(), partie.getListRobot(), images);

            }
//                } else {
//                    roboti.setRepos(true);
//                    roboti.setBouclier(false);
//                    if (roboti.recuperationEnergie(roboti) == 1) {
//                        roboti.setEnergie(roboti.getEnergie() + 1);
//                    } else {
//                        roboti.setRepos(false);
//                        roboti.setBouclier(true);
//                    }
//
//                }

        }
        // }

    }

    public void afficherCase(Controleur controleur) {

        for (CaseGraphique caseGraphique : controleur.modele.getPartie().getTriListCaseGraphique()) {
            this.jpanelDroite.add(caseGraphique);
        }

    }

    public void menu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu jMenu = new JMenu("Menu");
        JMenu jMenu1 = new JMenu("Menu1");
        JMenu jMenu2 = new JMenu("Menu2");

        JMenuItem jMenuItem = new JMenuItem();
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
    public void update(Observable o, Object obj) {
        Partie partie = ((Partie) obj);	// Recuperation de la partie envoyee par le modele

        this.revalidate();
        this.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.jpanelDroite.removeAll();
        //this.controleur.modele.getPartie().getTriListCaseGraphique()
        for (CaseGraphique caseGraphique : partie.getTriListCaseGraphique()) {
            this.jpanelDroite.add(caseGraphique);
        }

    }

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

}
