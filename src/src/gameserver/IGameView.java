package gameserver;

import gameclient.IUpdateGameView;

import java.awt.*;

/**
 *
 */
public interface IGameView {

    /**
     * Retorna as propriedades atuais do jogo.
     *
     * @return Objeto contendo todas as propriedades e status do jogo.
     */
    GameStatus getGameStatus();

    /**
     * TODO: FUNÇÃO QUE DEVE SER ESTIRPADA DAQUI
     *
     * @param pos Posição da célula
     * @return ID do player que ocupa a célula
     */
    int getCellContent(Point pos);

    /**
     * Conecta um visualizador ao servidor, para que este possa notificá-lo de mudanças no jogo.
     *
     * @param gameScreen Visualizador do jogo.
     */
    void addGameViewer(IUpdateGameView gameScreen);
}
