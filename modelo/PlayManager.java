package modelo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import mino.*;

public class PlayManager {
  private final int WIDTH = 360;
  private final int HEIGHT = 600;
  public static int left_x, right_x, top_y, bottom_y; 
  public static final int dropInterval = 60; //a cada 60 frames desce a peca
  private Peca pecaAtual;
  private Queue<Peca> proximasPecas = new LinkedList<>();
  private Random random = new Random();
  final int PECA_INICIO_X, PECA_INICIO_Y;
  public static ArrayList<Bloco> blocosCaidos = new ArrayList<>();
  private GamePanel gp;
  public boolean esperandoReiniciar = false;

  public PlayManager(){
    left_x = (GamePanel.WIDTH/2) - (WIDTH/2);
    right_x = left_x + WIDTH;
    top_y = 50;
    bottom_y = top_y + HEIGHT;

    PECA_INICIO_X = left_x + (WIDTH/2) - Bloco.SIZE;
    PECA_INICIO_Y = top_y - 3*Bloco.SIZE;

    definirPecasIniciais();
  }

  public void setGamePanel( GamePanel gp){
    this.gp = gp;
  }

  public void definirPecasIniciais(){
    pecaAtual = sorteiaPeca(random.nextInt(7));
    pecaAtual.setXY(PECA_INICIO_X, PECA_INICIO_Y);
    pecaAtual.setPlayManager(this);

    for(int i=0; i<3; i++){
      Peca aux = sorteiaPeca(random.nextInt(7));
      aux.setXY(PECA_INICIO_X, PECA_INICIO_Y+60);
      proximasPecas.add(aux);
    }
  }

  public void update(){
    if(esperandoReiniciar){
      if(ControleTeclado.enterTyped){
        restart();
        System.out.println("Reiniciou");
      }
    }else{
      pecaAtual.update();
      if(!pecaAtual.ativa){
        blocosCaidos.addAll(Arrays.asList(pecaAtual.b));
        checkLinhaCompleta();
        pecaAtual = proximasPecas.poll();
        if(pecaAtual!=null)
          pecaAtual.setPlayManager(this);
        Peca aux = sorteiaPeca(random.nextInt(7));
        aux.setXY(PECA_INICIO_X, PECA_INICIO_Y+60);
        proximasPecas.add(aux);
      }
    }
    
  }

  public void drawRestart(Graphics2D g2d){
    for(Bloco b: blocosCaidos){
      b.c = Color.gray;
    }
    draw(g2d);
    
  }

  //desenha a borda da tela do jogo
  public void draw(Graphics2D g2d){
    g2d.setColor(Color.white);
    g2d.setStroke(new BasicStroke(4f));
    g2d.drawRect(left_x-4, top_y -4, WIDTH+8, HEIGHT+8);

    int x = right_x + 100;
    int y = top_y ;
    g2d.drawRect(x, y, 200, HEIGHT-180);

    for(Bloco b: blocosCaidos){
      b.draw(g2d);
    }

    g2d.setColor(Color.white);
    if(esperandoReiniciar){
      g2d.setFont(new Font("Arial", Font.PLAIN, 50));
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      g2d.drawString("GAME OVER",490,345);
      g2d.setFont(new Font("Arial", Font.PLAIN, 30));
      g2d.drawString("pressione enter",540,370);
      
    }else{
      g2d.setFont(new Font("Arial", Font.PLAIN, 20));
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      g2d.drawString("PEÇA SEGUINTE:", x+20, y+30);
      if(pecaAtual!=null){
        pecaAtual.draw(g2d);
      }

      int cont=0;
      for(Peca p: proximasPecas){
        p.drawNaFila(g2d, cont++);
      }
    }
  }

  public Peca sorteiaPeca(int num){
    switch(num){
      case 0: return new Peca_Barra();
      case 1: return new Peca_L1();
      case 2: return new Peca_L2();
      case 3: return new Peca_Quadrado();
      case 4: return new Peca_T();
      case 5: return new Peca_Z1();
      case 6: return new Peca_Z2();
      default: return null;
    }
  }

  public void perdeu(){
    esperandoReiniciar = true;
  }

  public void restart(){
    blocosCaidos.clear();
    proximasPecas.clear();
    definirPecasIniciais();
    gp.startGame(); //chamar restart
    esperandoReiniciar = false;
  }

  public void checkLinhaCompleta(){
    int colunas = WIDTH / Bloco.SIZE;
    for (int y = bottom_y - Bloco.SIZE; y >= top_y; y -= Bloco.SIZE) {
      ArrayList<Bloco> linha = new ArrayList<>();
      for (Bloco b : blocosCaidos) {
          if (b.y == y) {
              linha.add(b);
          }
      }
      if (linha.size() == colunas) {
        // remove linha
        blocosCaidos.removeAll(linha);

        // desce tudo que esta acima
        for (Bloco b : blocosCaidos) {
            if (b.y < y) {
                b.y += Bloco.SIZE;
            }
        }
        y += Bloco.SIZE;
      } 
    }
  }

}
