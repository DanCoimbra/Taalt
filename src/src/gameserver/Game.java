package gameserver;

import gameclient.IUpdateGameView;

import java.awt.*;
import java.util.ArrayList;

// TODO: Implementar função buildGame, que basicamente inicializa a partida com os parâmetros e constrói o tabuleiro.

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
    private ArrayList<IUpdateGameView> gameViewerList;
    private GameStatus status;
    private Board board;

    public Game() {
        this.gameViewerList = new ArrayList<IUpdateGameView>();
    }

    public void setGameStatus(Options options) {
        this.status = new GameStatus(options);
    }

    public void setBoard(Board board) {
        /* Garante que "ninguém" (i.e. o Sr. Coimbra) use essa função de maneira errada, recriando um jogo com o mesmo
         * objeto ao invés de usar o GameBuilder pra criar um novo objeto jogo */
        if (this.board == null) {
            this.board = board;
        }
    }

    /**
     *  Receber uma tentativa de jogada: um objeto Input, que apenas informa uma posição do tabuleiro.
     *  Preenche o tabuleiro, e ou passa de turno ou termina o jogo.
     *  Por fim, emite um Output para quaisquer OutputReceivers listados.
     */
    @Override
    public void placePiece(Point pos) {
        Player currentPlayer = this.status.getCurrentPlayer();
        boolean successfulMove = this.board.fillCell(pos, currentPlayer);
        if (successfulMove) {
            Point posFallen = this.board.fallPiece(pos);
            this.cellUpdate(pos);
            if (posFallen == null) {
                this.cellUpdate(pos);
            } else {
                this.cellUpdate(posFallen);
            }
            this.status.nextRound();
            this.gameUpdate();
        }
    }

    public void cellUpdate(Point pos) {
        for (IUpdateGameView gameView: this.gameViewerList) {
            gameView.updateCell(pos);
        }
    }

    public void gameUpdate() {
        if (this.board.hasWon()) {
            this.status.endGame();
        }

        for (IUpdateGameView gameView: this.gameViewerList) {
           gameView.updateGameStatus();
        }
    }

    @Override
    public void setGravityMode(GravityMode gravityMode) {
        this.status.setGravityMode(gravityMode);
        this.board.setGravityMode(gravityMode);
    }

    @Override
    public void addGameViewer(IUpdateGameView gameViewer) {
        this.gameViewerList.add(gameViewer);
    }

    @Override
    public GameStatus getGameStatus() {
        return this.status;
    }

    /** Método de IGameView. Permite que agentes externos observem o conteúdo de uma célula. */
    @Override
    public int getCellContent(Point pos) {
        return this.board.getCellContent(pos);
    }
}
