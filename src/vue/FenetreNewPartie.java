/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author sergeokov
 */
public class FenetreNewPartie extends JFrame {
 
    private final Vue vue ;
    private JButton buton;

    public FenetreNewPartie(Vue vue) {
        this.vue = vue;
        
        this.setTitle("Nouvelle partie");
		this.setSize(new Dimension(300, 350));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);		
		
                buton = new JButton("voir");
		this.getContentPane().add(buton);
                
                this.setVisible(true);
    }

}
