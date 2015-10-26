package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ActionQuitter extends AbstractAction{
	private static final long serialVersionUID = 980805590566244003L;
	
	protected Vue vue;
	
	public ActionQuitter(String texte, Vue vue) {
		super(texte);
		putValue(Action.NAME, texte);
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
