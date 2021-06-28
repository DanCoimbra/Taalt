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
     * Retorna uma célula do tabuleiro dada sua posição.
     *
     * @param pos Posição da célula no tabuleiro.
     * @return Célula.
     */
    Cell getCell(Point pos);

    /**
     * Conecta um visualizador ao servidor, para que este possa notificá-lo de mudanças no jogo.
     *
     * @param gameScreen Visualizador do jogo.
     */
    void addGameViewer(IUpdateGameView gameScreen);

    /**
     * Atualiza todos os visualizadores de uma possível mudança na célula especificada.
     *
     * @param pos Coordenadas da célula.
     */
    void cellUpdate(Point pos);
}
