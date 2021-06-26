package gameclient;

import gameserver.IGame;

/**
 * Agrega todas as interfaces oferecidas pela janela de visualização da partida.
 */
public interface IGameScreen extends IUpdateGameView {

    /**
     * Obtém o estado inicial do jogo e configura a visualização do tabuleiro levando em conta as configurações do jogo.
     *
     * @param gameServer Objeto de controle do jogo.
     */
    void setupGame(IGame gameServer);
}
