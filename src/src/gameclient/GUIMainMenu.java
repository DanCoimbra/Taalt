package gameclient;

import gameserver.Options;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *  Interface gráfica para inserir as opções de jogo e inicializá-lo.
 *
 *  Contém quatro componentes que herdam de JPanel, cada um responsável por coletar um tipo de opção de jogo.
 *  Os componentes são definidos como inner classes. Por implementarem IOptionsProducer, armazena referência
 *  a implementadores de IOptionsReceiver e os envia objetos Option quando "fireOptions()" é chamada.
 */

public class GUIMainMenu extends JPanel implements IOptionsProducer {
    ArrayList<IOptionsReceiver> optionsReceiverList;
    Options options;
    JButton start;

    public GUIMainMenu() {
        super();

        this.optionsReceiverList = new ArrayList<IOptionsReceiver>();
        this.options = new Options();
        int inputCount = options.getInputCount();
        this.setLayout(new GridLayout(inputCount + 2, 1, 0, 10));
        JLabel titleScreen = new JLabel(new ImageIcon("assets/titlescreen200x60.png"));
        this.add(titleScreen);

        for (int input = 0; input < inputCount; input++) {
            this.add(new InputPanel(this, this.options.getInputID(input)));
        }

        this.add(new StartButton(this));
    }

    /** Implementa métodos de IOptionsProducer. */
    @Override
    public void addOptionsReceiver(IOptionsReceiver optionsReceiver) {
        this.optionsReceiverList.add(optionsReceiver);
    }

    @Override
    public void fireOptions(Options options) {
        for (IOptionsReceiver optionsReceiver : this.optionsReceiverList) {
            optionsReceiver.receiveOptions(options);
        }
    }

    /**
     *  Componente Swing contendo um JComboBox para escolher o número de jogadores
     *  da partida, dentre alternativas pré-determinadas. Toda vez que o usuário
     *  selecionar um número de jogadores, "JComboBox.actionPerformed()" alterará
     *  o objeto Options armazenado na variável de instância "GUIMainMenu.options".
     */
    class InputPanel extends JPanel implements ActionListener {
        GUIMainMenu outerPanel;
        JTextArea inputTextArea;
        String inputID;

        InputPanel(GUIMainMenu outerPanel, String inputID) {
            super();
            this.outerPanel = outerPanel;
            this.inputID = inputID;
            this.setLayout(new GridLayout(1, 3, 10, 0));

            String displayText = this.outerPanel.options.getDisplayText(inputID);
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
     *  o método sobrescrito "JButton.actionPerformed()" chamará o método "GUIMainMenu.fireOptions()".
     */
    private class StartButton extends JPanel implements ActionListener {
        GUIMainMenu outer;
        JButton button;

        StartButton(GUIMainMenu outer) {
            super();
            this.outer = outer;
            this.button = new JButton("Start");
            button.addActionListener(this);
            this.add(button);
        }

        /** Implementa método de JButton. */
        @Override
        public void actionPerformed(ActionEvent e) {
            this.outer.fireOptions(options);
        }
    }

}
