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

public class GUIMainMenuOld extends JPanel implements IOptionsProducer {
    ArrayList<IOptionsReceiver> optionsReceiverList;
    Options options;
    JButton start;

    public GUIMainMenuOld() {
        super();
        this.optionsReceiverList = new ArrayList<IOptionsReceiver>();
        this.options = new Options();
        this.setLayout(new GridLayout(5, 1, 0, 50));
        JLabel titleScreen = new JLabel(new ImageIcon("assets/titlescreen200x60.png"));
        this.add(titleScreen);
        this.add(new InputPlayer(this));
        this.add(new InputMNK(this));
        this.add(new InputGravity(this));
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
    class InputPlayer extends JPanel implements ActionListener {
        GUIMainMenuOld outer;

        InputPlayer(GUIMainMenuOld outer) {
            super();
            this.outer = outer;
            this.setLayout(new GridLayout(1, 2));

            JLabel label= new JLabel("Choose the amount of players: ");
            this.add(label);

            JComboBox<String> box = new JComboBox<>(new String[]{"2", "3", "4"});
            box.addActionListener(this);
            this.add(box);
        }

        // TODO: Se livrar desse problema de unchecked casting.
        /** Implementa método de JComboBox. */
        @Override
        public void actionPerformed(ActionEvent e) {
            @SuppressWarnings("unchecked")
            JComboBox<String> box = (JComboBox<String>) e.getSource();
            String selectedItem = (String) box.getSelectedItem();
            assert selectedItem != null;
            int numPlayers = Integer.parseInt(selectedItem);
            //options.setNumPlayers(numPlayers);
        }
    }

    /**
     *  Componente Swing contendo três JSliders para escolher os parâmetros m, n, k do jogo.
     *  Toda vez que o usuário altera a posição do cabeçote de um JSlider, "JSlider.stateChanged()"
     *  alterará o objeto Options armazenado na variável de instância "GUIMainMenu.options".
     */
    private class InputMNK extends JPanel implements ChangeListener {
        GUIMainMenuOld outer;
        JLabel label;
        JSlider mSlider, nSlider, kSlider;

        InputMNK(GUIMainMenuOld outer) {
            super();
            this.outer = outer;
            this.setLayout(new GridLayout(4, 1));

            this.mSlider = new JSlider(3, 10, 3);
            this.nSlider = new JSlider(3, 10, 3);
            this.kSlider = new JSlider(3, 10, 3);

            ArrayList<JSlider> sliderList = new ArrayList<JSlider>();
            sliderList.add(this.mSlider);
            sliderList.add(this.nSlider);
            sliderList.add(this.kSlider);
            for (JSlider slider: sliderList) {
                slider.setPaintTicks(true);
                slider.setMinorTickSpacing(10);
                slider.setPaintTrack(true);
                slider.setPaintLabels(true);
                slider.addChangeListener(this);
                this.add(slider);
            }

            this.label = new JLabel("Values for m, n, k: " + mSlider.getValue() + ", " + nSlider.getValue() + ", " + kSlider.getValue());
            this.add(label);
        }

        /** Implementa método de JSlider. */
        @Override
        public void stateChanged(ChangeEvent e) {
            this.label.setText("Values for m, n, k: " + mSlider.getValue() + ", " + nSlider.getValue() + ", " + kSlider.getValue());
            Options outerOptions = this.outer.options;
            /**
             if (e.getSource() == mSlider) {
             outerOptions.setM(mSlider.getValue());
             } else if (e.getSource() == nSlider) {
             outerOptions.setN(nSlider.getValue());
             } else {
             outerOptions.setK(kSlider.getValue());
             }
             */
        }
    }

    /**
     *  Componente Swing contendo um JTtogleButton para ativar ou desativar o modo gravidade.
     *  Toda vez que o usuário altera o toggle, "JToggleButton.itemStateChanged()" alterará
     *  o objeto Options armazenado na variável de instância "GUIMainMenu.options".
     */
    private class InputGravity extends JPanel implements ItemListener {
        GUIMainMenuOld outer;

        InputGravity(GUIMainMenuOld outer) {
            super();
            this.outer = outer;
            this.setLayout(new GridLayout(1, 2));
            JLabel label = new JLabel("Gravity mode: ");
            JToggleButton toggle = new JToggleButton();
            toggle.addItemListener(this);
            this.add(label);
            this.add(toggle);
        }

        /** Implementa método de JSlider. */
        @Override
        public void itemStateChanged(ItemEvent e) {
            boolean gravityMode = (e.getStateChange() == ItemEvent.SELECTED);
            //options.setGravityMode(gravityMode);
        }
    }

    /**
     *  Componente Swing contendo um JButton para iniciar o jogo. Quando o usuário clica o botão,
     *  o método sobrescrito "JButton.actionPerformed()" chamará o método "GUIMainMenu.fireOptions()".
     */
    private class StartButton extends JPanel implements ActionListener {
        GUIMainMenuOld outer;
        JButton button;

        StartButton(GUIMainMenuOld outer) {
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
