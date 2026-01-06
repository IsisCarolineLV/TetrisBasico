package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bloco extends Rectangle{
  public int x, y;
  public static final int SIZE=30;
  public Color c;

  public Bloco(Color c){
    this.c = c;
  }

  public void draw (Graphics2D g2d){
    g2d.setColor(c);
    int margem = 1;
    g2d.fillRect(x+margem, y+margem, Bloco.SIZE-(margem*2), Bloco.SIZE-(margem*2));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Bloco)) return false;

    Bloco b = (Bloco) o;
    return this.x == b.x && this.y == b.y;
  }
}
