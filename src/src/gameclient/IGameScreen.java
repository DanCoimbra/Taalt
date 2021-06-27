package gameclient;

import gameserver.IGame;

/**
 * Agrega todas as interfaces oferecidas pela janela de visualização da partida.
 */
public interface IGameScreen extends IUpdateGameView {

    /**
     * Conecta o componente Game (servidor) ao cliente, obtém o estado inicial do jogo e configura a visualização da
     * janela levando em conta as configurações do jogo.
     *
     * @param gameServer Objeto de controle do jogo.
     */
    void setupGameScreen(IGame gameServer);
}
