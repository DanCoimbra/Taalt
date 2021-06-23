package gameclient;

import javax.swing.*;
import java.awt.*;

// TODO: Implementar funcionalidades.
/**
 *  Menu interativo inserido em GUIGameScreen.
 */
public class GUIGameMenu extends JPanel {
    GUIGameMenu(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(255, 255, 200));
        this.setLayout(new GridLayout(1, 2));

        JLabel back = new JLabel("Back to main menu");
        back.setHorizontalAlignment(JLabel.CENTER);
        back.setVerticalAlignment(JLabel.CENTER);
        this.add(back);

        JLabel gravity = new JLabel("Turn on gravity mode");
        gravity.setHorizontalAlignment(JLabel.CENTER);
        gravity.setVerticalAlignment(JLabel.CENTER);
        this.add(gravity);
    }
}
