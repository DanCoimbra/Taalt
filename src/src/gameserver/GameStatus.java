package gameserver;

import java.awt.*;
import java.util.ArrayList;

public class GameStatus {
    public final static int MAX_PLAYERS = 4;
    int m, n, k;
    int rounds, totalPlayers, currentPlayerID;
    ArrayList<Player> playerList;
    GravityMode gravityMode;
    GameCondition condition;

    public GameStatus(Options options) {
        this.m = options.getM();
        this.n = options.getN();
        this.k = options.getK();
        this.rounds = 1;
        this.totalPlayers = options.getNumPlayers();
        this.currentPlayerID = 1;
        this.playerList = new ArrayList<Player>();
        this.gravityMode = options.getGravityMode();
        this.condition = GameCondition.START;

        for (int ID = 1; ID <= totalPlayers; ID++) {
            assert ID <= MAX_PLAYERS;
            String playerName = options.getPlayerName(ID);
            Player newPlayer = new Player(ID, playerName);
            this.playerList.add(newPlayer);
        }
    }

    public void nextRound() {
        this.rounds += 1;
        this.currentPlayerID += 1;
        if (this.currentPlayerID > this.totalPlayers) {
            this.currentPlayerID = 1;
        }
    }

    public void endGame() {
        this.condition = GameCondition.END;
    }

    public Output getOutput() {
        int players = this.totalPlayers;
        int round = this.rounds;
        String currentName = this.getCurrentPlayer().getName();
        GravityMode gravityMode = this.gravityMode;
        GameCondition condition = this.condition;
        return new Output(players, round, currentName, gravityMode, condition, m, n, k);
    }

    public int getCurrentPlayerID() {
        return this.currentPlayerID;
    }

    public Player getCurrentPlayer() {
        int ID = this.currentPlayerID;
        int index = ID - 1;
        return this.playerList.get(index);
    }

    public void setGravityMode(GravityMode gravityMode) {
        this.gravityMode = gravityMode;
    }

    public GravityMode getGravityMode() {
        return this.gravityMode;
    }
}
