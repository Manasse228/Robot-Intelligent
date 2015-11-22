/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author 21416699
 */
public class FiltreJTF extends KeyAdapter {

    @Override
    public void keyTyped(KeyEvent arg0) {
        char c = arg0.getKeyChar();
        if (!(c >= '0' && c <= '9')) {
            arg0.consume();
        }

    }
}
