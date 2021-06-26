package gameclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  Menu interativo inserido em GUIGameScreen.
 */
public class GUIGameMenu extends JPanel implements ActionListener {
    GUIGameScreen parentScreen;
    JButton backButton;
    JButton gravityButton;

    GUIGameMenu(int width, int height, GUIGameScreen parentScreen) {
        this.parentScreen = parentScreen;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(255, 255, 200));
        this.setLayout(new GridLayout(1, 2));

        JButton backButton = new JButton("Back to main menu");
        backButton.addActionListener(this);
        this.add(backButton);

        JButton gravityButton = new JButton("Turn on gravity mode");
        gravityButton.addActionListener(this);
        this.add(gravityButton);
        // TODO: Implementar funcionalidade.
        gravityButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.parentScreen.endGame();
        }
    }
}
