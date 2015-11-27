/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import controleur.Controleur;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import modele.Modele;
import vue.Vue;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        Modele modele = new Modele();

        Controleur controleur = new Controleur(modele);

        Vue vue = new Vue(controleur);

        modele.addObserver(vue);

    }

}
