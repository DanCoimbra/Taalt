package gameserver;

/**
 *  Interface para recipientes de objetos Output, correspondentes
 *  a informações do estado atual do jogo.
 */
public interface IOutputReceiver {
    public void receiveOutput(Output output);
}
