/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


public class CaseVide implements Case{
    
    Position position;

    public CaseVide(Position position) {
        this.position = position;
    }
    
    

    @Override
    public Position position() {
         return  position ;
    }

    @Override
    public String toString() {
        return "CaseVide";
    }
    
    
  
}
