package modelo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleTeclado implements KeyListener {

  public static boolean upPressed, downPressed, leftPressed, rightPressed;
  public static boolean spaceTyped, cTyped, enterTyped=false;

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    if (code == KeyEvent.VK_UP) {
      upPressed = true;
    } else if (code == KeyEvent.VK_DOWN) {
      downPressed = true;
    } else if (code == KeyEvent.VK_LEFT) {
      leftPressed = true;
    } else if (code == KeyEvent.VK_RIGHT) {
      rightPressed = true;
    } else if (code == KeyEvent.VK_SPACE) {
      spaceTyped = true;
    } else if (code == KeyEvent.VK_C) {
      cTyped = true;
    } else if (code == KeyEvent.VK_ENTER){
      enterTyped = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    if (code == KeyEvent.VK_UP) {
      upPressed = false;
    } else if (code == KeyEvent.VK_DOWN) {
      downPressed = false;
    } else if (code == KeyEvent.VK_LEFT) {
      leftPressed = false;
    } else if (code == KeyEvent.VK_RIGHT) {
      rightPressed = false;
    } else if (code == KeyEvent.VK_SPACE) {
      spaceTyped = false;
    } else if (code == KeyEvent.VK_C){
      cTyped = false;
    } else if (code == KeyEvent.VK_ENTER){
      enterTyped = false;
    }
  }

}