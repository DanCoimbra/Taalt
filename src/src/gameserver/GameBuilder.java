package gameserver;

/**
 *
 */
public class GameBuilder implements IGameBuilder {

    public IGame buildGame(Options options) {

        IGame newGame = new Game();
        newGame.setGameOptions(options);

        // TODO: Construir tabuleiro aqui
        // codigo

        return newGame;

    }

}
