package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class FenNewPartie extends JFrame {

    private JLabel labelRobotname, labelHateurName, labellargeurName;
    private JButton buttonValide;
    private JFormattedTextField largeurGrille, hauteurGrille, nbreRobot;
    private JPanel panel_parametres;
    public Vue vue;

    public FenNewPartie(Vue vue) {
        this.vue = vue;

        labelRobotname = new JLabel("Nombre de Robot(s):  ");
        labelHateurName = new JLabel("Hauteur: ");
        labellargeurName = new JLabel("Largeur: ");
        buttonValide = new JButton("Jouer");

        this.setTitle("Nouvelle Partie");
        this.setSize(new Dimension(340, 280));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setContentPane(initComponents());

    }

    public JPanel initComponents() {

        this.largeurGrille = new JFormattedTextField();
        this.largeurGrille.setPreferredSize(new Dimension(170, 40));
        this.largeurGrille.setText("4");
        this.largeurGrille.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.largeurGrille.setHorizontalAlignment(JFormattedTextField.CENTER);
        this.largeurGrille.addKeyListener(new FiltreJTF());

        this.hauteurGrille = new JFormattedTextField();
        this.hauteurGrille.setPreferredSize(new Dimension(170, 40));
        this.hauteurGrille.setText("4");
        this.hauteurGrille.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.hauteurGrille.setHorizontalAlignment(JFormattedTextField.CENTER);
        this.hauteurGrille.addKeyListener(new FiltreJTF());

        this.nbreRobot = new JFormattedTextField();
        this.nbreRobot.setPreferredSize(new Dimension(170, 40));
        this.nbreRobot.setText("7");
        this.nbreRobot.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.nbreRobot.setHorizontalAlignment(JFormattedTextField.CENTER);
        this.nbreRobot.addKeyListener(new FiltreJTF());

        this.panel_parametres = new JPanel();
        this.panel_parametres.setLayout(new GridBagLayout());
        this.panel_parametres.setSize(this.getWidth(), (this.getHeight() / 3) * 2);
        this.panel_parametres.setBackground(Color.white);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 8;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.panel_parametres.add(this.labellargeurName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        this.panel_parametres.add(this.largeurGrille, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.panel_parametres.add(this.labelHateurName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        this.panel_parametres.add(this.hauteurGrille, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.panel_parametres.add(this.labelRobotname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        this.panel_parametres.add(this.nbreRobot, gbc);

        buttonValide.addActionListener(new ValiderNouvellePartie("Valider la chose", this.vue, this));
        content.add(panel_parametres, BorderLayout.NORTH);
        this.buttonValide.setPreferredSize(new Dimension(95, 100));
        content.add(buttonValide, BorderLayout.SOUTH);
        return content;
    }

    public JFormattedTextField getLargeur() {
        return largeurGrille;
    }

    public void setLargeur(JFormattedTextField largeur) {
        this.largeurGrille = largeur;
    }

    public JFormattedTextField getHauteur() {
        return hauteurGrille;
    }

    public void setHauteur(JFormattedTextField hauteur) {
        this.hauteurGrille = hauteur;
    }

    public JFormattedTextField getNbreRobot() {
        return nbreRobot;
    }

    public void setNbreRobotr(JFormattedTextField nbreRobot) {
        this.nbreRobot = nbreRobot;
    }

    public JFormattedTextField getLargeurGrille() {
        return largeurGrille;
    }

    public void setLargeurGrille(JFormattedTextField largeurGrille) {
        this.largeurGrille = largeurGrille;
    }

    public JFormattedTextField getHauteurGrille() {
        return hauteurGrille;
    }

    public void setHauteurGrille(JFormattedTextField hauteurGrille) {
        this.hauteurGrille = hauteurGrille;
    }

    public JLabel getLabelRobotname() {
        return labelRobotname;
    }

    public void setLabelRobotname(JLabel labelRobotname) {
        this.labelRobotname = labelRobotname;
    }

    public JLabel getLabelHateurName() {
        return labelHateurName;
    }

    public void setLabelHateurName(JLabel labelHateurName) {
        this.labelHateurName = labelHateurName;
    }

    public JLabel getLabellargeurName() {
        return labellargeurName;
    }

    public void setLabellargeurName(JLabel labellargeurName) {
        this.labellargeurName = labellargeurName;
    }

    public JButton getButtonValide() {
        return buttonValide;
    }

    public void setButtonValide(JButton buttonValide) {
        this.buttonValide = buttonValide;
    }

    public Vue getVue() {
        return vue;
    }

}
