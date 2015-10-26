package vue;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import javax.swing.KeyStroke;

public class ActionAide extends AbstractAction{
	private static final long serialVersionUID = 7773581269766898715L;
	
    protected KeyStroke accelerateur = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK);
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
