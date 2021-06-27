package gameserver;

import java.awt.*;

/**
 *
 */
public class GameBuilder implements IGameBuilder {

    public IGame buildGame(Options options) {
        Game newGame = new Game();
        newGame.setGameStatus(options);

        Dimension boardDimension = new Dimension(options.getM(), options.getN());
        Board newBoard = new Board(newGame, options.getK(), options.getGravityMode(), boardDimension);
        newGame.setBoard(newBoard);

        return newGame;
    }

}
