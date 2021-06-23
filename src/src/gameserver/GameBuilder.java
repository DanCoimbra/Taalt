package gameserver;

public abstract class GameBuilder {
    public static IGame buildGame(Options options) {
        return new Game(options);
    }
}
