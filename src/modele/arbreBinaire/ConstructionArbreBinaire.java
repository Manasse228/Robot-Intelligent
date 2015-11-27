
package modele.arbreBinaire;

import controleur.Partie;
import modele.Robot;


public class ConstructionArbreBinaire {

    public Partie partie;
    public Robot robot;

    public Arbre arbre;

    public Noeud noeudVérification_Sante;
    public Noeud noeudCoincer;
    public Noeud noeudVoirDevant;
    public Noeud noeudUneCase;
    public Noeud noeudMissileDevant;

    public Feuille feuille;

    public ConstructionArbreBinaire() {
    }

    public ConstructionArbreBinaire(Partie partie, Robot robot) {
        this.partie = partie;
        this.robot = robot;

        noeudVérification_Sante = new Noeud(new ConditionVerificationSante(), robot, partie);
        noeudCoincer = new Noeud(new ConditionCoincer(), robot, partie);
        noeudVoirDevant = new Noeud(new ConditionVoirDevant(), robot, partie);
        noeudUneCase = new Noeud(new ConditionUneCase(), robot, partie);
        noeudMissileDevant = new Noeud(new ConditionMissileDevant(), robot, partie);
        
        feuille = new Feuille();
        arbre = new Arbre(robot, partie);

        noeudVérification_Sante.createNoeud(new Feuille(feuille.rienFaire()), noeudCoincer);
        noeudCoincer.createNoeud(new Feuille(feuille.Tourner()), noeudVoirDevant);
        noeudVoirDevant.createNoeud(noeudUneCase, noeudMissileDevant);
        noeudUneCase.createNoeud(new Feuille(feuille.lancerLaser()), new Feuille(feuille.lancerMissile()));
        noeudMissileDevant.createNoeud(new Feuille(feuille.avancer()), new Feuille(feuille.aleatoire()));

        arbre.add(noeudVérification_Sante);
    }

    public String action() {
        return arbre.parcourir();
    }

    public int verification(Condition condition, Robot robot, Partie partie) {
        int result = 0;
        if (condition.check(partie, robot) == true) {
            result = 1;
        }
        return result;
    }

}
