package gameserver;

import java.util.ArrayList;

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
        this.warnUpdate();
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    @Override
    public void addCellListener(IContentReceiver listener) {
        this.contentReceiverList.add(listener);
    }

    @Override
    public void warnUpdate() {
        for (IContentReceiver listener: this.contentReceiverList) {
            listener.listenUpdate();
        }
    }

    @Override
    public PlayerID sendContent() {
        if (this.piece != null) {
            return this.piece.getID();
        } else {
            return PlayerID.NONE;
        }
    }
}
