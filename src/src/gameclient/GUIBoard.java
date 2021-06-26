package gameclient;

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


public class GUIBoard extends JPanel {
    private static final int CELL_GAP = 3;
    int rows, cols;
    GUICell[][] matrix;
    GUIGameScreen gameScreen;

    public GUIBoard(int width, int height, GUIGameScreen gameScreen) {
        super();
        this.rows = height;
        this.cols = width;
        this.matrix = new GUICell[rows][cols];
        this.gameScreen = gameScreen;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new GridLayout(rows, cols, CELL_GAP, CELL_GAP));
    }

    public void fillBoard() {
        GUICell guiCell;
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Point pos = new Point(row, col);
                guiCell = new GUICell(pos, this);
                this.setCell(pos, guiCell);
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

    /** Adição de um componente GUICell. */
    public void setCell(Point pos, GUICell cell) {
        if (isEmpty(pos)) {
            this.matrix[pos.x][pos.y] = cell;
            this.add(cell);
        }
    }

    /** Alteração de um componente GUICell a partir de GUIGameScreen. */
    public void updateCell(Point pos, int content) {
        if (insideBoard(pos)) {
            GUICell cell = this.matrix[pos.x][pos.y];
            cell.update(content);
        }
    }

    /** Recebimento de input do usuário a partir de GUICell. */
    public void cellClick(Point pos) {
        this.gameScreen.cellClick(pos);
    }

}
