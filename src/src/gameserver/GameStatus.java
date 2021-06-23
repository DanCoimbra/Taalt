package gameserver;

import java.util.ArrayList;

public class GameStatus {
    int totalPlayers;
    PlayerID currentPlayer;
    int rounds;
    ArrayList<Player> playerList;
    boolean gravityMode;
    GameCondition condition;

    public GameStatus(Options options) {
        this.totalPlayers = options.getNumPlayers();
        this.currentPlayer = PlayerID.ONE;
        this.rounds = 1;
        this.playerList = new ArrayList<Player>();
        this.gravityMode = options.getGravityMode();
        this.condition = GameCondition.START;

        for (int playerID = 1; playerID <= totalPlayers; playerID++) {
            assert playerID <= 4;
            String playerName = options.getPlayerName(playerID - 1);
            Player newPlayer = new Player(PlayerID.values()[playerID], playerName);
            this.playerList.add(newPlayer);
        }
    }

    public void nextRound() {
        this.rounds += 1;
        if (this.currentPlayer == PlayerID.values()[this.totalPlayers]) {
            this.currentPlayer = PlayerID.ONE;
        } else {
            this.currentPlayer = this.currentPlayer.getNext();
        }
    }

    public void endGame() {
        this.condition = GameCondition.END;
    }

    public Output getOutput() {
        int players = this.totalPlayers;
        int round = this.rounds;
        String currentName = this.getCurrentPlayer().getName();
        boolean gravity = this.gravityMode;
        GameCondition condition = this.condition;
        return new Output(players, round, currentName, gravity, condition);
    }

    public Player getCurrentPlayer() {
        return this.playerList.get(this.currentPlayer.ordinal() - 1);
    }

    public void setGravityMode(boolean gravityMode) {
        this.gravityMode = gravityMode;
    }

    public boolean getGravityMode() {
        return this.gravityMode;
    }
}
