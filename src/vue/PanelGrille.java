package vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelGrille extends JPanel {

    public PanelGrille() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2d = (Graphics2D) g;
        ImageIcon a = new ImageIcon(System.getProperty("user.dir") + "/src/images/background.jpg");
        // ImageIcon a = new ImageIcon(getClass().getResource("/images/background.jpg"));
        Image img = a.getImage();
        g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);

    }

}
