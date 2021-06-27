package gameserver;

/**
 *  Célula do tabuleiro de uma partida de Taalt. Armazena um objeto Piece em um ponteiro
 *  possivelmente nulo. Por meio da interface IContentProducer, avisa quaisquer implementadores
 *  de IContentReceiver sempre que ocorrer uma atualização no objeto Piece da célula.
 */
public class Cell {
    private Piece piece;

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public int getContent() {
        if (this.piece != null) {
            return this.piece.getID();
        } else {
            return 0;
        }
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void removePiece() {
        this.piece = null;
    }
}
