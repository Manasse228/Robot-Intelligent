/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import controleur.Controleur;
import modele.Modele;
import vue.Vue;

/**
 *
 * @author 21416699
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {


        Modele modele = new Modele();

        Controleur controleur = new Controleur(modele);

        Vue vue = new Vue(controleur);

        modele.addObserver(vue);
        
        vue.voirlo();

    }

}
