/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.Controleur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import modele.Missile;
import modele.Partie;
import modele.Position;
import modele.Robot;
import modele.arbreBinaire.ConstructionArbreBinaire;
import strategy.StgActiverBouclier;
import strategy.StgAvancer;
import strategy.StgDebloquer;
import strategy.StgDesactiverBouclier;
import strategy.StgLancerLaser;
import strategy.StgLancerMissile;
import strategy.StgReculer;
import strategy.StgRienFaire;
import strategy.StgTourner;
import strategy.Strategy;

/**
 *
 * @author sergeokov
 */
public class Vue extends JFrame implements Observer {

    Position position;
    Robot robot;
    Images images;
    Controleur controleur;
    JPanel jpanelGauche, jpanelDroite, contentPanel;
    ArrayList<Missile> listMissileForDelete, listMissileForDelete2 = new ArrayList<>();
    GridBagConstraints constraints;
    ConstructionArbreBinaire constructionArbreBinaire;
    private int hauteurGrille, largeurGrille, NbreGrille;

    public Vue(Controleur controleur) throws InterruptedException {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Robot");
        this.setSize((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6));
        this.setMinimumSize(new Dimension((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        constraints = new GridBagConstraints();
        images = new Images();
        robot = new Robot();
        contentPanel = new JPanel();
        jpanelGauche = new JPanel();
        jpanelDroite = new JPanel();

        this.controleur = controleur;
        this.controleur.demarrer(2, 2, 4);
        menu();
        afficherCase(controleur);

        jpanelDroite.setLayout(new GridLayout(2, 2, 2, 2));

        JButton bb = new JButton("missile");
        jpanelGauche.add(bb);

        jpanelDroite.setBackground(Color.black);
        jpanelGauche.setBackground(Color.red);

        contentPanel.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        constraints.weighty = 0.7;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.BOTH;
        contentPanel.add(jpanelGauche, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.7;
        constraints.weighty = 0.7;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        contentPanel.add(jpanelDroite, constraints);

        this.setContentPane(contentPanel);
        this.setVisible(true);
    }

    public void centreDeCommandement(String ordre, Robot r) {
        Strategy strategy;
        switch (ordre) {
            case "LancerMissile":
                System.err.println("LancerMissile " + r.getPosition());
                strategy = new StgLancerMissile(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "LancerLaser":
                System.err.println("LancerLaser " + r.getPosition());
//                strategy = new StgLancerLaser(this.controleur.getModele().getPartie(), r);
//                strategy.renvoyerPartie();
                break;
            case "Avancer":
                System.err.println("Avancer " + r.getPosition());
                strategy = new StgAvancer(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "Debloquer":
                System.err.println("Debloquer " + r.getPosition());
                strategy = new StgDebloquer(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "Reculer":
                System.err.println("Reculer " + r.getPosition());
                strategy = new StgReculer(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "RienFaire":
                System.err.println("RienFaire " + r.getPosition());
                strategy = new StgRienFaire(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "Tourner":
                System.err.println("Tourner " + r.getPosition());
                strategy = new StgTourner(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "ActiverBouclier":
                System.err.println("ActiverBouclier " + r.getPosition());
                strategy = new StgActiverBouclier(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "DesactiverBouclier":
                System.err.println("DesactiverBouclier " + r.getPosition());
                strategy = new StgDesactiverBouclier(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
        }
    }

    public void voirlo() throws InterruptedException {

//        Iterator<Robot> iter = this.controleur.getModele().getPartie().getListRobot().iterator();
//        while (iter.hasNext()) {
//            Robot robo = iter.next();
//            System.err.println(" Avant Nom " + robo.getNom() //+"Bouclier "+robo.isBouclier()
//                    + " Position " + robo.getPosition() + " Direction " + robo.getDirection() + " energie " + robo.getEnergie());
//        }
//        while (this.controleur.getModele().getPartie().getListRobot().size() != 1) {
            Iterator<Robot> iteRobot = this.controleur.getModele().getPartie().getListRobot().iterator();
            while (iteRobot.hasNext()) {
                Robot robo = iteRobot.next();
                this.controleur.getModele().notifyObserver();
                constructionArbreBinaire = new ConstructionArbreBinaire(this.controleur.getModele().getPartie(), robo);
                centreDeCommandement(constructionArbreBinaire.action(), robo);
                this.controleur.getModele().notifyObserver();
            }
//        }

//
//        Iterator<Missile> iteMisssile = this.controleur.getModele().getPartie().getListMissile().iterator();
//        while (iteMisssile.hasNext()) {
//
//            Missile mis = iteMisssile.next();
//            if ((!listMissileForDelete2.isEmpty()) && (listMissileForDelete2.contains(mis))) {
//                iteMisssile.remove();
//            } else {
//                listMissileForDelete = mis.seDeplacer(mis.getDirection(), mis.getPosition(),
//                        this.controleur.getModele().getPartie(), images, mis.getNom(), this.controleur);
//            }
//
//            if (listMissileForDelete.size() == 1) {
//                iteMisssile.remove();
//            } else {
//                for (int i = 0; i < listMissileForDelete.size(); i++) {
//                    if (listMissileForDelete.get(i).getNom().equals(mis.getNom())) {
//                        iteMisssile.remove();
//                    } else {
//                        listMissileForDelete2.add(listMissileForDelete.get(i));
//                    }
//                }
//            }
//            listMissileForDelete.clear();
//        }
//        listMissileForDelete2.clear();
//        this.controleur.getModele().notifyObserver();
//        iteRobot = this.controleur.getModele().getPartie().getListRobot().iterator();
//        while (iteRobot.hasNext()) {
//            Robot robo = iteRobot.next();
//            Strategy strategy = new StgAvancer(this.controleur.getModele().getPartie(), robo);
//            strategy.renvoyerPartie();
//            if (robo == this.controleur.getModele().getPartie().getRobotMorgue()) {
//                iteRobot.remove();
//            }
//            this.controleur.getModele().notifyObserver();
//        }
//        Iterator<Robot> iter2 = this.controleur.getModele().getPartie().getListRobot().iterator();
//        while (iter2.hasNext()) {
//            Robot robo = iter2.next();
//            System.err.println(" Après Nom " + robo.getNom() //+"Bouclier "+robo.isBouclier()
//                    + " Position " + robo.getPosition() + " Direction " + robo.getDirection() + " energie " + robo.getEnergie());
//        }
//        System.err.println("taille finale de missile " + this.controleur.getModele().getPartie().getListMissile().size());
        this.controleur.getModele().notifyObserver();
    }

    public void afficherCase(Controleur controleur) {
        for (CaseGraphique caseGraphique : controleur.modele.getPartie().getTriListCaseGraphique()) {
            this.jpanelDroite.add(caseGraphique);
        }

    }

    public void menu() {

        JMenuBar menuBar = new JMenuBar();
        JMenu menuBar_fichier = new JMenu("Fichier");
        //JMenu menuBar_Aprpos = new JMenu("Apropos");
        menuBar_fichier.add(new NewPartieAction("Nouvelle partie", this));
        menuBar_fichier.addSeparator();

        menuBar_fichier.addSeparator();
        menuBar_fichier.add(new ActionQuitter("Quitter", this));
        // menuBar_Aprpos.add(new NewPartieAction("Apropos", this));
        menuBar.add(menuBar_fichier);
        setJMenuBar(menuBar);
        //JMenuBar menuBar = new JMenuBar();

        JMenu jMenu = new JMenu("Apropos");
        JMenuItem jMenuItem = new JMenuItem(new ActionAPropos("Apropos", this));
        JMenuItem jMenuItemAide = new JMenuItem(new ActionAide("Aide", this));
        jMenu.add(jMenuItem);
        jMenu.add(jMenuItemAide);
        menuBar.add(jMenu);
        this.setJMenuBar(menuBar);
    }

    @Override
    public void update(Observable o, Object obj) {
        Partie partie = ((Partie) obj);	// Recuperation de la partie envoyee par le modele

        this.revalidate();
        this.repaint();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.jpanelDroite.removeAll();
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

    public void setLargeurGrille(int largeurGrille) {
        this.largeurGrille = largeurGrille;
    }

    public void setHauteurGrille(int hauteurGrille) {
        this.hauteurGrille = hauteurGrille;
    }

    public void setNreRobot(int NbreGrille) {
        this.NbreGrille = NbreGrille;
    }

}
