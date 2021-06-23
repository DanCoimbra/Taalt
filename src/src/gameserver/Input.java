package gameserver;

import java.awt.*;

public class Input {
    Point point;

    public Input(Point point) {
        this.point = point;
    }

    public Point getInput() {
        return this.point;
    }
}
