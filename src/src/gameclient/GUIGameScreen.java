package gameclient;

import gameserver.*;

import javax.swing.*;
import java.awt.*;

/**
 *  Armazena e organiza os componentes gráficos correspondentes ao jogo.
 *
 *  Por implementar a interface ICommandReceiver, pode receber comandos de CommandProducers.
 *  Por meio do método implementado "ICommandProducer.fireCommand()", repassa tais comandos
 *  para quaisquer recipientes CommandReceiver.
 */

// TODO: Consider JOptionPane for error messages, end game messages, and replay queries.
public class GUIGameScreen extends JPanel implements IGameScreen {
    GUIController controller;
    IGame gameServer;
    GUIGameMenu menu;
    GUIBoard board;
    GUIGameStatus status;

    private final Dimension panelDimensions;
    public static final int MENU_SIZE = 50;
    public static final int STATUS_SIZE = 50;

    public GUIGameScreen(GUIController controller) {
        super();
        this.controller = controller;
        panelDimensions = controller.getWindowDimensions();

        int width = this.panelDimensions.width;
        int height = this.panelDimensions.height;

        this.setLayout(new BorderLayout());
        this.setSize(width, height - MENU_SIZE - STATUS_SIZE);

        this.menu = new GUIGameMenu(width, MENU_SIZE, this);
        this.add(menu, BorderLayout.PAGE_START);

        this.status = new GUIGameStatus(width, STATUS_SIZE);
        this.add(status, BorderLayout.PAGE_END);
    }

    public void setupGame(IGame gameServer) {
        this.gameServer = gameServer;
        Output initialState = this.gameServer.getUpdate();

        this.board = new GUIBoard(this.panelDimensions.width, this.panelDimensions.height, this);
        this.board.fillBoard();
        this.add(board, BorderLayout.CENTER);

        this.status.update(initialState);
    }

    /** Chama o método "IGame.receiveInput()". */
    public void cellClick(Point pos) {
        this.gameServer.placePiece(pos);
    }

    /**
     *  Implementa método de IGameScreen.
     *  Atualiza o mostrador dos dados do jogo (GUIGameStatus) e, se for o caso,
     *  chama "GUIController.end()" para finalizar a partida atual.
     */
    public void updateGameStatus() {
        Output output = this.gameServer.getUpdate();
        this.status.update(output);
        if (output.getGameCondition() == GameCondition.END) {
            this.endGame();
        }
    }

    public void endGame() {
        this.controller.showMainMenu();
    }

    public void updateCell(Point cellCoordinates) {
        int cellContent = this.gameServer.getCellContent(cellCoordinates);
        this.board.updateCell(cellCoordinates, cellContent);
    }

}