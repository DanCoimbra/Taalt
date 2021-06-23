package gameserver;

import java.awt.*;
import java.util.ArrayList;

/**
 *  Servidor de uma partida de Taalt. Cria um objeto Board, correspondente ao tabuleiro do jogo,
 *  e um objeto GameStatus, correspondendo aos dados do andamento do jogo, a exemplo de qual é
 *  o jogador da vez e qual é o número do turno atual.
 *
 *  Implementa a interface agregadaora IGame, a qual lhe permite receber inputs (via IInputReceiver),
 *  enviar outputs (via IOutputProducer), e enviar as células (IContentProducer) individuais do
 *  tabuleiro (via IContentProducerViewer).
 */
public class Game implements IGame {
    ArrayList<IOutputReceiver> outputReceiverList;
    GameStatus status;
    Board board;

    public Game(Options options) {
        this.outputReceiverList = new ArrayList<IOutputReceiver>();
        this.status = new GameStatus(options);

        Dimension boardDimension = new Dimension(options.getM(), options.getN());
        this.board = new Board(options.getK(), boardDimension);
    }

    /** Implementa métodos de IContentProducerViewer. */
    @Override
    public IContentProducer getContentProducer(Point pos) {
        return this.board.getContentProducer(pos);
    }

    /**
     *  Implementa métodos de IInputReceiver.
     *  Receber uma tentativa de jogada: um objeto Input, que apenas informa uma posição do tabuleiro.
     *  Preenche o tabuleiro, e ou passa de turno ou termina o jogo.
     *  Por fim, emite um Output para quaisquer OutputReceivers listados.
     */
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

    /** Implementa métodos de IOutputProducer. */
    @Override
    public void addOutputReceiver(IOutputReceiver listener) {
        this.outputReceiverList.add(listener);
    }

    @Override
    public void clearOutputReceiverList() {
        this.outputReceiverList = new ArrayList<IOutputReceiver>();
    }

    /** Método de IOutputProducer. Emite um Output para quaisquer OutputReeivers listados.
     *  Se o jogo houver terminado, limpa a lista de OutputReceivers.
     */
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
