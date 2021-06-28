package gameserver;

import gameclient.IUpdateGameView;

import java.awt.*;
import java.util.ArrayList;

/**
 *  Servidor de uma partida de Taalt. Conecta um objeto Board, correspondente ao tabuleiro do jogo,
 *  e cria um objeto GameStatus, correspondendo aos dados do andamento do jogo, a exemplo de qual é
 *  o jogador da vez e qual é o número do turno atual.
 *
 *  Implementa a interface agregadaora IGame, a qual lhe permite receber inputs (via IInputReceiver),
 *  enviar outputs (via IOutputProducer), e enviar as células (IContentProducer) individuais do
 *  tabuleiro (via IContentProducerViewer).
 */
public class Game implements IGame {
    private final ArrayList<IUpdateGameView> gameViewerList;
    private GameStatus status;
    private Board board;

    public Game() {
        this.gameViewerList = new ArrayList<>();
    }

    @Override
    public void setGameStatus(Options options) {
        this.status = new GameStatus(options);
    }

    @Override
    public void setBoard(Board board) {
        if (this.board == null) {
            this.board = board;
        }
    }

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

    @Override
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

    @Override
    public Cell getCell(Point pos) {
        return this.board.getCell(pos);
    }
}
