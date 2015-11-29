import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
    private Pong game;
    //private Ball ball;
    //private Racket player1, player2;
    private int score1, score2;

    public GamePanel(Pong game) {
        setBackground(Color.WHITE);
        this.game = game;
        //ball = new Ball(game);
        //player1 = new Racket(game, KeyEvent.VK_UP, KeyEvent.VK_DOWN, game.getWidth() - 36);
        //player2 = new Racket(game, KeyEvent.VK_W, KeyEvent.VK_S, 20);
        //Timer timer = new Timer(5, this);
        //timer.start();
        //addKeyListener(this);
        setFocusable(true);
    }
    
    
    
}