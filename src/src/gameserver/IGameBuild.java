package gameserver;

/**
 * Responsável por criar partidas, configurar os parâmetros iniciais do jogo e inicializar tabuleiro.
 */
public interface IGameBuild {
    /**
     * Constrói uma nova partida a partir do parâmetros de jogo especificados.
     *
     * @param options Objeto contendo todas as opções de jogo.
     */
    void setGameStatus(Options options);

    /**
     * Conecta o tabuleiro ao jogo.
     * @param board Objeto do tabuleiro.
     */
    void setBoard(Board board);
}
