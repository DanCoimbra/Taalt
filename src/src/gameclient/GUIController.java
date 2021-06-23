package gameclient;

import gameserver.Game;
import gameserver.IGame;
import gameserver.Options;

import java.awt.*;
import javax.swing.*;

// TODO: Consider using JFrame.pack() method to dynamically adjust screen size.
/**
 *  Janela principal e controlador principal da interface gráfica. Alterna entre componentes
 *  correspondentes ao menu principal (GUIMainMenu) e à visualização do jogo (GUIGameScreen).
 *  Recebe objetos Options de GUIMainMenu e os envia para GUIGameScreen.
 */
public class GUIController extends JFrame implements IOptionsReceiver {
    public final static int WIDTH = 500;
    public final static int HEIGHT = 600;
    GUIMainMenu menuScreen;
    GUIGameScreen gameScreen;
    Game gameServer;
    Options options;

    public GUIController() throws InterruptedException {
        // Inicializa a janela.
        this.setTitle("Taalt! – The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);

        // Adiciona um ícone (favicon) à janela.
        ImageIcon logoIcon = new ImageIcon("assets/logo32x32.png");
        this.setIconImage(logoIcon.getImage());

        // Inicializa o menu principal, pelo qual o jogador pode inserir opções e iniciar o jogo.
        this.menuScreen = new GUIMainMenu();
        this.menuScreen.addOptionsReceiver(this);
        this.getContentPane().add(this.menuScreen);

        this.setVisible(true);
    }

    // Implementa método de IOptionsReceiver.
    @Override
    public void receiveOptions(Options options) {
        this.options = options;
        this.start();
    }

    // Comandos para iniciar e terminar o jogo.
    public void start() {
        this.gameServer = new Game(this.options);
        this.gameScreen = new GUIGameScreen(WIDTH, HEIGHT, options.getM(), options.getN(), this);

        // Conecta o cliente ao servidor e, ademais, conecta as células do cliente às células dos servidor com sync().
        this.gameServer.addOutputReceiver(this.gameScreen);
        this.gameScreen.addInputReceiver(this.gameServer);
        this.gameScreen.sync(gameServer);

        // Devolve o estado inicial do jogo, antes de qualquer input de jogador.
        this.gameServer.fireOutput();

        this.menuScreen.setVisible(false);
        this.getContentPane().add(this.gameScreen);
    }

    public void end() {
        this.getContentPane().remove(this.gameScreen);
        this.gameScreen = null;
        this.gameServer = null;
        this.menuScreen.setVisible(true);
    }
}

