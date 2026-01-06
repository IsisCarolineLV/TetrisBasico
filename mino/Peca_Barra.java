package mino;
//rotacao preguiçosa, deveria rodar tudo
import java.awt.Color;

public class Peca_Barra extends Peca{
  
  public Peca_Barra(){
    create(Color.cyan);
  }
  
  public void setXY(int x, int y){
    // 1
    // 0
    // 2 
    // 3
    b[0].x = x;
    b[0].y = y;

    b[1].x = b[0].x;
    b[1].y = b[0].y - Bloco.SIZE;

    b[2].x = b[0].x;
    b[2].y = b[0].y + Bloco.SIZE;

    b[3].x = b[0].x;
    b[3].y = b[0].y + 2*Bloco.SIZE;
  }

  public void updateXY(int direcao){
    switch (direcao) {
      case 1:
        b[1].x = b[0].x;
        b[1].y = b[0].y - Bloco.SIZE;

        b[2].x = b[0].x;
        b[2].y = b[0].y + Bloco.SIZE;

        b[3].x = b[0].x;
        b[3].y = b[0].y + 2*Bloco.SIZE;
        break;

      case 2:
        b[1].x = b[0].x - Bloco.SIZE;
        b[1].y = b[0].y;

        b[2].x = b[0].x + Bloco.SIZE;
        b[2].y = b[0].y;

        b[3].x = b[0].x + 2*Bloco.SIZE;
        b[3].y = b[0].y;
        break;

      case 3:
        b[1].x = b[0].x;
        b[1].y = b[0].y + Bloco.SIZE;

        b[2].x = b[0].x;
        b[2].y = b[0].y - Bloco.SIZE;

        b[3].x = b[0].x;
        b[3].y = b[0].y - 2*Bloco.SIZE;
        break;

      case 4:
        b[1].x = b[0].x + Bloco.SIZE;
        b[1].y = b[0].y;

        b[2].x = b[0].x - Bloco.SIZE;
        b[2].y = b[0].y;

        b[3].x = b[0].x - 2*Bloco.SIZE;
        b[3].y = b[0].y;
        break;
    }
  }

}
