package gameclient;

import gameserver.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *  Armazena e organiza os componentes gráficos correspondentes ao jogo.
 *
 *  Por implementar a interface ICommandReceiver, pode receber comandos de CommandProducers.
 *  Por meio do método implementado "ICommandProducer.fireCommand()", repassa tais comandos
 *  para quaisquer recipientes CommandReceiver.
 */

// TODO: Consider JOptionPane for error messages, end game messages, and replay queries.
public class GUIGameScreen extends JPanel implements ICommandReceiver, IClient {
    public static final int MENU_SIZE = 50;
    public static final int STATUS_SIZE = 50;
    GUIController parentScreen;
    IGame gameServer;
    Output output;
    GUIGameMenu menu;
    GUIBoard board;
    GUIGameStatus status;
    int width, height;
    int rows, cols;

    public GUIGameScreen(int width, int height, int rows, int cols, GUIController parentScreen) {
        super();
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.cols = cols;
        this.parentScreen = parentScreen;
        this.setLayout(new BorderLayout());
        this.setSize(width, height - MENU_SIZE - STATUS_SIZE);

        this.menu = new GUIGameMenu(width, MENU_SIZE);
        this.add(menu, BorderLayout.PAGE_START);

        this.board = new GUIBoard(this.rows, this.cols, width, height);
        this.board.addCommandReceiver(this);
        this.add(board, BorderLayout.CENTER);

        this.status = new GUIGameStatus(width, STATUS_SIZE);
        this.add(status, BorderLayout.PAGE_END);
    }

    /** Constrói um GUIBoard, o preenche com GUICells, e conecta cada uma com a correspondente "gameserver.Cell". */
    public void sync(IGame gameServer) {
        this.board.sync(gameServer);
    }

    /** Implementa métodos de ICommandReceiver. */
    @Override
    public void listenCommand(Point pos) {
        Input input = new Input(pos);
        this.fireInput(input);
    }

    /** Implementa métodos de IInputProducer (subsumido em IClient). */
    @Override
    public void addInputReceiver(IInputReceiver gameServer) {
        this.gameServer = (IGame) gameServer;
    }

    @Override
    public void fireInput(Input input) {
        this.gameServer.receiveInput(input);
    }

    /** Implementa métodos de IOutputReceiver (subsumido em IClient).
     *  Atualiza o mostrador dos dados do jogo (GUIGameStatus) e, se for o caso,
     *  chama "GUIController.end()" para finalizar a partida atual.
     */
    @Override
    public void receiveOutput(Output output) {
        this.output = output;
        this.status.update(output);
        if (output.getGameCondition() == GameCondition.END) {
            this.parentScreen.end();
        }
    }
}
