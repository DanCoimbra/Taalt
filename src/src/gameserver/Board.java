package gameserver;

import java.awt.*;

/**
 *  Tabuleiro de uma partida de Taalt. Armazena uma matriz de objetos Cell, de dimensões especificadas
 *  no construtor. Armazena a quantidade de peças consecutivas requeridas para vitória, e com essa
 *  informação a função "Board.hasWon()" detecta se um jogador ganhou.
 */
public class Board {
    private Game game;
    private int successiveCellsToWin;
    private int rows, cols;
    private GravityMode gravityMode;
    private Cell[][] matrix;

    Board(Game game, int successiveCellsToWin, GravityMode gravityMode, Dimension dimension) {
        this.game = game;
        this.successiveCellsToWin = successiveCellsToWin;
        this.gravityMode = gravityMode;
        this.rows = (int) dimension.getWidth();
        this.cols = (int) dimension.getHeight();

        this.matrix = new Cell[this.rows][this.cols];
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                this.matrix[row][col] = new Cell();
            }
        }
    }

    public int getCellContent(Point pos) {
        Cell cell = this.getCell(pos);
        assert cell != null;
        return cell.getContent();
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
     *  Dada uma posição válida, calcula qual posição poderia ser preenchida dada o estado atual do
     *  modo gravidade (possivelmente DISABLED) e, caso haja tal posição, altera a célula  ali contida.
     *  Termina por avisar o controlador de jogo (Game) de que houve alteração naquela posição.
     */
    public boolean fillCell(Point pos, Player player) {
        if (this.insideBoard(pos) && this.isEmpty(pos)) {
            Cell cell = this.getCell(pos);
            assert cell != null;

            Piece piece = new Piece(player);
            player.addPiece(piece);
            cell.setPiece(piece);

            return true;
        }
        return false;
    }

    /** Dada uma posição válida, calcula qual posição poderia ser preenchida dada o estado atual do modo gravidade. */
    public Point fallPiece(Point pos) {
        if (this.gravityMode != GravityMode.DISABLED) {
            int[] fallDirection = this.getFallDirection();
            Point parsePos = new Point(pos.x, pos.y);
            Point lastEmptyPos = new Point(pos.x, pos.y);
            while (true) {
                parsePos.x += fallDirection[0];
                parsePos.y += fallDirection[1];
                if (this.insideBoard(parsePos) && this.isEmpty(parsePos)) {
                    lastEmptyPos.x = parsePos.x;
                    lastEmptyPos.y = parsePos.y;
                } else {
                    break;
                }
            }

            Cell prevCell = this.getCell(pos);
            Piece piece = prevCell.getPiece();
            prevCell.removePiece();

            Cell newCell = this.getCell(lastEmptyPos);
            newCell.setPiece(piece);

            return lastEmptyPos;
        }
        return null;
    }

    public int[] getFallDirection() {
        return switch (this.gravityMode) {
            case DISABLED -> new int[]{0, 0};
            case UP -> new int[]{-1, 0};
            case DOWN -> new int[]{+1, 0};
            case LEFT -> new int[]{0, -1};
            case RIGHT -> new int[]{0, +1};
        };
    }

    public void fallBoard() {
        if (this.gravityMode != GravityMode.DISABLED) {
            int rowStart = 0;
            int colStart = 0;
            int rowStep = 1;
            int colStep = 1;
            int rowEnd = this.rows - 1;
            int colEnd = this.cols - 1;
            switch (gravityMode) {
                case DOWN -> {
                    rowStart = this.rows - 1;
                    rowStep = -1;
                    rowEnd = 0;
                }
                case RIGHT -> {
                    colStart = this.cols - 1;
                    colStep = -1;
                    colEnd = 0;
                }
            }

            for (int row = rowStart; row <= rowEnd; row += rowStep) {
                for (int col = colStart; col <= colEnd; col += colStep) {
                    Point pos = new Point(row, col);
                    Cell cell = this.getCell(pos);
                    if (cell != null) {
                        Point posFallen = fallPiece(pos);
                        if (posFallen != null) {
                            this.game.cellUpdate(pos);
                            this.game.cellUpdate(posFallen);
                        }
                    }
                }
            }
        }
        this.game.gameUpdate();
    }


    /**
     *  Determina se um jogador venceu. Para tornar tal determinação mais eficiente,
     *  é informada a função uma posição a partir da qual ela deve checar por
     *  sequências longas o suficiente de peças do mesmo jogador, informado como
     *  parâmetro. O uso correto da função requer informar-lhe a posição e o jogador
     *  correspondente à última jogada que foi feita.
     */
    public boolean hasWon() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Point pos = new Point(row, col);
                if (this.winningPosition(pos)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean winningPosition(Point pos) {
        Piece piece = this.getCell(pos).getPiece();
        if (piece != null) {
            Player player = piece.getPlayer();

            int rowSelected = pos.x;
            int colSelected = pos.y;
            int rowParse, colParse;
            int consecutive;
            Cell cellParse;
            int[][] directions = new int[][]{{0, -1}, {0, +1},
                    {-1, 0}, {+1, 0},
                    {+1, +1}, {-1, -1},
                    {+1, -1}, {-1, +1}};

            for (int[] direction : directions) {
                rowParse = rowSelected;
                colParse = colSelected;
                consecutive = 0;
                while (this.insideBoard(new Point(rowParse, colParse))) {
                    cellParse = this.matrix[rowParse][colParse];
                    if (cellParse.getContent() == player.getID()) {
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
        }
        return false;
    }

    public void setGravityMode(GravityMode gravityMode) {
        this.gravityMode = gravityMode;
        this.fallBoard();
    }

}
