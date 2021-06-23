package gameserver;

/**
 *  Objetos Output contêm informações do estado atual do jogo: o número total de jogadores,
 *  o número da rodada atual, o nome do jogador atual, se o modo gravidade está ativado, e
 *  se o jogo está em andamento (GameCondition.START) ou terminado (GameCondition.END).
 */
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
