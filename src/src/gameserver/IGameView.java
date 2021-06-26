package gameserver;

import gameclient.IUpdateGameView;

import java.awt.*;

/**
 *  Interface para produtores de objetos Output, correspondentes
 *  a informações do estado atual do jogo.
 */
public interface IGameView {
    Output getUpdate();
    int getCellContent(Point pos);
    void addGameViewer(IUpdateGameView gameScreen);
}
