/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import modele.Partie;

/**
 *
 * @author 21416699
 */
public interface Strategy {

    public Partie renvoyerPartie();

    /*
     /*
     Je parcours la liste des robots avec un itérateur parce que la liste 
     peut se modifier à tout moment c'est à dire si un robot tue un robot faut 
     que le robot mort sort de la liste
     */
//        Iterator it = this.controleur.getModele().getPartie().getListRobot().iterator();
//        while (it.hasNext()) {
//            Robot roboti = (Robot) it.next();
////        }
////        for (Robot roboti : this.controleur.getModele().getPartie().getListRobot()) {
//            if (robot.etatRobot(this.controleur.getModele().getPartie().getListRobot(), roboti) == true) {
//                it.remove();
//            } else {
//                Missile missile = new Missile(roboti.getPosition(), roboti.getDirection(), robot.getNom());
//                this.controleur.getModele().lancerMissile(roboti.getDirection(), roboti.getPosition(), this.controleur.getModele().getPartie(), images, missile);
//                this.controleur.getModele().notifyObserver();
//                
//            }
//
//        }
//        it = this.controleur.getModele().getPartie().getListRobot().iterator();
//        while (it.hasNext()) {
//            Robot roboti = (Robot) it.next();
//            System.err.println("Robot position "+roboti.getPosition()+" et il est "+roboti.isMort());
//        }
//        
//        for (int i = 0; i < 6; i++) {
//            mouvementRobot(this.controleur.modele.getPartie());
//            this.controleur.getModele().notifyObserver();
//        }
//          for (int i = 0; i < 60; i++) {
//              System.out.println(i);
//            mouvementRobot(this.controleur.modele.getPartie());
//            this.controleur.getModele().notifyObserver();
//
//            }
}
