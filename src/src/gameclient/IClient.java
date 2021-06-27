package gameclient;

import gameserver.IGame;

/**
 * Interface genérica para um cliente do jogo Taalt!
 */
public interface IClient {

    /**
     * Troca a visualização para o Menu Principal.
     */
    void showMainMenu();

    /**
     * Troca a visualização para a Tela do Jogo. Lança a excessão () se uma partida válida não está em andamento.
     */
    void showGameScreen();

    /**
     * Conecta um componente IGame à janela/visualização do jogo (GameScreen) e a configura de acordo.
     *
     * @param gameServer Componente IGame através do qual o GameScreen se comunica para visualizar e controlar o jogo.
     */
    void setupGameScreen(IGame gameServer);
}
