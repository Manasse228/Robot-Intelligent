/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author sergeokov
 */
public class ActionNewPartie_1 extends AbstractAction {

    protected Vue vue;

    public ActionNewPartie_1(Vue vue, String texte) {
        super(texte);
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      // vue.mouvementRobot();
    }

}
