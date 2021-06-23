package gameserver;

public enum PlayerID {
    NONE,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN;

    public PlayerID getNext() {
        int nextIndex = (this.ordinal() + 1) % values().length;
        return values()[nextIndex];
    }
}


