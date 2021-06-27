package gameclient;

import java.awt.*;

/**
 * Interface para informar mudanças à interface gráfica do jogo.
 */
public interface IUpdateGameView {

    /**
     * Atualiza o painel com informações do estado atual do jogo, a exemplo do nome do jogador atual.
     * Se o estado atual do jogo houver terminado, a visualização do jogo também cessa.
     */
    void updateGameStatus();

    /**
     * Sinaliza à célula da interface gráfica que a célula correspondente no servidor foi atualizada.
     *
     * @param cellCoordinates Coordenadas da célula no tabuleiro.
     */
    void updateCell(Point cellCoordinates);

}
