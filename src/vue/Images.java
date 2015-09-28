/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.ImageIcon;
import modele.Case;
import modele.Direction;
import modele.Missile;
import modele.Robot;

/**
 *
 * @author sergeokov
 */
public class Images {

    ImageIcon backgroung = new ImageIcon(System.getProperty("user.dir") + "/src/images/background.jpg");
    ImageIcon vide = new ImageIcon(System.getProperty("user.dir") + "/src/images/vide.jpg");

    ImageIcon robotEst = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotEst.jpg");
    ImageIcon robotOuest = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotOuest.jpg");
    ImageIcon robotSud = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotSud.jpg");
    ImageIcon robotNord = new ImageIcon(System.getProperty("user.dir") + "/src/images/robotNord.jpg");

    ImageIcon missileEst = new ImageIcon(System.getProperty("user.dir") + "/src/images/missile_est.jpg");
    ImageIcon missileOuest = new ImageIcon(System.getProperty("user.dir") + "/src/images/missile_ouest.jpg");
    ImageIcon missileSud = new ImageIcon(System.getProperty("user.dir") + "/src/images/missile_sud.jpg");
    ImageIcon missileNord = new ImageIcon(System.getProperty("user.dir") + "/src/images/missile_nord.jpg");

    public ImageIcon directionRobot(Case caz) {
        Robot robot = (Robot) caz;
        ImageIcon imageIcon = backgroung;
        switch (robot.getDirection()) {
            case Est:
                imageIcon = robotEst;
                break;
            case Ouest:
                imageIcon = robotOuest;
                break;
            case Nord:
                imageIcon = robotNord;
                break;
            case Sud:
                imageIcon = robotSud;
                break;
            default:
                // rien
                break;
        }
        return imageIcon;
    }

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
                directionMissile(caz);  
                break;
            default:
                break;
        }
        return imageIcon;
    }

}
