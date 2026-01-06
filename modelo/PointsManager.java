package modelo;

public class PointsManager {
  private int totalPoints = 0;
  private int contCombo=0, contComboAux=0;
  private int mult=0;
  private int level = 1;
  private int velocidadeAnt=0;

  public int getTotalPoints(){
    totalPoints += 50*contCombo*level + mult*level;
    mult=0;
    contCombo = 0;
    return totalPoints;
  }

  public void maisCombo(boolean continuar) {
    if(continuar){
      contComboAux++;
    }else{
      contCombo = contComboAux;
      contComboAux=0;
    }
  }

  public void setMult(String tipo) {
    if(tipo.equals("single")) mult=100;
    else if(tipo.equals("double")) mult=300;
    else if(tipo.equals("triple")) mult=500;
    else if(tipo.equals("tetris")) mult=800;
    else if(tipo.equals("sigle+clear")) mult = 800;
    else if(tipo.equals("double+clear")) mult = 1200;
    else if(tipo.equals("triple+clear")) mult = 1800;
    else if(tipo.equals("tetris+clear")) mult = 2000;
  }

  public void setLevel(long duracaoNs) {
    double duracaoMs = duracaoNs / 1000000.0;
    if(duracaoMs<10000) {PlayManager.setDropInterval(70);}
    else if(duracaoMs<25000) {PlayManager.setDropInterval(60);}
    else if(duracaoMs<50000) {PlayManager.setDropInterval(50); level = 2;}
    else if(duracaoMs<100000) {PlayManager.setDropInterval(40);}
    else if(duracaoMs<150000) {PlayManager.setDropInterval(30); level = 3;}
    else if(duracaoMs<200000) {PlayManager.setDropInterval(25);}
    else if(duracaoMs<300000) {PlayManager.setDropInterval(20); level = 4;}
    else if(duracaoMs<400000) {PlayManager.setDropInterval(15);}
    else {PlayManager.setDropInterval(10); level=5;}
  
  }

  public int getContCombo() {
    return contCombo;
  }
  public int getMult() {
    return mult;
  }
  public int getLevel() {
    return level;
  }

}
