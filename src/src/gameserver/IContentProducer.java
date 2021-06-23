package gameserver;

/**
 *  Interface para produtores de conteúdos, correspondentes ao conteúdo de
 *  células no tabuleiro, codificados pelo ID do jogador, começando em 1.
 */
public interface IContentProducer {
    public void addContentReceiver(IContentReceiver contentReceiver);
    public void alertContentUpdate();
    public int sendContent();
}
