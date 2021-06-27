package gameclient;

import gameserver.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  Interface gráfica para inserir as opções de jogo e inicializá-lo. As caixas de input
 *  para inserir tais opções são geradas dinamicamente de acordo com informações do servidor.
 *  O conjunto das caixas é inserido em um JScrollPane, pois sua quantidade e tamanho
 *  podem exceder o tamanho-padrão da janela.
 */
public class GUIMainMenu extends JScrollPane implements IMainMenu, ActionListener {
    private final GUIController controller;
    private final Options options;      // Uma vez criado o objeto, ele não muda, só os atributos dele.
    private InputPanel[] inputPanelList;

    private final String titleScreenLogo = "assets/titlescreen200x60.png";

    public GUIMainMenu(GUIController controller) {
        super();
        this.controller = controller;
        this.options = new Options();

        /* Cria a interface innerScreen do menu, que ficará dentro do JScrollPane. */
        JPanel innerScreen = new JPanel();
        JLabel titleScreen = new JLabel(new ImageIcon(titleScreenLogo));

        /* Adiciona ao painel do menu logo principal */
        innerScreen.add(titleScreen);

        /* Adiciona ao painel do menu campos de opções de acordo com informações de Options. */
        int inputCount = options.getNumberOfOptions();
        innerScreen.setLayout(new GridLayout(inputCount + 2, 1, 0, 10));
        this.inputPanelList = new InputPanel[inputCount];
        for (int i = 0; i < inputCount; i++) {
            InputPanel inputPanel = new InputPanel(this.options.getInputID(i));
            innerScreen.add(inputPanel);
            this.inputPanelList[i] = inputPanel;
        }

        /* Adiciona ao painel do menu o botão de inciar o jogo. */
        JButton startButton = new JButton("Start");
        startButton.addActionListener(this);
        innerScreen.add(startButton);

        /* Adiciona ao JScrollPane o painel do menu. */
        this.getViewport().setView(innerScreen);
    }

    /*
     * Sobrescreve método de JButton. Captura os inputs do usuário, chama o método genérico
     * de configuração de opções Options.setOption(), e chama o método que inicia o jogo.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (InputPanel inputPanel : this.inputPanelList) {
            String inputID   = inputPanel.inputID;
            String inputText = inputPanel.inputTextArea.getText();
            if (inputText.length() > 0) {
                this.options.setOption(inputID, inputText);
            }
        }
        this.createNewGame();
    }

    /* Inicia o servidor IGame e chama GUIController para configurar e exibir a tela de jogo. */
    private void createNewGame() {
        IGameBuilder gameBuilder = new GameBuilder();
        IGame gameServer = gameBuilder.buildGame(this.options);
        this.controller.setupGameScreen(gameServer);
        this.controller.showGameScreen();
    }

    /** Painel onde serão inseridas informações dos parâmetros do jogo. */
    class InputPanel extends JPanel {
        String inputID;
        JTextArea inputTextArea;

        InputPanel(String inputID) {
            super();
            this.setLayout(new GridLayout(1, 2, 10, 0));
            this.inputID = inputID;
            this.inputTextArea = new JTextArea();

            String displayText = GUIMainMenu.this.options.getDisplayText(inputID);
            JLabel label = new JLabel(displayText + ": ");
            label.setHorizontalAlignment(JLabel.CENTER);
            this.add(label);

            this.inputTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
            this.inputTextArea.setBackground(new Color(220, 220, 200));
            this.add(inputTextArea);
        }
    }
}
