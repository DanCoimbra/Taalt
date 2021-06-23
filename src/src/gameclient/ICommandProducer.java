package gameclient;

import java.awt.*;

/**
 *  Interface para produtores de comandos, correspondentes a cliques
 *  em uma célula com certas coordenadas no tabuleiro.
 */
public interface ICommandProducer {
    public void addCommandReceiver(ICommandReceiver commandReceiver);
    public void fireCommand(Point pos);
}
