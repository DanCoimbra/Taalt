package gameserver;

/**
 * Interface do componente Builder.
 */
public interface IGameBuilder {
    /**
     * Constrói um novo objeto Game baseado nas opções de jogo fornecidas.
     *
     * @param options Opções de jogo
     * @return Objeto Game
     */
    IGame buildGame(Options options);
}
