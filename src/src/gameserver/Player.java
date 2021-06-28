package gameserver;

import java.awt.*;

/**
 * Representa um jogador de uma partida de Taalt. Possui um ID único,
 * um nome, e uma lista de peças no tabuleiro das quais é dono.
 */
public class Player {
    private final String name;
    private final int ID;
    private final Color color;

    public Player(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.color = generateColor();

    }

    private Color generateColor() {
        return switch (this.ID) {
            case 1 -> new Color(255, 0, 0);
            case 2 -> new Color(0, 255, 0);
            case 3 -> new Color(0, 0, 255);
            case 4 -> new Color(255, 255, 0);
            default -> new Color(0,0,0);
        };
    }

    public Color getColor() {
        return color;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }
}
