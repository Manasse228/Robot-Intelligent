package vue;

import controleur.Controleur;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import modele.Modele;

public class ValiderNouvellePartie extends AbstractAction {

    private static final long serialVersionUID = -4564529052797763587L;

    protected Vue vue;
    protected FenNewPartie fen;

    public ValiderNouvellePartie(String texte, Vue vue, FenNewPartie fen) {
        super(texte);
        putValue(Action.NAME, texte);
        this.vue = vue;
        this.fen = fen;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        int largeur = Integer.parseInt(this.fen.getLargeur().getText());
        int hauteur = Integer.parseInt(this.fen.getHauteur().getText());
        int nbRobots = Integer.parseInt(this.fen.getNbreRobot().getText());

        if (nbRobots <= largeur * hauteur) {

           
            this.vue.getControleur().demarrer(hauteur, largeur, nbRobots);
            this.vue.getJpanelDroite().setLayout(new GridLayout(hauteur, largeur, 2, 2));
            this.vue.afficherCase(this.vue.getControleur());
            this.fen.dispose();
            this.vue.jouer(this.vue);
            

        } else {//Le nombre de robot est trop grand 
            JOptionPane.showMessageDialog(this.vue,
                    "Robot trop trop beaucoup; assurer vous de ne pas dépasser "
                    + "le nombre total de robots qui est " + largeur * hauteur + " Robots",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

}
