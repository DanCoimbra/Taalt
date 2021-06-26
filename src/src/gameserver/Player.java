package gameserver;

import java.util.ArrayList;

/**
 *  Representa um jogador de uma partida de Taalt. Possui um ID único,
 *  um nome, e uma lista de peças no tabuleiro das quais é dono.
 */
public class Player {
    int ID;
    String name;
    ArrayList<Piece> pieces;

    public Player(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.pieces = new ArrayList<Piece>();
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }
}
