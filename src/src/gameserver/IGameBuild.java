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
    void setGameOptions(Options options);

    // TODO: Método para settar o tabuleiro, que será construído pelo COMPONENTE GameBuilder

}
