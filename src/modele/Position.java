/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


public class Position {
    
    int x,y;

    public Position(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public static boolean egalite(Position a, Position b){
        if( (a.getX() == b.getX()) && (a.getY() == b.getY()) ){
            return true;
        }else{
            return false;
        }
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return " X= " + x + " et  Y= " + y ;
    }
    
    
    
}
