/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import modele.Case;
import modele.Grille;
import modele.Position;
import modele.Robot;

/**
 *
 * @author sergeokov
 */
public class Vue extends JFrame implements Observer {

    Grille grille;
    ArrayList<CaseGraphique> listCaseGraphique = new ArrayList<CaseGraphique>();
    ArrayList<CaseGraphique> triListCaseGraphique = new ArrayList<CaseGraphique>();
    Images images = new Images();
    JButton jb;

    public Vue(int x, int y, int nbreRobot) {
        
        this.setTitle("Robot");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6));
        this.setMinimumSize(new Dimension((int) (screenSize.getHeight() * 0.8), (int) (screenSize.getHeight() * 0.6)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
         grille = new Grille(x, y, nbreRobot);
        menu();
        setLayout(new GridLayout(y, x));
        afficherCase(x, y);

    }
    public void deplacementRobot(){
        
    }

    public void afficherCase(int x, int y){
        
        for(Case caz:grille.getListCase()){
            listCaseGraphique.add(new CaseGraphique(caz, images.renvoiImages(caz).getImage()));
        }
//        int cpt=0;
//        while(cpt < y){
//            
//            for(int i=0;i<x;i++){
//                
//                for(CaseGraphique caseGraphique:listCaseGraphique){
//                    
//                if(Position.egalite(caseGraphique.getCaze().position(), new Position(i,cpt)) == true){
//                       triListCaseGraphique.add(caseGraphique);
//                    }
//            }
//            }
//            cpt++;
//        }
        for(int i=0;i<x;i++){
                for(int j=0;j<y;j++){
                    for(CaseGraphique caseGraphique:listCaseGraphique){
                         
                    if(Position.egalite(caseGraphique.getCaze().position(), new Position(i,j)) == true){
                       triListCaseGraphique.add(caseGraphique);
                    }
                    }
                    
                }
        }
        
        for(CaseGraphique caseGraphique:triListCaseGraphique){
           this.getContentPane().add(caseGraphique);
//           String nom = caseGraphique.caze.position().toString();
//           jb = new JButton(nom);
//           this.getContentPane().add(jb);
        }
        
        for(Case caz:grille.getListCaseRobot()){
            Robot robot = (Robot) caz;
//            System.out.println("Robot "+robot.position()+" direction "+robot.getDirection());
        }
        
        for(CaseGraphique caseGraphique:triListCaseGraphique){
            System.out.println("Position "+caseGraphique.getCaze().position()+" et je suis "+caseGraphique.getCaze().toString());
        }
        
    }

    public void menu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu jMenu = new JMenu("Menu");
        JMenu jMenu1 = new JMenu("Menu1");
        JMenu jMenu2 = new JMenu("Menu2");

        JMenuItem jMenuItem = new JMenuItem("cool");
        JMenuItem jMenuItem1 = new JMenuItem("cool");
        JMenuItem jMenuItem2 = new JMenuItem("cool");

        jMenu.add(jMenuItem);
        jMenu.add(jMenuItem1);

        menuBar.add(jMenu);
        menuBar.add(jMenu1);
        menuBar.add(jMenu2);
        this.setJMenuBar(menuBar);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
