package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ActionAide extends AbstractAction {

    protected Vue vue;

    ActionAide(String aide, Vue vue) {
        super(aide);
        putValue(Action.NAME, aide);
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        FenetreAide fen = new FenetreAide(vue);
        fen.setVisible(true);
    }

}
