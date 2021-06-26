package gameclient;

import gameserver.IGame;

import java.awt.*;
import javax.swing.*;

// TODO: Consider using JFrame.pack() method to dynamically adjust screen size.

/**
 *  Janela principal e gerenciador da interface gráfica. Alterna entre componentes correspondentes ao menu principal
 *  (GUIMainMenu) e à visualização do jogo (GUIGameScreen). Também recebe um  componente IGame de GUIMainMenu e o envia
 *  para GUIGameScreen, que será responsável por visualizar e controlar a partida.
 */
public class GUIController extends JFrame implements IClient {
    // Janelas secundárias (Menu e Janela do Jogo)
    private GUIMainMenu menuScreen;     // JScrollPane que contém uma lista de botões, nos quais se pode inserir opções e iniciar o jogo.
    private GUIGameScreen gameScreen;   // JPanel que mostra a visualização de um jogo.

    // Dimensões da janela principal
    private final Dimension windowDimensions = new Dimension(600, 600);

    private final String gameTitle = "Taalt! – The Game";
    private final String logoIconPath = "assets/logo32x32.png";

    public GUIController() {

        // Inicializa a janela principal (o próprio GUIController).
        this.setTitle(gameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(windowDimensions);
        this.setResizable(false);

        // Adiciona um ícone ao título da janela.
        ImageIcon logoIcon = new ImageIcon(logoIconPath);
        this.setIconImage(logoIcon.getImage());

        // Inicializa o menu principal e a janela do jogo.
        this.menuScreen = new GUIMainMenu(this);
        this.gameScreen = new GUIGameScreen(this);
        this.setVisible(true);
    }

    @Override
    public void showMainMenu() {
        try {
            this.getContentPane().remove(this.gameScreen);
            this.getContentPane().add(this.menuScreen);
            this.menuScreen.setVisible(true);
            this.revalidate();
        } catch (NullPointerException e) {
            // Deu problema ao remover o gameScreen ou ao adicionar menuScreen
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void showGameScreen() {
        try {
            this.getContentPane().remove(this.menuScreen);
            this.getContentPane().add(this.gameScreen);
            this.gameScreen.setVisible(true);
            this.revalidate();
        } catch (NullPointerException e) {
            // Deu problema ao remover o menuScreen ou ao adicionar gameScreen
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void setupGameScreen(IGame gameServer) {
        this.gameScreen.setupGame(gameServer);      // Inicializa o tabuleiro do visualizador
        gameServer.addGameViewer(this.gameScreen);  // Conecta o cliente ao servidor
    }

    Dimension getWindowDimensions() {
        return windowDimensions;
    }
}
