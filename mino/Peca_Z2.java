package mino;

import java.awt.Color;

public class Peca_Z2 extends Peca{
  
  public Peca_Z2(){
    create(Color.red);
  }
  
  public void setXY(int x, int y){
    // 1 0
    //   2 3 
    b[0].x = x;
    b[0].y = y;

    b[1].x = b[0].x - Bloco.SIZE;
    b[1].y = b[0].y;

    b[2].x = b[0].x;
    b[2].y = b[0].y + Bloco.SIZE;

    b[3].x = b[0].x + Bloco.SIZE;
    b[3].y = b[0].y + Bloco.SIZE;
  }

  public void updateXY(int direcao){
    switch (direcao) {
      case 1:
        b[1].x = b[0].x - Bloco.SIZE;
        b[1].y = b[0].y;

        b[2].x = b[0].x;
        b[2].y = b[0].y + Bloco.SIZE;

        b[3].x = b[0].x + Bloco.SIZE;
        b[3].y = b[0].y + Bloco.SIZE;
        break;

      case 2:
        b[1].x = b[0].x ;
        b[1].y = b[0].y + Bloco.SIZE;

        b[2].x = b[0].x + Bloco.SIZE;
        b[2].y = b[0].y;

        b[3].x = b[0].x + Bloco.SIZE;
        b[3].y = b[0].y - Bloco.SIZE;
        break;

      case 3:
        b[1].x = b[0].x + Bloco.SIZE;
        b[1].y = b[0].y;

        b[2].x = b[0].x;
        b[2].y = b[0].y - Bloco.SIZE;

        b[3].x = b[0].x - Bloco.SIZE;
        b[3].y = b[0].y - Bloco.SIZE;
        break;

      case 4:
        b[1].x = b[0].x;
        b[1].y = b[0].y - Bloco.SIZE;

        b[2].x = b[0].x - Bloco.SIZE;
        b[2].y = b[0].y;

        b[3].x = b[0].x - Bloco.SIZE;
        b[3].y = b[0].y + Bloco.SIZE;
        break;
    }
  }

}
