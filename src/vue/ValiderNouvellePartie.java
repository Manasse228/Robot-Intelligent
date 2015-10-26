package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

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

        int largeur = Integer.parseInt(fen.getLargeur().getText());
        int hauteur = Integer.parseInt(fen.getHauteur().getText());
        int nbRobots = Integer.parseInt(fen.getNbreRobot().getText());

        //vue.setContent();
        vue.setLargeurGrille(largeur);
        vue.setHauteurGrille(hauteur);
        vue.setNreRobot(nbRobots);
        vue.getControleur().demarrer(largeur, hauteur, nbRobots);
        fen.dispose();

    }

}
