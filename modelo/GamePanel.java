package modelo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

  public static final int WIDTH = 1280;
  public static final int HEIGHT = 720;

  private GameThread gameLoop;
  private PlayManager pm;

  public GamePanel() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(Color.black);
    this.setLayout(null);

    this.addKeyListener(new ControleTeclado());
    this.setFocusable(true);

    pm = new PlayManager();
    pm.setGamePanel(this);
    startGame();
  }

  public void startGame() {
    if(gameLoop!=null){
      gameLoop.parar();
    }
    gameLoop = new GameThread();
    gameLoop.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if(pm.esperandoReiniciar)
      pm.drawRestart(g2);
    else    
      pm.draw(g2);
  }

  public void update() {
    pm.update();
  }

  // *************************************************** */
  class GameThread extends Thread {
    private int FPS = 120;
    private boolean quebrarLoop = false;

    public GameThread() {
      super();
      this.setDaemon(true);
    }

    public void run() {

      double interval = 1000000000 / FPS;
      double delta = 0;
      long lastTime = System.nanoTime();
      long currentTime;

      while (!quebrarLoop) {
        // System.out.println("Oii");
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / interval;
        lastTime = currentTime;

        if (delta >= 1) {
          update();
          repaint();
          delta--;
        }

      }

    }

    public void parar() {
      quebrarLoop = true;
    }
  }// ************************************** */

}