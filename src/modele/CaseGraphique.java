package modele;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CaseGraphique extends JPanel {
    /*
     Cette classe dans le futur va representer sur la grille
     un robot, une case vide, une missile, un laser et des effets
     */

    private Image img;
    Case caze;

    public CaseGraphique(Case caze, Image image) {
        this.caze = caze;
        this.img = image;
    }

    public CaseGraphique(Image image) {
        this.img = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (img != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
        repaint();
    }

    public void setImage(String path) throws IOException {
        try {
            this.img = ImageIO.read(new File(path));
            repaint();
        } catch (IOException e) {
            throw new IOException(path + " introuvable", e);
        }
    }

    public Case getCaze() {
        return caze;
    }

    public void setCaze(Case caze) {
        this.caze = caze;
    }

}
