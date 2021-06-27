package gameclient;

import gameserver.*;

import javax.swing.*;
import java.awt.*;

/**
 *  Armazena e organiza os componentes gráficos correspondentes ao jogo. A barra de ferramentas
 *  permite sair do jogo e também ajustar o modo gravidade. A barra de status exibe dados sobre
 *  o estado atual do jogo. O tabuleiro permite inserir peças no tabuleiro do servidor.
 */

// TODO: Consider JOptionPane for error messages, end game messages, and replay queries.
public class GUIGameScreen extends JPanel implements IGameScreen {
    private GUIController controller;
    private IGame gameServer;
    private GUIGameToolbar toolbar;
    private GUIBoard board;
    private GUIGameStatus status;

    private final Dimension panelDimension;
    private static final int MENU_SIZE = 50;
    private static final int STATUS_SIZE = 50;

    public GUIGameScreen(GUIController controller) {
        super();
        this.controller = controller;

        /* Configuração visual do JPanel. */
        this.panelDimension = controller.getWindowDimensions();
        this.setLayout(new BorderLayout());
        this.setSize(this.panelDimension.width, this.panelDimension.height - MENU_SIZE - STATUS_SIZE);

        /* Cria a barra de ferramentas e a barra de status da exibição do jogo. */
        this.toolbar = new GUIGameToolbar(this.panelDimension.width, MENU_SIZE, this);
        this.add(toolbar, BorderLayout.PAGE_START);

        this.status = new GUIGameStatus(this.panelDimension.width, STATUS_SIZE);
        this.add(status, BorderLayout.PAGE_END);
    }

    @Override
    public void setupGameScreen(IGame gameServer) {
        this.gameServer = gameServer;

        //TODO: REVER REFATORAÇÃO
        GameStatus initialState = this.gameServer.getGameStatus();

        this.status.update(initialState);

        this.board = new GUIBoard(this.panelDimension, this);
        this.board.fillBoard(initialState.getM(), initialState.getN());
        this.add(board, BorderLayout.CENTER);
    }

    @Override
    public void updateGameStatus() {

        //TODO: REVER REFATORAÇÃO
        GameStatus output = this.gameServer.getGameStatus();

        this.status.update(output);
        if (output.hasGameEnded()) {
            this.endGame();
        }
    }

    @Override
    public void updateCell(Point cellCoordinates) {
        int cellContent = this.gameServer.getCellContent(cellCoordinates);
        this.board.updateCell(cellCoordinates, cellContent);
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