/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author sergeokov
 */
public class FenetreAide extends JFrame {

    private final Vue vue;

    private final JLabel labelRobotNord = new JLabel("La direction Robot est du Nord  ");
    private final JLabel labelRobotSud = new JLabel("La direction Robot est du Sud ");
    private final JLabel labelRobotEst = new JLabel("La direction Robot est du Est ");
    private final JLabel labelRobotOuest = new JLabel("La direction Robot est du Ouest ");

    private final JLabel labelRobotMissile = new JLabel("Le Missile");
    private final JLabel labelRobotexplo = new JLabel(" Robot tué par une Missile");
    private final JLabel labelRobotMisAttack = new JLabel("Explosion de deux missiles");
    private final JLabel labelRobotMisAttack1 = new JLabel("Robot tué par laser");
    private final JLabel labelRobotBouclier = new JLabel("Ceci indique que le robot est protégé par un bouclier");
    private final JLabel message1 = new JLabel("EKLOU Manasse Serge");
    protected static final JLabel message2 = new JLabel("KAPEMA Omba Serge");
    protected static final JLabel message3 = new JLabel("KAPEMA Shako Cedrick");
    protected static final JLabel message4 = new JLabel("VATEL Nicolas");
    protected static final JLabel message5 = new JLabel("MANEL Rekik");
    protected static final JLabel message = new JLabel("FILS ROUGE:\n"
            + "Le but du jeu est de permettre les affrontements entre robots sur grille des groupes\n"
            + "Créé par EKLOU Serge, KAPEMA Serge, KAPEMA Cedrick,  \n"
            + "VATEL Nicolas et MANEL Rekik\n");

//    java.net.URL imgUrl = getClass().getResource("images/robotEst.jpg");
//    ImageIcon icon = new ImageIcon(imgUrl);
    public FenetreAide(Vue vue) {
        this.vue = vue;

        this.setTitle("Aide du Joeur");
        this.setSize(new Dimension(550, 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);

        this.setVisible(true);
        initComponents();
    }

    private void initComponents() {
        JTabbedPane tabPane = new JTabbedPane();
        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new GridBagLayout());
        JPanel panel3 = new JPanel(new GridBagLayout());
        JPanel panel4 = new JPanel(new GridBagLayout());

        ImageIcon ico = new ImageIcon(getClass().getResource("/images/robotNordSansBouclier.jpg"));
        ImageIcon ico1 = new ImageIcon(getClass().getResource("/images/robotSudSansBouclier.jpg"));
        ImageIcon ico2 = new ImageIcon(getClass().getResource("/images/robotEstSansBouclier.jpg"));
        ImageIcon ico3 = new ImageIcon(getClass().getResource("/images/robotOuestSansBouclier.jpg"));

        ImageIcon ico4 = new ImageIcon(getClass().getResource("/images/missile_est.jpg"));
        ImageIcon ico5 = new ImageIcon(getClass().getResource("/images/sans-bouclier.jpg"));
        ImageIcon ico6 = new ImageIcon(getClass().getResource("/images/explosion.jpg"));
        ImageIcon ico7 = new ImageIcon(getClass().getResource("/images/sans-bouclier-laser.jpg"));

        ImageIcon ico8 = new ImageIcon(getClass().getResource("/images/robotEst.jpg"));

        ico = new ImageIcon(ico.getImage().getScaledInstance(60, 60, 25));
        ico1 = new ImageIcon(ico1.getImage().getScaledInstance(60, 60, 25));
        ico2 = new ImageIcon(ico2.getImage().getScaledInstance(60, 60, 25));
        ico3 = new ImageIcon(ico3.getImage().getScaledInstance(60, 60, 25));
        ico4 = new ImageIcon(ico4.getImage().getScaledInstance(60, 60, 25));
        ico5 = new ImageIcon(ico5.getImage().getScaledInstance(60, 60, 25));
        ico6 = new ImageIcon(ico6.getImage().getScaledInstance(60, 60, 25));
        ico7 = new ImageIcon(ico7.getImage().getScaledInstance(60, 60, 25));
        ico8 = new ImageIcon(ico8.getImage().getScaledInstance(60, 60, 25));

        labelRobotNord.setIcon(ico);
        labelRobotSud.setIcon(ico1);
        labelRobotEst.setIcon(ico2);
        labelRobotOuest.setIcon(ico3);
        labelRobotMissile.setIcon(ico4);
        labelRobotexplo.setIcon(ico5);
        labelRobotMisAttack.setIcon(ico6);
        labelRobotMisAttack1.setIcon(ico7);
        labelRobotBouclier.setIcon(ico8);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.white);
        panel4.setBackground(Color.white);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel4.add(message, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        panel4.add(labelRobotexplo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel4.add(labelRobotOuest, constraints);
        //////////////////////////////////////////////////////
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel2.add(labelRobotMisAttack, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        panel2.add(labelRobotexplo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel2.add(labelRobotOuest, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        panel2.add(labelRobotMisAttack1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel1.add(labelRobotNord, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        panel1.add(labelRobotSud, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel1.add(labelRobotOuest, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        panel1.add(labelRobotEst, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel1.add(labelRobotEst, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        panel1.add(labelRobotBouclier, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //newPanel.add(buttonValide, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //newPanel.add(buttonValide, constraints);

        tabPane.add("Position du robot", panel1);
        tabPane.add("Destruction du robot", panel2);

        tabPane.add("Apropos", panel4);
        this.add(tabPane);

    }
}
