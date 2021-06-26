package gameclient;

import java.awt.*;

/**
 * Interface para informar mudanças à interface gráfica do jogo.
 */
public interface IUpdateGameView {

    /**
     * Atualiza o painel com informações do status do jogo, como vez de jogar, se o modo gravidade foi habilitado, etc...
     */
    void updateGameStatus();

    /**
     * Sinaliza à célula da interface gráfica que a célula correspondente no servidor foi atualizada.
     *
     * @param cellCoordinates Coordenadas da célula no tabuleiro.
     */
    void updateCell(Point cellCoordinates);

}
