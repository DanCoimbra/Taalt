package gameserver;

public class Piece {
    Player player;

    public Piece(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public PlayerID getID() {
        return this.player.getID();
    }
}
