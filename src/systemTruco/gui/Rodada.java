package systemTruco.gui;

import javax.swing.*;
import java.awt.*;

public class Rodada extends JButton {
    public Rodada(String string) {
        super(string);
        this.setPreferredSize(new Dimension(214, 53));
        //ImageIcon ic = new ImageIcon();
        // this.setIcon(ic);
        //this.setBackground(Color.white);
        this.setOpaque(true);
        this.repaint();
        this.revalidate();
    }
}
