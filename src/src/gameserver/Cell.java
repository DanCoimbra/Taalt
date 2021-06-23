package gameserver;

import java.util.ArrayList;

/**
 *  Célula do tabuleiro de uma partida de Taalt. Armazena um objeto Piece em um ponteiro
 *  possivelmente nulo. Por meio da interface IContentProducer, avisa quaisquer implementadores
 *  de IContentReceiver sempre que ocorrer uma atualização no objeto Piece da célula.
 */
public class Cell implements IContentProducer {
    Piece piece;
    ArrayList<IContentReceiver> contentReceiverList;

    Cell() {
        this.contentReceiverList = new ArrayList<IContentReceiver>();
    }

    Cell(Piece piece) {
        this();
        this.piece = piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.alertContentUpdate();
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    /** Implementa os métodos de IContentProducer. */
    @Override
    public void addContentReceiver(IContentReceiver contentReceiver) {
        this.contentReceiverList.add(contentReceiver);
    }

    @Override
    public void alertContentUpdate() {
        for (IContentReceiver contentReceiver: this.contentReceiverList) {
            contentReceiver.noticeContentUpdate(this);
        }
    }

    @Override
    public int sendContent() {
        if (this.piece != null) {
            return this.piece.getID().ordinal();
        } else {
            return PlayerID.NONE.ordinal();
        }
    }
}
