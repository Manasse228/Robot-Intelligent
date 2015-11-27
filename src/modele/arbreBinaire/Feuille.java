package modele.arbreBinaire;

import java.util.ArrayList;
import java.util.Random;
import strategy.Strategy;

public class Feuille extends ArbreBinaire {

    String nom;

    Strategy strategy;

    public Feuille() {
    }

    public Feuille(String nom) {

        this.nom = nom;
    }

    /*
     Cette méthode renvoie une action à exécuter au hasard
     */
    public String aleatoire() {
        ArrayList<String> listMethode = new ArrayList<>();
        listMethode.add("Avancer");
        listMethode.add("Reculer");
        listMethode.add("RienFaire");
        listMethode.add("Tourner");
        listMethode.add("ActiverBouclier");
        listMethode.add("DesactiverBouclier();");

        Random rand = new Random();

        return listMethode.get(rand.nextInt(((listMethode.size() - 1) + 1) - 0));
    }

    public String avancer() {
        return "Avancer";
    }

    public String debloquer() {
        return "Debloquer";
    }

    public String lancerMissile() {
        return "LancerMissile";
    }

    public String lancerLaser() {
        return "LancerLaser";
    }

    public String reculer() {
        return "Reculer";
    }

    public String rienFaire() {
        return "RienFaire";
    }

    public String Tourner() {
        return "Tourner";
    }

    public String activerBlouclier() {
        return "ActiverBouclier";
    }

    public String desactiverBouclier() {
        return "DesactiverBouclier";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String parcourir() {
        return getNom();
    }

    @Override
    public String toString() {
        return "Feuille";
    }

}
