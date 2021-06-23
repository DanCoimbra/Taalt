package gameclient;

import gameserver.Options;

/**
 *  Interface para recipientes de objetos Options, contendo opções de jogo
 *  selecionadas por um usuário.
 */
public interface IOptionsReceiver {
    public void receiveOptions(Options options);
}
