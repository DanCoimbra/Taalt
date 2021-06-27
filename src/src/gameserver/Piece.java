package gameserver;

/**
 *  Representa as peças do jogo Taalt que, por hora, possui apenas
 *  a funcionalidade de armazenar a identidade de seu Player dono.
 */
public class Piece {
    private Player player;

    public Piece(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getID() {
        return this.player.getID();
    }
}
