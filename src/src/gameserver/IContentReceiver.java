package gameserver;

/**
 *  Interface para receptores de conteúdos, correspondentes ao conteúdo de
 *  células no tabuleiro, codificados pelo ID do jogador, começando em 1.
 */
public interface IContentReceiver {
    public void noticeContentUpdate(IContentProducer source);
}
