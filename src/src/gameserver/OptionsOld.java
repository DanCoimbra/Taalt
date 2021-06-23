package gameserver;

import java.util.ArrayList;

// TODO: Ideia: Para melhor encapsular Options, criar uma variável estática informando quais as opções disponíveis.
// TODO (cont.): Assim, o cliente não precisa presumir as opções de jogo. Ao invés disso, cria dinamicamente campos de input.
/**
 *  Objetos Options contêm especificações sobre uma partida de Taalt: o número de jogadores,
 *  o nome dos jogadores, as dimensões do tabuleiro (m x n), o tamanho da sequência de peças
 *  requerida para vitória (k), e se o modo gravidade está ativado.
 */
public class OptionsOld {
    int numPlayers;
    ArrayList<String> names;
    int m;
    int n;
    int k;
    boolean gravityMode;

    public OptionsOld() {
        this.numPlayers = 2;
        this.names = new ArrayList<String>();
        this.m = 3;
        this.n = 3;
        this.k = 3;
        this.gravityMode = false;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void addPlayerName(String name) {
        this.names.add(name);
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setGravityMode(boolean gravityMode) {
        this.gravityMode = gravityMode;
    }

    public String getPlayerName(int index) {
        return this.names.get(index);
    }

    public int getNumPlayers() {
        return this.numPlayers;
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

    public boolean getGravityMode() {
        return this.gravityMode;
    }
}
