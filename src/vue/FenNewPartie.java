package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class FenNewPartie extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	;
  private JLabel labelRobotname = new JLabel("Nombre de Robot:  ");
    private JLabel labelHateurName = new JLabel("Nombre de Hauteur: ");
    private JLabel labellargeurName = new JLabel("Nombre de largeur: ");
    private JTextField textNbreRobot = new JTextField(4);
   private JTextField textLargeurgrille = new JTextField(4);
   private JTextField textHauteurgrille = new JTextField(4);
    private JButton buttonValide = new JButton("Validé");
    private JFormattedTextField largeurGrille, hauteurGrille, NbreRobot ;
    private JButton validateBouton ;
	private final Vue vue ;

	public FenNewPartie(Vue vue){
		this.vue = vue ;

		this.setTitle("Nouvelle partie");
		this.setSize(new Dimension(340, 280));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);		
//		this.largeur = new JFormattedTextField() ;
//		this.largeur.setPreferredSize(new Dimension(170, 40));
//		this.largeur.setText("10");
//		this.largeur.setFont(new Font("Tahoma", Font.BOLD, 12)) ;	
//		this.largeur.setHorizontalAlignment(JFormattedTextField.CENTER);
//		this.largeur.setEnabled(false);
//		this.largeur.addKeyListener(new FiltreJTF());
		this.setContentPane(initComponents());
                
	}
	
	public JPanel initComponents() {
            
         JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         newPanel.setBackground(Color.white);
         
        // add components to the panel
//        constraints.gridx = 0;
//        constraints.gridy = 0;     
//        newPanel.add(labelRobotname, constraints);
//   
//        constraints.gridx = 1;
//        newPanel.add(textNbreRobot, constraints);
//         
//        constraints.gridx = 0;
//        constraints.gridy = 1;     
//        newPanel.add(labelHateurName, constraints);
//         
//        constraints.gridx = 1;
//        newPanel.add(textLargeurgrille, constraints);
//        
//        constraints.gridx = 0;
//        constraints.gridy = 3;     
//        newPanel.add(labelHateurName, constraints);
//         
//        constraints.gridx = 3;
//        newPanel.add(textLargeurgrille, constraints);
//         
//        constraints.gridx = 0;
//        constraints.gridy = 3;
//        constraints.gridwidth = 2;
//        constraints.anchor = GridBagConstraints.CENTER;
//        newPanel.add(buttonValide, constraints);
//       
//		//gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 3; gbc.gridheight = 1;
//		//this.panel_parametres.add(validate, gbc);
		
                constraints.gridx = 0; 
                constraints.gridy = 0;
                constraints.gridwidth = 1; 
                constraints.gridheight = 1;
		newPanel.add(labelRobotname, constraints);
		
		constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.gridheight = 1;
		newPanel.add(textNbreRobot, constraints);
		
		constraints.gridx = 0; 
                constraints.gridy = 2; 
                constraints.gridwidth = 1;
                constraints.gridheight = 1;
		newPanel.add(labelHateurName, constraints);
		
		constraints.gridx = 0;
                constraints.gridy = 3;
                constraints.gridwidth = 2; 
                constraints.gridheight = 1;
		newPanel.add(textHauteurgrille, constraints);
		
		constraints.gridx = 0; 
                constraints.gridy = 4; 
                constraints.gridwidth = 1; 
                constraints.gridheight = 1;
		newPanel.add(labellargeurName, constraints);
		
		constraints.gridx = 0; 
                constraints.gridy = 5; 
                constraints.gridwidth = 2; 
                constraints.gridheight = 1;
		newPanel.add(textLargeurgrille, constraints);
		
		constraints.gridx = 0; 
                constraints.gridy = 6; 
                constraints.gridwidth = 1; 
                constraints.gridheight = 1;
		newPanel.add(buttonValide, constraints);
		
		constraints.gridx = 1; 
                constraints.gridy = 6; 
                constraints.gridwidth = 1; 
                constraints.gridheight = 1;
		newPanel.add(buttonValide, constraints);			
		this.buttonValide.addActionListener(new ValiderNouvellePartie("Valider la chose", vue, this)) ;
		return newPanel;
}
	
	
	public JButton getValidate() {
		return validateBouton;
	}

	public void setValidate(JButton validate) {
		this.validateBouton = validate;
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
		return NbreRobot;
	}

	public void setNbreRobotr(JFormattedTextField NbreRobot) {
		this.NbreRobot = NbreRobot;
	}

}
