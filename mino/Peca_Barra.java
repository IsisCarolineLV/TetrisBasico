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

    b[1].x = b[0].x - Bloco.SIZE;
    b[1].y = b[0].y;

    b[2].x = b[0].x + Bloco.SIZE;
    b[2].y = b[0].y;

    b[3].x = b[0].x + 2*Bloco.SIZE;
    b[3].y = b[0].y;
    
  }

  public void updateXY(int direcao){
    Peca_Barra p = clone();

    p.girar(direcao,0);
    if(p.colidindoEsq()){ 
      girar(direcao, Bloco.SIZE);
    }else if (p.colidindoDir()){ 
      girar(direcao, -Bloco.SIZE);
    }else if (p.colidindoBlocosLateral() || p.colidindoChao()){
      return;
    }
    girar(direcao, 0);
  }

  public void girar(int direcao, int desloc){
    b[0].x +=desloc;
    switch (direcao) {
      case 1:
        b[1].x = b[0].x - Bloco.SIZE;
        b[1].y = b[0].y;

        b[2].x = b[0].x + Bloco.SIZE;
        b[2].y = b[0].y;

        b[3].x = b[0].x + 2*Bloco.SIZE;
        b[3].y = b[0].y;
        break;

      case 2:
        b[1].x = b[0].x;
        b[1].y = b[0].y + Bloco.SIZE;

        b[2].x = b[0].x;
        b[2].y = b[0].y - Bloco.SIZE;

        b[3].x = b[0].x;
        b[3].y = b[0].y - 2*Bloco.SIZE;
        break;

      case 3:
        b[1].x = b[0].x + Bloco.SIZE;
        b[1].y = b[0].y;

        b[2].x = b[0].x - Bloco.SIZE;
        b[2].y = b[0].y;

        b[3].x = b[0].x - 2*Bloco.SIZE;
        b[3].y = b[0].y;
        break;

      case 4:
        b[1].x = b[0].x;
        b[1].y = b[0].y - Bloco.SIZE;

        b[2].x = b[0].x;
        b[2].y = b[0].y + Bloco.SIZE;

        b[3].x = b[0].x;
        b[3].y = b[0].y + 2*Bloco.SIZE;
        break;
    }
  }

  public Peca_Barra clone(){
    Peca_Barra p = new Peca_Barra();
    p.setXY(this.b[0].x, this.b[0].y);
    p.setPlayManager(super.pm);
    return p;
  }

}
