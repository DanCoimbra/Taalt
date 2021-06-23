package gameclient;

import gameserver.Output;

import javax.swing.*;
import java.awt.*;

/**
 *  Visualização dos dados do jogo atual: número total de jogadores,
 *  número da rodada atual, e nome do jogador da vez. Componente visual
 *  não-interativo de GUIGameScreen.
 */
public class GUIGameStatus extends JPanel {
    int numPlayers;
    int rounds;
    String name;
    JLabel numPlayersLabel;
    JLabel roundsLabel;
    JLabel currentPlayerLabel;

    GUIGameStatus(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(200, 255, 200));
        this.setLayout(new GridLayout(1, 3));

        this.numPlayersLabel = new JLabel();
        this.numPlayersLabel.setHorizontalAlignment(JLabel.CENTER);
        this.numPlayersLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(this.numPlayersLabel);

        this.roundsLabel = new JLabel();
        this.roundsLabel.setHorizontalAlignment(JLabel.CENTER);
        this.roundsLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(this.roundsLabel);

        this.currentPlayerLabel = new JLabel();
        this.currentPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
        this.currentPlayerLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(this.currentPlayerLabel);
    }

    /** Atualiza os mostradores com novas informações. */
    public void update(Output output) {
        this.numPlayers = output.getNumPlayers();
        this.rounds = output.getRounds();
        this.name = output.getCurrentName();
        this.numPlayersLabel.setText("Players: " + this.numPlayers);
        this.roundsLabel.setText("Rounds: " + this.rounds);
        this.currentPlayerLabel.setText(this.name + "'s turn");
    }
}
