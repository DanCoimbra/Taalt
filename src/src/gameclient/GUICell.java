package gameclient;

import gameserver.IContentProducer;
import gameserver.IContentReceiver;
import gameserver.PlayerID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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

public class GUICell extends JButton implements ICommandProducer, IContentReceiver {
    Point pos;
    ArrayList<ICommandReceiver> commandReceiverList;
    ArrayList<IContentProducer> contentProducerList;

    public GUICell(Point pos) {
        super();
        this.pos = pos;
        this.commandReceiverList = new ArrayList<ICommandReceiver>();
        this.contentProducerList = new ArrayList<IContentProducer>();
        this.paint(-1);

        // Remove bordas e uma interação gráfica indesejável
        this.setBorder(null);
        this.setFocusable(false);
    }

    public void paint(int playerID) {
        int tone = 255 - 51 * (playerID + 1);
        if (tone < 0) {
            tone = 0;
        }
        this.setBackground(new Color(255, tone, tone));
    }

    /** Sobrescreve método de JButton. */
    @Override
    protected void fireActionPerformed(ActionEvent e) {
        this.fireCommand(this.pos);
    }

    /** Implementa métodos de ICommandProducer. */
    @Override
    public void addCommandReceiver(ICommandReceiver commandReceiver) {
        this.commandReceiverList.add(commandReceiver);
    }

    @Override
    public void fireCommand(Point pos) {
        for (ICommandReceiver commandReceiver : this.commandReceiverList) {
            commandReceiver.listenCommand(pos);
        }
    }

    /** Implementa métodos de IContentReceiver. */
    @Override
    public void noticeContentUpdate(IContentProducer source) {
        int playerID = source.sendContent();
        this.paint(playerID - 1);

        // Bloqueia novos cliques
        this.setEnabled(false);
    }
}
