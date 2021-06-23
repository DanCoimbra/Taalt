package gameclient;

import gameserver.Cell;
import gameserver.IContentProducer;
import gameserver.IGame;

import java.awt.*;
import javax.swing.*;

/**
 *  Componente gráfico correspondente ao tabuleiro. Organiza componentes em formato de grade,
 *  que podem ser adicionados dinamicamente. Possui verificação interna de consistência na
 *  adição de tais componentes.
 *
 *  Por implementar a interface ICommandReceiver, pode receber comandos de CommandProducers.
 *  Por meio do método implementado "ICommandProducer.fireCommand()", repassa tais comandos
 *  para quaisquer recipientes CommandReceiver.
 */


public class GUIBoard extends JPanel implements ICommandReceiver, ICommandProducer {
    private static final int CELL_GAP = 3;
    int rows, cols;
    GUICell[][] matrix;
    ICommandReceiver commandReceiver;

    public GUIBoard(int rows, int cols, int width, int height) {
        super();
        this.rows = rows;
        this.cols = cols;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new GridLayout(rows, cols, CELL_GAP, CELL_GAP));

        GUICell guiCell;
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Point pos = new Point(row, col);
                guiCell = new GUICell(pos);
                this.setCell(pos, guiCell);
            }
        }
    }

    /** Conecta cada GUICell do GUIBoard com sua célula ContentProducer correspondente no servidor do jogo. */
    public void sync(IGame gameServer) {
        GUICell guiCell;
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                guiCell = this.matrix[row][col];
                assert guiCell != null;

                Point pos = new Point(row, col);
                IContentProducer contentProducer = gameServer.getContentProducer(pos);
                assert contentProducer != null;

                // Sincronização
                contentProducer.addContentReceiver(guiCell);
            }
        }
    }

    /** Verificação de consistência interna para quaisquer adição de componentes. */
    public boolean insideBoard(Point pos) {
        boolean rowInside = (pos.x >= 0 && pos.x < this.rows);
        boolean colInside = (pos.y >= 0 && pos.y < this.cols);
        return rowInside && colInside;
    }

    public boolean isEmpty(Point pos) {
        return this.insideBoard(pos) && (this.matrix[pos.x][pos.y] == null);
    }

    /** Adição de componentes  */
    public void setCell(Point pos, GUICell cell) {
        if (isEmpty(pos)) {
            cell.addCommandReceiver(this);
            this.matrix[pos.x][pos.y] = cell;
            this.add(cell);
        }
    }

    /** Implementa métodos de ICommandReceiver. */
    @Override
    public void listenCommand(Point pos) {
        this.fireCommand(pos);
    }

    /** Implementa métodos de ICommandProducer. */
    @Override
    public void addCommandReceiver(ICommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void fireCommand(Point pos) {
        this.commandReceiver.listenCommand(pos);
    }


}
