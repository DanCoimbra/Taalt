package gameserver;

import java.util.ArrayList;

public class Player {
    PlayerID ID;
    String name;
    ArrayList<Piece> pieces;

    public Player(PlayerID ID, String name) {
        this.ID = ID;
        this.name = name;
        this.pieces = new ArrayList<Piece>();
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public PlayerID getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }
}
