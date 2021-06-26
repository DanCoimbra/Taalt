package gameclient;

import gameserver.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  Interface gráfica para inserir as opções de jogo e inicializá-lo.
 */
public class GUIMainMenu extends JScrollPane implements IMainMenu {
    private final GUIController controller;
    private final Options options;      // Uma vez criado o objeto, ele não muda, só os atributos dele.

    private final String titleScreenLogo = "assets/titlescreen200x60.png";

    public GUIMainMenu(GUIController controller) {
        super();
        this.controller = controller;
        this.options = new Options();

        /* Cria a interface do menu que ficará dentro do JScrollPane */
        JPanel innerScreen = new JPanel();
        JLabel titleScreen = new JLabel(new ImageIcon(titleScreenLogo));
        innerScreen.add(titleScreen);

        int inputCount = options.getNumberOfOptions();
        innerScreen.setLayout(new GridLayout(inputCount + 2, 1, 0, 10));

        for (int i = 0; i < inputCount; i++) {
            innerScreen.add(new InputPanel(this.options.getInputID(i)));
        }

        innerScreen.add(new StartButton());

        /* Adiciona tal interface ao JScrollPane */
        this.getViewport().setView(innerScreen);
    }

    private void createNewGame() {
        IGameBuilder gameBuilder = new GameBuilder();
        IGame gameServer = gameBuilder.buildGame(this.options);
        this.controller.setupGameScreen(gameServer);
        this.controller.showGameScreen();
    }

    /**
     *  Painel onde serão inseridas informações dos parâmetros do jogo, que serão aplicadas ao clicar no botão
     *  correspondente a cada campo.
     */
    class InputPanel extends JPanel implements ActionListener {
        JTextArea inputTextArea;
        String inputID;

        InputPanel(String inputID) {
            super();
            this.inputID = inputID;
            this.setLayout(new GridLayout(1, 3, 10, 0));

            String displayText = GUIMainMenu.this.options.getDisplayText(inputID);
            JLabel label= new JLabel(displayText + ": ");
            this.add(label);

            this.inputTextArea = new JTextArea();
            this.inputTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
            this.inputTextArea.setBackground(new Color(220, 220, 200));
            this.add(inputTextArea);

            JButton applyButton = new JButton("Apply");
            applyButton.addActionListener(this);
            this.add(applyButton);
        }

        /** Implementa método de JButton. */
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputTextArea.getText();
            options.setOption(this.inputID, inputText);
        }
    }

    /**
     *  Componente Swing contendo um JButton para iniciar o jogo. Quando o usuário clica o botão,
     *  o método sobrescrito "JButton.actionPerformed()" chamará o método "GUIMainMenu.start()".
     */
    private class StartButton extends JPanel implements ActionListener {
        StartButton() {
            super();
            JButton button = new JButton("Start");
            button.addActionListener(this);
            this.add(button);
        }

        /** Implementa método de JButton. */
        @Override
        public void actionPerformed(ActionEvent e) {
            GUIMainMenu.this.createNewGame();
        }
    }
}
