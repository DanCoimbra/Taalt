package gameserver;

import java.util.ArrayList;

/**
 *  Objetos Options contêm especificações sobre uma partida de Taalt: o número de jogadores,
 *  o nome dos jogadores, as dimensões do tabuleiro (m x n), o tamanho da sequência de peças
 *  requerida para vitória (k), e se o modo gravidade está ativado.
 */
public class Options {
    String[] inputs;
    int inputCount;
    int numPlayers;
    ArrayList<String> names;
    int m;
    int n;
    int k;
    boolean gravityMode;

    public Options() {
        this.inputs = new String[]{"numplayers", "names", "m", "n", "k", "gravity"};
        this.numPlayers = 2;
        this.names = new ArrayList<String>();
        this.m = 3;
        this.n = 3;
        this.k = 3;
        this.gravityMode = false;
    }

    public int getInputCount() {
        return this.inputs.length;
    }

    public String getInputID(int index) {
        return this.inputs[index];
    }

    public String getDisplayText(String inputID) {
        return switch (inputID) {
            case "numplayers" -> "Number of players";
            case "names" -> "Player names (comma-separated)";
            case "m" -> "Rows (m)";
            case "n" -> "Columns (n)";
            case "k" -> "Sequence size (k)";
            case "gravity" -> "Gravity mode (true/false)";
            default -> "ERROR";
        };
    }

    public void setOption(String inputID, String inputText) {
        switch (inputID) {
            case "numplayers" -> this.setNumPlayers(inputText);
            case "names" -> this.setPlayerNames(inputText);
            case "m" -> this.setM(inputText);
            case "n" -> this.setN(inputText);
            case "k" -> this.setK(inputText);
            case "gravity" -> this.setGravityMode(inputText);
        };
    }

    public void setNumPlayers(String inputText) {
        int numPlayers = Integer.parseInt(inputText);
        if (2 <= numPlayers && numPlayers <= 4) {
            this.numPlayers = numPlayers;
        }
    }

    public void setPlayerNames(String inputText) {
        String[] nameList = inputText.split(",");
        for (String name : nameList) {
            this.addPlayerName(name);
        }
    }

    public void addPlayerName(String name) {
        this.names.add(name);
    }

    public void setM(String inputText) {
        int m = Integer.parseInt(inputText);
        if (3 <= m && m <= 10) {
            this.m = m;
        }
    }

    public void setN(String inputText) {
        int n = Integer.parseInt(inputText);
        if (3 <= n && n <= 10) {
            this.n = n;
        }
    }

    public void setK(String inputText) {
        int k = Integer.parseInt(inputText);
        if (3 <= k && k <= 10) {
            this.k = k;
        }
    }

    public void setGravityMode(String inputText) {
        inputText = inputText.toLowerCase();
        if (inputText.equals("t") || inputText.equals("true")) {
            this.gravityMode = true;
        } else if (inputText.equals("f") || inputText.equals("false")) {
            this.gravityMode = false;
        }
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public String getPlayerName(int index) {
        return this.names.get(index);
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
