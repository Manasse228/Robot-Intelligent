package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ActionNewPartie extends AbstractAction {

    public Vue vue;

    public ActionNewPartie(String texte, Vue vue) {
        super(texte);
        putValue(Action.NAME, texte);
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        FenNewPartie fen = new FenNewPartie(this.vue);
        fen.setVisible(true);
//        this.vue.setEnabled(false);
    }

}
