package gameclient;

import java.awt.*;

/**
 *  Interface para recipientes de comandos, correspondentes a cliques
 *  em uma célula com certas coordenadas no tabuleiro.
 */
public interface ICommandReceiver {
    public void listenCommand(Point pos);
}
