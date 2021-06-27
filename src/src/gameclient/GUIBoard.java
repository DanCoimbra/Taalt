package gameclient;

import java.awt.*;
import javax.swing.*;

/**
 *  Componente gráfico correspondente ao tabuleiro. Organiza componentes em formato de grade,
 *  que podem ser adicionados dinamicamente. Possui verificação interna de consistência na
 *  adição de tais componentes. Envia cliques obtidos em GUICell para GUIGameScreen. Recebe
 *  atualizações para GUICell a partir de GUIGameScreen.
 */


public class GUIBoard extends JPanel {
    private final GUIGameScreen gameScreen;
    private GUICell[][] matrix;
    private int rows, cols;

    private static final int CELL_GAP = 3;

    /* Configuração visual do tabuleiro. */
    public GUIBoard(Dimension dimension, GUIGameScreen gameScreen) {
        super();
        this.gameScreen = gameScreen;
        this.setPreferredSize(dimension);
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /* Preenche o painel de tabuleiro com rows × cols objetos GUICell, arranjadas em grade. */
    public void fillBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new GUICell[rows][cols];
        this.setLayout(new GridLayout(rows, cols, CELL_GAP, CELL_GAP));
        GUICell guiCell;
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Point pos = new Point(row, col);
                guiCell = new GUICell(pos, this);
                this.setCell(pos, guiCell);
            }
        }
    }

    /* Adição de um componente GUICell ao tabuleiro, condicionado a uma checagem de consistência interna. */
    public void setCell(Point pos, GUICell cell) {
        if (isEmpty(pos)) {
            this.matrix[pos.x][pos.y] = cell;
            this.add(cell);
        }
    }

    /* Abaixo, dois métodos de verificação de consistência interna para quaisquer adição de componentes. */
    public boolean insideBoard(Point pos) {
        boolean rowInside = (pos.x >= 0 && pos.x < this.rows);
        boolean colInside = (pos.y >= 0 && pos.y < this.cols);
        return rowInside && colInside;
    }

    public boolean isEmpty(Point pos) {
        return this.insideBoard(pos) && (this.matrix[pos.x][pos.y] == null);
    }

    /* Recebimento de input do usuário, a partir de GUICell. Input passado a GUIGameScreen. */
    public void cellClick(Point pos) {
        this.gameScreen.cellClick(pos);
    }

    /* Recebimento de output do servidor, a partir de GUIGameScreen. Output passado a GUICell. */
    public void updateCell(Point pos, int content) {
        if (insideBoard(pos)) {
            GUICell cell = this.matrix[pos.x][pos.y];
            cell.update(content);
        }
    }


}
