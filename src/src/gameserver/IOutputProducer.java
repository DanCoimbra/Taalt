package gameserver;

/**
 *  Interface para produtores de objetos Output, correspondentes
 *  a informações do estado atual do jogo.
 */
public interface IOutputProducer {
    public void addOutputReceiver(IOutputReceiver outputReceiver);
    public void clearOutputReceiverList();
    public void fireOutput();
}
