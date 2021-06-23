package gameserver;

public class Output {
    int numPlayers;
    int rounds;
    String currentName;
    boolean gravityMode;
    GameCondition gameCondition;

    public Output(int numPlayers, int rounds, String currentName, boolean gravityMode, GameCondition gameCondition) {
        this.numPlayers = numPlayers;
        this.rounds = rounds;
        this.currentName = currentName;
        this.gravityMode = gravityMode;
        this.gameCondition = gameCondition;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public int getRounds() {
        return this.rounds;
    }

    public String getCurrentName() {
        return this.currentName;
    }

    public boolean getGravityMode() {
        return this.gravityMode;
    }

    public GameCondition getGameCondition() {
        return this.gameCondition;
    }
}
