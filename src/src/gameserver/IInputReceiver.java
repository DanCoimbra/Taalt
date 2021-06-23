package gameserver;

/**
 *  Interface para receptores de objetos Input, que contém nada mais do que
 *  coordenadas de tabuleiro, nas quais se deseja inserir uma peça.
 */
public interface IInputReceiver {
    public void receiveInput(Input input);
}
