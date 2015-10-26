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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author sergeokov
 */
public class FenetreAide extends JFrame {

    private final Vue vue;
    private JButton buton;
    private JLabel labelRobotNord = new JLabel("La direction Robot est du Nord  ");
    private JLabel labelRobotSud = new JLabel("La direction Robot est du Sud ");
    private JLabel labelRobotEst = new JLabel("La direction Robot est du Est ");
    private JLabel labelRobotOuest = new JLabel("La direction Robot est du Ouest ");
    private JLabel labelRobotMissile = new JLabel("Le Missile");

//    java.net.URL imgUrl = getClass().getResource("images/robotEst.jpg");
//    ImageIcon icon = new ImageIcon(imgUrl);
    public FenetreAide(Vue vue) {
        this.vue = vue;

        this.setTitle("Aide du Joueur");
        this.setSize(new Dimension(450, 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);

                //this.getContentPane().add(labelRobotNord);
        this.setVisible(true);
        this.setContentPane(initComponents());
    }

    public JPanel initComponents() {

        JPanel newPanel = new JPanel(new GridBagLayout());
        ImageIcon ico = new ImageIcon(getClass().getResource("/images/robotNord.jpg"));
        ImageIcon ico1 = new ImageIcon(getClass().getResource("/images/robotSud.jpg"));
        ImageIcon ico2 = new ImageIcon(getClass().getResource("/images/robotEst.jpg"));
        ImageIcon ico3 = new ImageIcon(getClass().getResource("/images/robotOuest.jpg"));
        ImageIcon ico4 = new ImageIcon(getClass().getResource("/images/missile_est.jpg"));
        ImageIcon ico5 = new ImageIcon(getClass().getResource("/images/robotEst.jpg"));
        ImageIcon ico6 = new ImageIcon(getClass().getResource("/images/robotEst.jpg"));

        ico = new ImageIcon(ico.getImage().getScaledInstance(60, 60, 25));
        ico1 = new ImageIcon(ico1.getImage().getScaledInstance(60, 60, 25));
        ico2 = new ImageIcon(ico2.getImage().getScaledInstance(60, 60, 25));
        ico3 = new ImageIcon(ico3.getImage().getScaledInstance(60, 60, 25));
        ico4 = new ImageIcon(ico4.getImage().getScaledInstance(60, 60, 25));
        ico5 = new ImageIcon(ico5.getImage().getScaledInstance(60, 60, 25));
        ico6 = new ImageIcon(ico6.getImage().getScaledInstance(60, 60, 25));

        labelRobotNord.setIcon(ico);
        labelRobotSud.setIcon(ico1);
        labelRobotEst.setIcon(ico2);
        labelRobotOuest.setIcon(ico3);
        labelRobotMissile.setIcon(ico4);

                   //lol.setIcon(new ImageIcon(getClass().getResource("/images/robotEst.jpg")));
        //labelRobotNord.setIconImage(new ImageIcon(getClass().getResource("/img/fond1.png")).getImage());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        newPanel.setBackground(Color.white);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        newPanel.add(labelRobotNord, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        newPanel.add(labelRobotSud, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        newPanel.add(labelRobotOuest, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        newPanel.add(labelRobotEst, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        newPanel.add(labelRobotEst, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        newPanel.add(labelRobotMissile, constraints);

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
        return newPanel;
    }
}
