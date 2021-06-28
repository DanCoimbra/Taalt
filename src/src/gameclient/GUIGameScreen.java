package gameclient;

import gameserver.Cell;
import gameserver.IGame;
import gameserver.GameStatus;
import gameserver.GravityMode;

import javax.swing.*;
import java.awt.*;

/**
 *  Armazena e organiza os componentes gráficos correspondentes ao jogo. A barra de ferramentas
 *  permite sair do jogo e também ajustar o modo gravidade. A barra de status exibe dados sobre
 *  o estado atual do jogo. O tabuleiro permite inserir peças no tabuleiro do servidor.
 */

// TODO: Consider JOptionPane for error messages, end game messages, and replay queries.
public class GUIGameScreen extends JPanel implements IGameScreen {
    private final GUIController controller;
    private final Dimension gameScreenDimension;
    private IGame gameServer;

    // Elementos Swing contidos no painel.
    private GUIBoard board;
    private final GUIGameStatus gameStatusPanel;
    private final GUIGameToolbar toolbar;

    private static final int MENU_SIZE = 80;
    private static final int STATUS_SIZE = 50;

    public GUIGameScreen(GUIController controller) {
        super();
        this.controller = controller;

        /* Configuração visual do JPanel. */
        this.gameScreenDimension = controller.getWindowDimensions();
        this.setLayout(new BorderLayout());
        this.setSize(this.gameScreenDimension.width, this.gameScreenDimension.height - MENU_SIZE - STATUS_SIZE);

        /* Cria a barra de ferramentas e a barra de status da exibição do jogo. */
        this.toolbar = new GUIGameToolbar(this.gameScreenDimension.width, MENU_SIZE, this);
        this.add(toolbar, BorderLayout.PAGE_START);

        this.gameStatusPanel = new GUIGameStatus(this.gameScreenDimension.width, STATUS_SIZE);
        this.add(gameStatusPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void setupGameScreen(IGame gameServer) {
        this.gameServer = gameServer;

        GameStatus initialState = this.gameServer.getGameStatus();
        this.gameStatusPanel.update(initialState);

        if (this.board != null) {
            this.remove(this.board);
        }

        this.board = new GUIBoard(this.gameScreenDimension, this);
        this.board.fillBoard(initialState.getM(), initialState.getN());
        this.add(board, BorderLayout.CENTER);
    }

    @Override
    public void updateGameStatus() {
        GameStatus gameStatus = this.gameServer.getGameStatus();
        this.gameStatusPanel.update(gameStatus);

        if (gameStatus.hasGameEnded()) {
            this.endGame();
        }
    }

    @Override
    public void updateCell(Point cellCoordinates) {
        Cell cell = this.gameServer.getCell(cellCoordinates);
        this.board.updateCell(cellCoordinates, cell);
    }

    /* Envia ao servidor IGame as coordenadas onde o usuário clicou. */
    public void cellClick(Point pos) {
        this.gameServer.placePiece(pos);
    }

    public void setGravityMode(GravityMode gravityMode) {
        this.gameServer.setGravityMode(gravityMode);
    }

    public void endGame() {
        this.controller.showMainMenu();
    }
}