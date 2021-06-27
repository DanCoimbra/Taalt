package gameclient;

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
    private int content;

    private final int MAX_PLAYERS = 4;

    public GUICell(Point pos, GUIBoard board) {
        super();
        this.content = 0;
        this.pos = pos;
        this.board = board;
        this.paint();

        // Remove bordas e uma interação gráfica indesejável
        this.setBorder(null);
        this.setFocusable(false);
    }

    private void paint() {
        this.setBackground(getPlayerColor(this.content));
    }

    // TODO: Fix
    private Color getPlayerColor(int playerID) {
        assert playerID <= MAX_PLAYERS;
        int playerTone = 255 - playerID * (255 / MAX_PLAYERS);
        return new Color(255, playerTone, playerTone);
    }

    public void update(int content) {
        this.content = content;
        this.paint();

        // Habilita a célula apenas se estiver vazia
        this.setEnabled(content == 0);
    }

    /** Sobrescreve método de JButton. */
    @Override
    protected void fireActionPerformed(ActionEvent e) {
        this.board.cellClick(this.pos);
    }
}
