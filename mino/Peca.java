package mino;

import java.awt.Color;
import java.awt.Graphics2D;

import modelo.*;

public abstract class Peca {
  
  public Bloco[] b = new Bloco[4];
  protected Bloco[] tempB =  new Bloco[4];
  private int contDropador = 0;
  private int direcaoAtual = 0;
  public boolean ativa=true;
  private PlayManager pm;
  private int lockDelay = 0;
  private static final int MAX_LOCK_DELAY = 50;
  private int downCooldown=40;

  public void create( Color c){
    for(int i=0; i<4; i++){
      b[i] = new Bloco(c);
      tempB[i] = new Bloco(c);
    }
  }

  public void setPlayManager( PlayManager pm){
    this.pm = pm;
  }

  public abstract void setXY(int x, int y);
  public abstract void updateXY(int direcao);
  
  public void update(){
    if (colidindoChao()) {
      lockDelay++; // encostou no chão
      if (lockDelay >= MAX_LOCK_DELAY) {
        ativa = false; // AGORA trava
        downCooldown = 40;
        return;
      }
    }

    if (downCooldown > 0) {
      downCooldown--;
    }

    if(ControleTeclado.upPressed){
      direcaoAtual = (direcaoAtual)%4+1;
      updateXY(5-direcaoAtual);
      ControleTeclado.upPressed=false;
    }
    else if(ControleTeclado.downPressed && downCooldown==0){
      if(!colidindoChao()){
        for(int i=0; i<4; i++){
          b[i].y += Bloco.SIZE;
        }
        contDropador = 0;
      } 
    }
    else if(ControleTeclado.leftPressed){
      if(!colidindoEsq() && !colidindoBlocosLateral()){
        for(int i=0; i<4; i++){
          b[i].x -= Bloco.SIZE;
        }
        lockDelay=0;
      }
      ControleTeclado.leftPressed = false;
      
    }
    else if(ControleTeclado.rightPressed){
      if(!colidindoDir() && !colidindoBlocosLateral()){
        for(int i=0; i<4; i++){
          b[i].x += Bloco.SIZE;
        }
        lockDelay=0;
      }
      ControleTeclado.rightPressed = false;

    }

    contDropador++;
    if(contDropador == PlayManager.dropInterval & !(colidindoChao() || colidindoBlocos())){
      for(int i=0; i<4; i++){
        b[i].y += Bloco.SIZE;
      }
      contDropador = 0;
    }

    if(colidindoTeto()){  //resposta esta no colidindoBlocos
      System.out.println("Perdeu");
      pm.perdeu();
    }
  }

  public void draw(Graphics2D g2d){
    g2d.setColor(b[0].c);
    for(int i=0; i<4; i++){
      if(b[i].y>PlayManager.top_y-30)
        b[i].draw(g2d);
    }
  }

  public void drawNaFila(Graphics2D g2d, int posicao){
    g2d.setColor(b[0].c);
    int acressimo_y=0;
    int acressimo_x = 390;
    if(posicao==0){
      acressimo_y = 100;
    }else if(posicao == 1){
      acressimo_y = 220;
    }else{
      acressimo_y = 340;
    }
    for(Bloco bb: b){
      Bloco aux = bb.clone();
      aux.x += acressimo_x;
      aux.y += acressimo_y;
      aux.draw(g2d);
    } 
  }

  public boolean colidindoChao(){
    for(int i=0; i<4; i++){
      if(b[i].y >= PlayManager.bottom_y-30){
        return true;
      }
    }
    return false;
  }

  public boolean colidindoDir(){
    for(int i=0; i<4; i++){
      if(b[i].x >= PlayManager.right_x-30){
        return true;
      }
    }
    return false;
  }

  public boolean colidindoEsq(){
    for(int i=0; i<4; i++){
      if(b[i].x <= PlayManager.left_x){
        return true;
      }
    }
    return false;
  }

  public boolean colidindoBlocos(){
    for(Bloco a: PlayManager.blocosCaidos){
      for(int i=0; i<4; i++){
        if(a.x==b[i].x && a.y-30==b[i].y){
          System.out.println("Bateu");
          ativa=false;
          return true;
        }
      }
    }
    return false;
  }

  public boolean colidindoBlocosLateral(){
    for(Bloco a: PlayManager.blocosCaidos){
      for(int i=0; i<4; i++){
        if(a.x==b[i].x && a.y-30==b[i].y
          || (a.x-30==b[i].x || a.x+30==b[i].x) && a.y==b[i].y){
          System.out.println("Bateu na lateral");
          return true;
        }
      }
    }
    return false;
  }

  public boolean colidindoTeto(){
    if(!ativa){
      for(int i=0; i<4; i++){
        if(b[i].y<PlayManager.top_y)
          return true;
      }
      return false;
    }
    return false;
  }
}
