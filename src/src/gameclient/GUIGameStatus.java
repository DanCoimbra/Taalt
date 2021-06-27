package gameclient;

import gameserver.GameStatus;

import javax.swing.*;
import java.awt.*;

/**
 *  Visualização dos dados do jogo atual: número total de jogadores,
 *  número da rodada atual, e nome do jogador da vez. Componente visual
 *  não-interativo de GUIGameScreen.
 */
public class GUIGameStatus extends JPanel {
    private int numPlayers;
    private int rounds;
    private String name;
    private JLabel numPlayersLabel;
    private JLabel roundsLabel;
    private JLabel currentPlayerLabel;

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

    /* Atualiza os mostradores com novas informações. */
    public void update(GameStatus gameStatus) {
        this.numPlayers = gameStatus.getNumberOfPlayers();
        this.rounds = gameStatus.getCurrentRound();
        this.name = gameStatus.getCurrentPlayer().getName();
        this.numPlayersLabel.setText("Players: " + this.numPlayers);
        this.roundsLabel.setText("Rounds: " + this.rounds);
        this.currentPlayerLabel.setText(this.name + "'s turn");
    }
}
