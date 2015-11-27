package vue;

import modele.Images;
import modele.CaseGraphique;
import java.awt.Color;
import controleur.Controleur;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import modele.Missile;
import controleur.Partie;
import javax.swing.JOptionPane;
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

public class Vue extends JFrame implements Observer {

    Position position;
    Robot robot;
    Images images;
    Controleur controleur;
    JPanel jpanelGauche, contentPanel;
    JLabel jlNomRobot, jlEnergie;
    ArrayList<Missile> listMissileVue;
    CaseGraphique jpanelDroite;
    GridBagConstraints constraints;
    ConstructionArbreBinaire constructionArbreBinaire;
    BoxLayout boxLayout;

    public Vue(Controleur controleur) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Robot");
        this.setSize((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6));
        this.setMinimumSize(new Dimension((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        listMissileVue = new ArrayList<>();
        this.controleur = controleur;

        menu();
        init();
        this.setVisible(true);
    }

    public void init() {
        constraints = new GridBagConstraints();
        images = new Images();
        robot = new Robot();
        contentPanel = new JPanel();
        jpanelGauche = new JPanel();
        jpanelDroite = new CaseGraphique(images.renvoiImagesBackground().getImage());
        boxLayout = new BoxLayout(jpanelGauche, WIDTH);

        jpanelGauche.setBackground(Color.BLACK);
        contentPanel.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
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

    }

    public void centreDeCommandement(String ordre, Robot r) {
        Strategy strategy;
        switch (ordre) {
            case "LancerMissile":
//                System.err.println("LancerMissile " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgLancerMissile(this.controleur.getModele().getPartie(), r, this.controleur.getModele());
                strategy.renvoyerPartie();
                break;
            case "LancerLaser":
//                System.err.println("LancerLaser " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgLancerLaser(this.controleur.getModele().getPartie(), r, this.controleur.getModele());
                strategy.renvoyerPartie();
                break;
            case "Avancer":
//                System.err.println("Avancer " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgAvancer(this.controleur.getModele().getPartie(), r, this.controleur.getModele());
                strategy.renvoyerPartie();
                break;
            case "Debloquer":
//                System.err.println("Debloquer " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgDebloquer(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "Reculer":
//                System.err.println("Reculer " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgReculer(this.controleur.getModele().getPartie(), r, this.controleur.getModele());
                strategy.renvoyerPartie();
                break;
            case "RienFaire":
//                System.err.println("RienFaire " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgRienFaire(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "Tourner":
//                System.err.println("Tourner " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgTourner(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "ActiverBouclier":
//                System.err.println("ActiverBouclier " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgActiverBouclier(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
            case "DesactiverBouclier":
//                System.err.println("DesactiverBouclier " + r.getPosition() + " Energie " + r.getEnergie() + " Nom " + r.getNom() + " DIrection " + r.getDirection());
                strategy = new StgDesactiverBouclier(this.controleur.getModele().getPartie(), r);
                strategy.renvoyerPartie();
                break;
        }
    }

    public JLabel labelEnergie(Robot r) {
        JLabel j;
        j = new JLabel(r.getNom());
        j.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 12));
        if (r.getEnergie() <= 5) {
            j.setForeground(Color.RED);
        } else {
            if (r.getEnergie() <= 10) {
                j.setForeground(Color.YELLOW);
            } else {
                j.setForeground(Color.GREEN);
            }
        }
        return j;
    }

    public void listRobotMissileVivants() {
        jpanelGauche.removeAll();
        jpanelGauche.setLayout(boxLayout);

        this.controleur.getModele().getPartie().getListRobot().clear();
        this.controleur.getModele().getPartie().getListMissile().clear();
        for (CaseGraphique c : this.controleur.getModele().getPartie().getTriListCaseGraphique()) {
            if (c.getCaze() instanceof Robot) {
                this.controleur.getModele().getPartie().getListRobot().add((Robot) c.getCaze());

                jlNomRobot = labelEnergie((Robot) c.getCaze());
                jpanelGauche.add(jlNomRobot);

            }
            if (c.getCaze() instanceof Missile) {
                this.controleur.getModele().getPartie().getListMissile().add((Missile) c.getCaze());
            }
        }

    }

    public void jouer(final Vue v) {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (v.controleur.getModele().getPartie().getListRobot().size() > 1) {
                    listRobotMissileVivants();
                    listMissileVue.clear();
                    for (Robot r : v.controleur.getModele().getPartie().getListRobot()) {

                        for (CaseGraphique c : v.controleur.getModele().getPartie().getTriListCaseGraphique()) {
                            if ((c.getCaze() instanceof Robot)
                                    && (Position.egalite(r.getPosition(), c.getCaze().position()) == true)) {
                                Robot robo = (Robot) c.getCaze();

                                for (Missile m : v.controleur.getModele().getPartie().getListMissile()) {
                                    if (m.getNom().equals(robo.getNom())) {
                                        m.seDeplacer(m.getDirection(), m.getPosition(), v.controleur.getModele().getPartie(),
                                                images, m.getNom(), v.controleur);
                                        listMissileVue.add(m);
                                    }
                                }

                                constructionArbreBinaire = new ConstructionArbreBinaire(v.controleur.getModele().getPartie(), robo);
                                centreDeCommandement(constructionArbreBinaire.action(), robo);
                                v.getControleur().getModele().notifyObserver();
                            }
                        }
                    }

                    for (Missile m : v.controleur.getModele().getPartie().getListMissile()) {
                        if (!listMissileVue.contains(m)) {
                            m.seDeplacer(m.getDirection(), m.getPosition(), v.controleur.getModele().getPartie(),
                                    images, m.getNom(), v.controleur);
                        }
                    }

                }
                // Fin de partie du Game
                fenetreFInDuGame();
            }
        };
        t.start();

    }

    public void fenetreFInDuGame() {
        JOptionPane.showMessageDialog(this,
                "Fin de la partie",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void afficherCase(Controleur controleur) {
        for (CaseGraphique caseGraphique : controleur.modele.getPartie().getTriListCaseGraphique()) {
            this.jpanelDroite.add(caseGraphique);
        }
        setControleur(controleur);
        this.controleur.getModele().notifyObserver();
    }

    /*
     Création du ménu 
     */
    public void menu() {

        JMenuBar menuBar = new JMenuBar();
        JMenu menuBar_fichier = new JMenu("Fichier");
        menuBar_fichier.add(new ActionNewPartie("Nouvelle partie", this));
        menuBar_fichier.addSeparator();

        menuBar_fichier.addSeparator();
        menuBar_fichier.add(new ActionQuitter("Quitter", this));
        menuBar.add(menuBar_fichier);
        setJMenuBar(menuBar);

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
            Thread.sleep(800);
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

    public CaseGraphique getJpanelDroite() {
        return jpanelDroite;
    }

    public void setJpanelDroite(CaseGraphique jpanelDroite) {
        this.jpanelDroite = jpanelDroite;
    }

}
