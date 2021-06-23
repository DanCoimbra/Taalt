package gameserver;

import java.awt.*;

/** Um objeto Input nada mais é do que coordenadas de tabuleiro, nas quais se deseja inserir uma peça. */
public class Input {
    Point point;

    public Input(Point point) {
        this.point = point;
    }

    public Point getInput() {
        return this.point;
    }
}
