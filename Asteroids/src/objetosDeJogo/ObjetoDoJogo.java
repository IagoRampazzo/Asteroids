/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosDeJogo;

 import java.util.Random;
/**
 *
 * @author u12175
 */
public class ObjetoDoJogo {
    private int posicaoX;
    private int posicaoY;
    private int raio;
    private double angulo; //angulo é em radianos

    public ObjetoDoJogo(int posicaoX, int posicaoY,int raio, double angulo) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.raio = raio;
        this.angulo = angulo;
    }
    
    public void gerarPosicaoAleatoria(int maxX,int maxY){
       int posicaoX = new Random().nextInt(maxX);
       int posicaoY = new Random().nextInt(maxY);
    }

    public void girar(double quantoGira){
        angulo += quantoGira;
        while (angulo>=Math.PI*2)
           angulo -= Math.PI*2;
        while (angulo<0)
            angulo += Math.PI*2;
    }

    public int getRaio() {
        return raio;
    }
    
    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
    
    
    
    
}
