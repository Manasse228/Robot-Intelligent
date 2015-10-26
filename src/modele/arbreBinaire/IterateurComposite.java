/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.arbreBinaire;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author sergeokov
 */
public class IterateurComposite implements Iterator{
    Stack pile = new Stack();

    public IterateurComposite(Iterator iterator) {
        pile.push(iterator);
    }
    
    

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
