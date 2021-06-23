package gameserver;

import java.awt.*;
import java.util.ArrayList;

public class Game implements IGame {
    ArrayList<IOutputReceiver> outputReceiverList;
    GameStatus status;
    Options options;
    Board board;

    public Game(Options options) {
        outputReceiverList = new ArrayList<IOutputReceiver>();
        this.status = new GameStatus(options);
        this.options = options;

        Dimension boardDimension = new Dimension(options.getM(), options.getN());
        this.board = new Board(this.options.getK(), boardDimension);
    }

    // Implementa métodos de IContentProducerViewer
    @Override
    public IContentProducer getContentProducer(Point pos) {
        return this.board.getContentProducer(pos);
    }

    // Implementa métodos de IInputReceiver
    @Override
    public void receiveInput(Input input) {
        Point pos = input.getInput();
        Player currentPlayer = this.status.getCurrentPlayer();
        this.board.fillCell(pos, currentPlayer);
        boolean end = this.board.hasWon(pos, currentPlayer);
        if (end) {
           this.status.endGame();
        } else {
            this.status.nextRound();
        }
        this.fireOutput();
    }

    // Implementa métodos de IOutputProducer
    @Override
    public void addOutputReceiver(IOutputReceiver listener) {
        this.outputReceiverList.add(listener);
    }

    @Override
    public void clearOutputReceiverList() {
        this.outputReceiverList = new ArrayList<IOutputReceiver>();
    }

    @Override
    public void fireOutput() {
        Output output = this.status.getOutput();
        for (IOutputReceiver listener: this.outputReceiverList) {
            listener.receiveOutput(output);
        }
        if (output.getGameCondition() == GameCondition.END) {
            this.clearOutputReceiverList();
        }
    }
}
