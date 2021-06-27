package gameclient;

import gameserver.GravityMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  Barra de ferramentas inserida em GUIGameScreen. Permite encerrar
 *  a partida atual e modificar o modo gravidade.
 */
public class GUIGameToolbar extends JPanel implements ActionListener {
    private GUIGameScreen parentScreen;

    GUIGameToolbar(int width, int height, GUIGameScreen parentScreen) {
        this.parentScreen = parentScreen;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(255, 255, 200));
        this.setLayout(new GridLayout(1, 2));

        // Adiciona o botão de volta ao menu principal.
        JButton backButton = new JButton("Back to main menu");
        backButton.addActionListener(this);
        this.add(backButton);

        // Adiciona painel com botões para configurar o modo gravidade.
        this.add(new GravityToolbar());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.parentScreen.endGame();
    }

    class GravityToolbar extends JPanel implements ActionListener {
        JButton upButton;
        JButton downButton;
        JButton leftButton;
        JButton rightButton;
        JButton offButton;

        public GravityToolbar() {
            this.setLayout(new BorderLayout());
            this.upButton = new JButton("^");
            this.downButton = new JButton("v");
            this.leftButton = new JButton("<");
            this.rightButton = new JButton(">");
            this.offButton = new JButton("OFF");
            upButton.addActionListener(this);
            downButton.addActionListener(this);
            leftButton.addActionListener(this);
            rightButton.addActionListener(this);
            offButton.addActionListener(this);
            this.add(upButton, BorderLayout.NORTH);
            this.add(downButton, BorderLayout.SOUTH);
            this.add(leftButton, BorderLayout.WEST);
            this.add(rightButton, BorderLayout.EAST);
            this.add(offButton, BorderLayout.CENTER);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (this.upButton.equals(source)) {
                parentScreen.setGravityMode(GravityMode.UP);
            } else if (this.downButton.equals(source)) {
                parentScreen.setGravityMode(GravityMode.DOWN);
            } else if (this.leftButton.equals(source)) {
                parentScreen.setGravityMode(GravityMode.LEFT);
            } else if (this.rightButton.equals(source)) {
                parentScreen.setGravityMode(GravityMode.RIGHT);
            } else if (this.offButton.equals(source)) {
                parentScreen.setGravityMode(GravityMode.DISABLED);
            }
        }
    }
}
