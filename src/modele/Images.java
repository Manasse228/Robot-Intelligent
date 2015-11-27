package modele;

import javax.swing.ImageIcon;

public class Images {
    /*
     Initialisation des liens vers les images 
     */

    ImageIcon backgroung = new ImageIcon(System.getProperty("user.dir") + "/src/images/ban.jpg");
    ImageIcon sanbouclierLaser = new ImageIcon(System.getProperty("user.dir") + "/src/images/sans-bouclier-laser.jpg");
    ImageIcon explosion = new ImageIcon(System.getProperty("user.dir") + "/src/images/explosion.jpg");
    ImageIcon sanbouclierMissile = new ImageIcon(System.getProperty("user.dir") + "/src/images/sans-bouclier.jpg");
    ImageIcon vide = new ImageIcon(System.getProperty("user.dir") + "/src/images/vide.jpg");

    ImageIcon robotEst = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotEst.jpg");
    ImageIcon robotOuest = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotOuest.jpg");
    ImageIcon robotSud = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotSud.jpg");
    ImageIcon robotNord = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotNord.jpg");

    ImageIcon robotEstSansBouclier = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotEstSansBouclier.jpg");
    ImageIcon robotOuestSansBouclier = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotOuestSansBouclier.jpg");
    ImageIcon robotSudSansBouclier = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotSudSansBouclier.jpg");
    ImageIcon robotNordSansBouclier = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotNordSansBouclier.jpg");

    ImageIcon missileEst = new ImageIcon(System.getProperty("user.dir") + "/src/images/missile_est.jpg");
    ImageIcon missileOuest = new ImageIcon(System.getProperty("user.dir") + "/src/images/missile_ouest.jpg");
    ImageIcon missileSud = new ImageIcon(System.getProperty("user.dir") + "/src/images/missile_sud.jpg");
    ImageIcon missileNord = new ImageIcon(System.getProperty("user.dir") + "/src/images/missile_nord.jpg");

    /*
     Renvi l'image d'un robot en fonction de sa direction
     */
    public ImageIcon directionRobot(Case caz) {
        Robot robot = (Robot) caz;
        ImageIcon imageIcon = backgroung;
        switch (robot.getDirection()) {
            case Est:
                if (robot.isBouclier() == true) {
                    imageIcon = robotEst;
                } else {
                    imageIcon = robotEstSansBouclier;
                }
                break;
            case Ouest:
                if (robot.isBouclier() == true) {
                    imageIcon = robotOuest;
                } else {
                    imageIcon = robotOuestSansBouclier;
                }
                break;
            case Nord:
                if (robot.isBouclier() == true) {
                    imageIcon = robotNord;
                } else {
                    imageIcon = robotNordSansBouclier;
                }
                break;
            case Sud:
                if (robot.isBouclier() == true) {
                    imageIcon = robotSud;
                } else {
                    imageIcon = robotSudSansBouclier;
                }
                break;
            default:
                // rien
                break;
        }
        return imageIcon;
    }

    /*
     Renvoi l'image d'une missile en fonction de sa direction
     */
    public ImageIcon directionMissile(Case caz) {
        ImageIcon imageIcon = backgroung;
        Missile missile = (Missile) caz;

        switch (missile.getDirection()) {
            case Est:
                imageIcon = missileEst;

                break;
            case Ouest:
                imageIcon = missileOuest;
                break;
            case Nord:
                imageIcon = missileNord;
                break;
            case Sud:
                imageIcon = missileSud;
                break;
            default:
                // rien
                break;
        }
        return imageIcon;
    }

    /*
     Renvoi l'image d'une collision de deux missiles
     */
    public ImageIcon renvoiImagesCollisionMissile() {
        return explosion;
    }

    /*
     Renvoi l'image du fond d'écran
     */
    public ImageIcon renvoiImagesBackground() {
        return backgroung;
    }

    /*
     Renvoi l'image d'un robot tué par une missile
     */
    public ImageIcon renvoiImagesRobotTue() {
        return sanbouclierMissile;
    }

    /*
     Renvoi l'image d'un robot tué par un laser
     */
    public ImageIcon renvoiImagesRobotTueLaser() {
        return sanbouclierLaser;
    }

    /*
     Methode principale qui renvoi une image
     */
    public ImageIcon renvoiImages(Case caz) {
        ImageIcon imageIcon = backgroung;

        switch (caz.toString()) {
            case "CaseVide":
                imageIcon = vide;
                break;
            case "Robot":
                imageIcon = directionRobot(caz);
                break;
            case "Missile":
                imageIcon = directionMissile(caz);
                break;
            default:
                break;
        }
        return imageIcon;
    }

}
