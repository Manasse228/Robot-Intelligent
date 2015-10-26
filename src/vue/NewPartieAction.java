package vue;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public class NewPartieAction extends AbstractAction{
	private static final long serialVersionUID = 7773581269766898715L;
	
    protected KeyStroke accelerateur = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK);
	protected Vue vue;
	
	public NewPartieAction(String texte, Vue vue) {
		super(texte);
		putValue(Action.NAME, texte);
		putValue(Action.ACCELERATOR_KEY, accelerateur);
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
		  FenNewPartie fen = new FenNewPartie(vue);
		  fen.setVisible(true);
	}

}
