package gameserver;

import java.util.ArrayList;

/**
 *  Objetos Options contêm especificações sobre uma partida de Taalt: o número de jogadores,
 *  o nome dos jogadores, as dimensões do tabuleiro (m x n), o tamanho da sequência de peças
 *  requerida para vitória (k), e se o modo gravidade está ativado.
 */
public class Options {
    String[] inputFields;
    int numPlayers;
    ArrayList<String> nameList;
    int m;
    int n;
    int k;
    GravityMode gravityMode;

    public Options() {
        this.inputFields = new String[]{"numPlayers", "nameList", "m", "n", "k", "gravityMode"};
        this.numPlayers = 2;
        this.nameList = new ArrayList<String>();
        this.m = 3;
        this.n = 3;
        this.k = 3;
        this.gravityMode = GravityMode.DISABLED;
    }

    /** Métodos que permitem ao cliente construir um menu de configurações */
    public int getNumberOfOptions() {
        return this.inputFields.length;
    }

    public String getInputID(int index) {
        return this.inputFields[index];
    }

    public String getDisplayText(String inputID) {
        return switch (inputID) {
            case "numPlayers" -> "Number of players";
            case "nameList" -> "Player names (comma-separated)";
            case "m" -> "Rows (m)";
            case "n" -> "Columns (n)";
            case "k" -> "Sequence size (k)";
            case "gravityMode" -> "Gravity mode (up/down/left/right/off)";
            default -> "ERROR";
        };
    }

    public void setOption(String inputID, String inputText) {
        switch (inputID) {
            case "numPlayers" -> this.setNumPlayers(inputText);
            case "nameList" -> this.setPlayerNames(inputText);
            case "m" -> this.setM(inputText);
            case "n" -> this.setN(inputText);
            case "k" -> this.setK(inputText);
            case "gravityMode" -> this.setGravityMode(inputText);
        }
    }

    /** Setters e getters das configurações */
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
        this.nameList.add(name);
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
        switch (inputText) {
            case "u", "up" -> this.gravityMode = GravityMode.UP;
            case "d", "down" -> this.gravityMode = GravityMode.DOWN;
            case "l", "left" -> this.gravityMode = GravityMode.LEFT;
            case "r", "right" -> this.gravityMode = GravityMode.RIGHT;
            case "o", "off" -> this.gravityMode = GravityMode.DISABLED;
        }
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public String getPlayerName(int ID) {
        int index = ID - 1;
        return this.nameList.get(index);
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

    public GravityMode getGravityMode() {
        return this.gravityMode;
    }
}
