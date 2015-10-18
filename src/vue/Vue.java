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
import modele.Grille;
import modele.Missile;
import modele.Partie;
import modele.Position;
import modele.Robot;
import strategy.StgLancerMissile;
import strategy.Strategy;

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
    ArrayList<Missile> listMissileForDelete, listMissileForDelete2 = new ArrayList<>();

    public Vue(Controleur controleur) throws InterruptedException {
        this.controleur = controleur;

        this.controleur.demarrer(5, 5, 4);

        this.setTitle("Robot");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6));
        this.setMinimumSize(new Dimension((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        menu();

        jpanelDroite = new JPanel();
        jpanelDroite.setLayout(new GridLayout(5, 5, 2, 2));
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
    }

    public void voirlo() throws InterruptedException {

        Iterator<Robot> iter = this.controleur.getModele().getPartie().getListRobot().iterator();
        while (iter.hasNext()) {
            Robot robo = iter.next();
            System.err.println(" Avant Nom " + robo.getNom()
                    + " Position " + robo.getPosition() + " Direction " + robo.getDirection() + " energie " + robo.getEnergie());
        }

        Iterator<Robot> iteRobot = this.controleur.getModele().getPartie().getListRobot().iterator();
        while (iteRobot.hasNext()) {
            Robot robo = iteRobot.next();
            Strategy strategy = new StgLancerMissile(this.controleur.getModele().getPartie(), robo);
            strategy.renvoyerPartie();
            this.controleur.getModele().notifyObserver();
        }

        this.controleur.getModele().notifyObserver();

        Iterator<Missile> iteMisssile = this.controleur.getModele().getPartie().getListMissile().iterator();
        while (iteMisssile.hasNext()) {

            Missile mis = iteMisssile.next();

            if ((!listMissileForDelete2.isEmpty()) && (listMissileForDelete2.contains(mis))) {
                iteMisssile.remove();
            } else {
                listMissileForDelete = mis.seDeplacer(mis.getDirection(), mis.getPosition(),
                        this.controleur.getModele().getPartie(), images, mis.getNom(), this.controleur);
            }

            if (listMissileForDelete.size() == 1) {
                iteMisssile.remove();
            } else {
                for (int i = 0; i < listMissileForDelete.size(); i++) {
                    if (listMissileForDelete.get(i).getNom().equals(mis.getNom())) {
                        iteMisssile.remove();
                    } else {
                        listMissileForDelete2.add(listMissileForDelete.get(i));
                    }
                }
            }
            listMissileForDelete.clear();

            this.controleur.getModele().notifyObserver();
        }
        listMissileForDelete2.clear();
        this.controleur.getModele().notifyObserver();

        Iterator<Robot> iter2 = this.controleur.getModele().getPartie().getListRobot().iterator();
        while (iter2.hasNext()) {
            Robot robo = iter2.next();
            System.err.println(" Après Nom " + robo.getNom()
                    + " Position " + robo.getPosition() + " Direction " + robo.getDirection() + " energie " + robo.getEnergie());
        }

        System.err.println("taille finale de missile " + this.controleur.getModele().getPartie().getListMissile().size());

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
