package gameclient;

import gameserver.IGame;

import java.awt.*;
import javax.swing.*;

/**
 *  Janela principal e gerenciador da interface gráfica. Alterna entre componentes correspondentes ao menu principal
 *  (GUIMainMenu) e à visualização do jogo (GUIGameScreen). Também recebe um componente IGame de GUIMainMenu, e o envia
 *  para GUIGameScreen, que será responsável por visualizar e controlar o componente IGame (correspondente à partida).
 */
public class GUIController extends JFrame implements IClient {
    // Janelas secundárias (Menu e Janela do Jogo)
    private final GUIMainMenu menuScreen;     // JScrollPane que contém os campos de configurações e botão Start.
    private final GUIGameScreen gameScreen;   // JPanel que mostra a visualização de um jogo.

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

        // Inicializa a janela do jogo.
        this.gameScreen = new GUIGameScreen(this);
        this.getContentPane().add(gameScreen);
        this.gameScreen.setVisible(false);

        // Inicializa o menu principal.
        this.menuScreen = new GUIMainMenu(this);
        this.getContentPane().add(menuScreen);
        this.gameScreen.setVisible(false);

        // Mostra janela mestra.
        this.setVisible(true);
    }

    @Override
    public void showMainMenu() {
        this.menuScreen.setVisible(true);
        this.gameScreen.setVisible(false);
        this.revalidate();
    }

    @Override
    public void showGameScreen() {
        // TODO: Se não há partida em andamento, lançar uma excessão.
        this.menuScreen.setVisible(false);
        this.gameScreen.setVisible(true);
        this.revalidate();
    }

    @Override
    public void setupGameScreen(IGame gameServer) {
        this.gameScreen.setupGameScreen(gameServer);  // Inicializa o tabuleiro do cliente GUIGameScreen
        gameServer.addGameViewer(this.gameScreen);    // Conecta o cliente GUIGameScreen ao servidor IGame
    }

    Dimension getWindowDimensions() {
        return windowDimensions;
    }
}
