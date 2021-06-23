package gameserver;

/**
 *  Interface para produtores de objetos Input, que contém nada mais do que
 *  coordenadas de tabuleiro, nas quais se deseja inserir uma peça.
 */
public interface IInputProducer {
    public void addInputReceiver(IInputReceiver inputReceiver);
    public void fireInput(Input input);
}
