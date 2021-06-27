package gameserver;

import java.util.ArrayList;

/**
 * Objetos Output contêm informações do estado atual do jogo: o número total de jogadores,
 * o número da rodada atual, o nome do jogador atual, se o modo gravidade está ativado, e
 * se o jogo está em andamento (GameCondition.START) ou terminado (GameCondition.END).
 */
public class GameStatus {
    private final ArrayList<Player> playerList;
    private GameCondition gameCondition;
    private GravityMode gravityMode;
    private int numberOfPlayers, currentPlayerIndex, currentRound;
    private final int m, n, k;

    private final static int MAX_PLAYERS = 4;

    public GameStatus(Options options) {
        this.playerList = new ArrayList<Player>();
        this.gameCondition = GameCondition.START;
        this.gravityMode = options.getGravityMode();
        this.numberOfPlayers = options.getNumPlayers();
        this.currentPlayerIndex = 0;
        this.currentRound = 1;
        this.m = options.getM();
        this.n = options.getN();
        this.k = options.getK();

        assert numberOfPlayers < MAX_PLAYERS;

        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = options.getPlayerName(i);
            Player newPlayer = new Player(i, playerName);
            this.playerList.add(newPlayer);
        }
    }

    public Player getCurrentPlayer() {
        return this.playerList.get(currentPlayerIndex);
    }

    public GameCondition getGameCondition() {
        return this.gameCondition;
    }

    public void endGame() {
        this.gameCondition = GameCondition.END;
    }

    public boolean hasGameEnded() {
        return this.gameCondition == GameCondition.END;
    }

    public GravityMode getGravityMode() {
        return this.gravityMode;
    }

    public void setGravityMode(GravityMode gravityMode) {
        this.gravityMode = gravityMode;
    }

    public void nextRound() {
        this.currentRound += 1;
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.numberOfPlayers;
    }

    public int getCurrentRound() {
        return this.currentRound;
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public int getM() {
        return this.m;
    }

    public int getN() {
        return this.n;
    }

    public int getK() {
        return this.k;
    }
}
