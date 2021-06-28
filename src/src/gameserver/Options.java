package gameserver;

import java.util.ArrayList;
import java.lang.Math;

/**
 *  Objetos Options contêm especificações sobre uma partida de Taalt: o número de jogadores,
 *  o nome dos jogadores, as dimensões do tabuleiro (m x n), o tamanho da sequência de peças
 *  requerida para vitória (k), e se o modo gravidade está ativado.
 */
public class Options {
    private final String[] inputFields;
    private final ArrayList<String> nameList;
    private GravityMode gravityMode;
    private int m, n, k;
    private int numPlayers;

    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 4;
    public static final int MIN_DIMENSION = 3;
    public static final int MAX_DIMENSION = 9;
    public static final int MIN_K = 3;

    public Options() {
        this.inputFields = new String[]{"numPlayers", "nameList", "m", "n", "k", "gravityMode"};
        this.nameList = new ArrayList<String>();
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
        int numPlayers;

        try {
            numPlayers = Integer.parseInt(inputText);
            if (MIN_PLAYERS <= numPlayers && numPlayers <= MAX_PLAYERS) {
                this.numPlayers = numPlayers;
            } else {
                this.numPlayers = MIN_PLAYERS;
            }
        } catch (NumberFormatException e) {
            this.numPlayers = MIN_PLAYERS;
        }
    }

    public void setPlayerNames(String inputText) {
        if (inputText.equals("")) {
            for (int i = 0; i < MAX_PLAYERS; i++) {
                this.nameList.add("Default name");
            }
        } else {
            String[] inputNames = inputText.split(",");
            for (String name : inputNames) {
                this.addPlayerName(name);
            }
            while (this.nameList.size() < this.numPlayers) {
                this.addPlayerName("Default name");
            }
        }
    }

    public void addPlayerName(String name) {
        this.nameList.add(name);
    }

    public void setM(String inputText) {
        try {
            int m = Integer.parseInt(inputText);
            if (MIN_DIMENSION <= m && m <= MAX_DIMENSION) {
                this.m = m;
            }
        }
        catch (NumberFormatException e) {
            this.m = MIN_DIMENSION;
        }
    }

    public void setN(String inputText) {
        try {
            int n = Integer.parseInt(inputText);
            if (MIN_DIMENSION <= n && n <= MAX_DIMENSION) {
                this.n = n;
            }
        }
        catch (NumberFormatException e) {
            this.n = MIN_DIMENSION;
        }
    }

    public void setK(String inputText) {
        try {
            int k = Integer.parseInt(inputText);
            int max_k = Math.max(this.m, this.n);
            if (MIN_K <= k && k <= max_k) {
                this.k = k;
            } else {
                this.k = MIN_K;
            }
        }
        catch (NumberFormatException e) {
            this.k = MIN_K;
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
            default -> this.gravityMode = GravityMode.DISABLED;
        }
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public String getPlayerName(int index) {
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
