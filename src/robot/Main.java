/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.util.Random;
import vue.Vue;

/**
 *
 * @author 21416699
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
Random rand = new Random();
        // TODO code application logic here
      // Grille grille = new Grille(3, 5, 3);
       // System.out.println("taille "+grille.getListCase().size());
        Vue vue = new Vue(3, 4, 2);
        vue.setVisible(true);
        
        
//       for(Case r:grille.getListCase()){
//           if("Robot".equals(r.toString())){
//               Robot c=(Robot) r;
//           //    System.out.println("***" + r.toString() +" "+ r.position()+" "+c.getNom());
//           }
//          
//       }
//       
//       for(Position r:grille.getListPositionRobots()){
//        //  System.out.println("##" +  r.toString());
//       }
//       
//       for(Case r:grille.getListCase()){
//      //  System.out.println( r.toString()+ r.position());
//       }
       
//       System.out.println("taille "+grille.getListsNomRobot().size());
//       for(String r:grille.getListsNomRobot()){
//        System.out.println("nom " + r);
//       }
       
      
    }
    
}
