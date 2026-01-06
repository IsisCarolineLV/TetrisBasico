package mino;

import java.awt.Color;

public class Peca_Quadrado extends Peca{

  public Peca_Quadrado(){
    create(Color.yellow);
  }
  
  public void setXY(int x, int y){
    //   1 2
    //   0 3 
    b[0].x = x;
    b[0].y = y;

    b[1].x = b[0].x;
    b[1].y = b[0].y - Bloco.SIZE;

    b[2].x = b[0].x + Bloco.SIZE;
    b[2].y = b[0].y - Bloco.SIZE;

    b[3].x = b[0].x + Bloco.SIZE;
    b[3].y = b[0].y;
  }

  public void updateXY(int direcao){

  }

}
