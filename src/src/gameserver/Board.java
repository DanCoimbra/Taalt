package gameserver;

import java.awt.*;

/**
 *  Tabuleiro de uma partida de Taalt. Armazena uma matriz de objetos Cell, de dimensões especificadas
 *  no construtor. Armazena a quantidade de peças consecutivas requeridas para vitória, e com essa
 *  informação a função "Board.hasWon()" detecta se um jogador ganhou.
 */
public class Board implements IContentProducerViewer {
    int successiveCellsToWin;
    int rows, cols;
    Cell[][] matrix;

    Board(int successiveCellsToWin, Dimension dimension) {
        this.successiveCellsToWin = successiveCellsToWin;
        this.rows = (int) dimension.getWidth();
        this.cols = (int) dimension.getHeight();
        this.matrix = new Cell[this.rows][this.cols];
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                this.matrix[row][col] = new Cell();
            }
        }
    }

    /** Implementa o método único de IContentProducerViewer. */
    @Override
    public IContentProducer getContentProducer(Point pos) {
        return this.getCell(pos);
    }

    public Cell getCell(Point pos) {
        if (insideBoard(pos)) {
            return this.matrix[pos.x][pos.y];
        } else {
            return null;
        }
    }

    public boolean insideBoard(Point pos) {
        boolean rowInside = (pos.x >= 0 && pos.x < this.rows);
        boolean colInside = (pos.y >= 0 && pos.y < this.cols);
        return rowInside && colInside;
    }

    public boolean isEmpty(Point pos) {
        Cell cell = this.getCell(pos);
        assert cell != null;
        return cell.isEmpty();
    }

    /**
     *  Dada uma posição válida, cria um objeto Cell e atribui-lhe um
     *  objeto Piece, que por sua vez é atribuída a um objeto Player.
     */
    public void fillCell(Point pos, Player player) {
        Cell cell = this.getCell(pos);
        assert cell != null;
        if (cell.isEmpty()) {
            Piece piece = new Piece(player);
            player.addPiece(piece);
            cell.setPiece(piece);
        }
    }

    /**
     *  Determina se um jogador venceu. Para tornar tal determinação mais eficiente,
     *  é informada a função uma posição a partir da qual ela deve checar por
     *  sequências longas o suficiente de peças do mesmo jogador, informado como
     *  parâmetro. O uso correto da função requer informar-lhe a posição e o jogador
     *  correspondente à última jogada que foi feita.
     */
    public boolean hasWon(Point pos, Player player) {
        int rowSelected = pos.x;
        int colSelected = pos.y;
        Cell cellParse;
        int rowParse, colParse;
        int consecutive;
        int[][] directions = new int[][]{{ 0, -1}, { 0, +1},
                                         {-1,  0}, {+1,  0},
                                         {+1, +1}, {-1, -1},
                                         {+1, -1}, {-1, +1}};
        for (int[] direction : directions) {
            rowParse = rowSelected;
            colParse = colSelected;
            consecutive = 0;
            while(this.insideBoard(new Point(rowParse, colParse))) {
                cellParse = this.matrix[rowParse][colParse];
                if (cellParse.sendContent() == player.getID().ordinal()) {
                    consecutive++;
                    rowParse += direction[0];
                    colParse += direction[1];
                    if (consecutive >= this.successiveCellsToWin) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }

}
