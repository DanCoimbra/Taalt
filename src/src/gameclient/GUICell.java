package gameclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *  Componente gráfico correspondente à célula do tabuleiro.
 *
 *  Cliques do usuário acionam o método sobrescrito "JButton.fireActionperformed()".
 *
 *  GUICell usa este método para enviar comandos para quaisquer recipientes CommandReceiver,
 *  por meio do método implementado "ICommandProducer.fireCommand()".
 *
 *  Por implementar a interface IContentReceiver, pode receber avisos de ContentProducers
 *  de que houve uma atualização no conteúdo destes, via "IContentReceiver.noticeContentUpdate()".
 *  Ao receber o aviso, inquirirá o ContentProducer sobre seu novo conteúdo, e atualizará
 *  sua representação gráfica do conteúdo de acordo.
 */

public class GUICell extends JButton {
    int content;
    Point pos;
    GUIBoard board;

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

    public void paint() {
        int tone = 255 - 51 * this.content;
        if (tone < 0) {
            tone = 0;
        }
        this.setBackground(new Color(255, tone, tone));
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
