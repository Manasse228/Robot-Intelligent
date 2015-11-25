package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ActionAPropos extends AbstractAction {

    protected Vue vue;
    protected static final String message = "Robot Game:\n"
            + "Ce jeux est créé par les membres suivants: \n"
            + "Créé par EKLOU Serge, Kapema Serge, Kapema cedrick,  "
            + "vatel Nicolas et Manel Rekik\n";

    public ActionAPropos(String texte, Vue vue) {
        super(texte);
        putValue(Action.NAME, texte);
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(vue, message, (String) getValue(Action.NAME),
                JOptionPane.INFORMATION_MESSAGE, (ImageIcon) getValue(Action.SMALL_ICON));
    }
}
