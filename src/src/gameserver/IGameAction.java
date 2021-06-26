package gameserver;

import java.awt.*;

/**
 *  Interface através da qual o servidor do jogo aceita comandos.
 */
public interface IGameAction {

    /**
     * Coloca uma peça nas coordenadas especificadas do tabuleiro. A peça pertencerá ao jogador que estiver em sua vez
     * de jogar.
     *
     * @param coordinates Coordenadas no tabuleiro.
     */
    void placePiece(Point coordinates);

    /**
     * Altera o modo de gravidade do jogo. A gravidade pode estar no modo desligado (DISABLED) ou ligado em uma direção.
     */
    void setGravityMode(GravityMode gravityMode);
}
