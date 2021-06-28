package gameclient;

import gameserver.Cell;
import gameserver.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *  Componente gráfico correspondente à célula do tabuleiro. Cliques do usuário acionam o método
 *  sobrescrito "JButton.fireActionperformed()", que passará o comando a GUIBoard.
 *  Recebe atualizações de conteúdo a partir de GUIBoard.
 */

public class GUICell extends JButton {
    private GUIBoard board;
    private Point pos;
    private Cell cell;

    public GUICell(Point pos, GUIBoard board) {
        super();
        this.pos = pos;
        this.board = board;
        this.paint();

        // Remove bordas e uma interação gráfica indesejável
        this.setBorder(null);
        this.setFocusable(false);
    }

    private void paint() {
        Piece p;

        if (this.cell != null && (p = this.cell.getPiece()) != null) {
            this.setBackground(p.getPlayer().getColor());
        } else {
            this.setBackground(new Color(255,255,255));
        }
    }

    public void update(Cell cell) {
        this.cell = cell;
        this.paint();

        // Habilita a célula apenas se estiver vazia
        this.setEnabled(cell.isEmpty());
    }

    /** Sobrescreve método de JButton. */
    @Override
    protected void fireActionPerformed(ActionEvent e) {
        this.board.cellClick(this.pos);
    }
}
