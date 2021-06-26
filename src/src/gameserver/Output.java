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
    GravityMode gravityMode;
    GameCondition gameCondition;
    int m;
    int n;
    int k;

    public Output(int numPlayers, int rounds, String currentName, GravityMode gravityMode, GameCondition gameCondition, int m, int n, int k) {
        this.numPlayers = numPlayers;
        this.rounds = rounds;
        this.currentName = currentName;
        this.gravityMode = gravityMode;
        this.gameCondition = gameCondition;
        this.m = m;
        this.n = n;
        this.k = k;
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

    public GravityMode getGravityMode() {
        return this.gravityMode;
    }

    public GameCondition getGameCondition() {
        return this.gameCondition;
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
