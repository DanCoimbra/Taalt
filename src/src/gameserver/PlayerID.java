package gameserver;

/** Armazena identificadores de jogadores, inclusive a ausência de um jogador (NONE). */
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

    /** Retorna o próximo jogador. ONE retorna TWO, TWO retorna THREE, etc., e TEN retorna ONE. */
    public PlayerID getNext() {
        int nextIndex = (this.ordinal() + 1) % values().length;
        if (nextIndex == 0) {
            nextIndex += 1;
        }
        return values()[nextIndex];
    }
}


