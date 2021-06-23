package gameclient;

import gameserver.Options;

/**
 * Interface para produtores de objetos Options, contendo opções de jogo
 * selecionadas por um usuário.
 */

public interface IOptionsProducer {
    public void addOptionsReceiver(IOptionsReceiver optionsReceiver);
    public void fireOptions(Options options);
}
