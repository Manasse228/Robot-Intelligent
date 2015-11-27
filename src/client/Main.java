
package client;

import controleur.Controleur;
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
