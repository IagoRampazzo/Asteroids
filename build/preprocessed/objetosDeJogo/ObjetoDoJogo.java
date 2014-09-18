/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosDeJogo;

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author u12175
 */
public abstract class ObjetoDoJogo {

    private static final int VELOCIDADE_MAXIMA = 20;
    private int posicaoX;
    private int posicaoY;
    private int raio;       //raio é utilizado para tratar colisões
    private double angulo; //angulo é em radianos
    private int velocidade;
    private String caminhoImagem;
    protected Sprite spt;
    private boolean movendo;
    protected int LARGURA;
    protected int ALTURA;

    public ObjetoDoJogo(int posicaoX, int posicaoY, int raio, double angulo, String caminhoImagem) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.raio = raio;
        this.angulo = angulo;
        this.velocidade = 0;
        this.caminhoImagem = caminhoImagem;
        this.movendo = false;
        System.out.println("CAMINHO " + caminhoImagem);

        try {
            this.spt = new Sprite(Image.createImage(caminhoImagem));
            this.spt.move(posicaoX, posicaoY);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setarPosicaoAleatoria(int maxX, int maxY) {   //gera um x e um Y aleatório
        posicaoX = new Random().nextInt(maxX);
        posicaoY = new Random().nextInt(maxY);
    }

    public void girar(double quantoGira) {       // Gira o objeto no sentido anti-horário, para girá-lo no sentido horário basta
        angulo += quantoGira;                   //colocar um valor negativo
        while (angulo >= Math.PI * 2) {
            angulo -= Math.PI * 2;                 //0 -> 2*PI são os valores possíveis
        }
        while (angulo < 0) {
            angulo += Math.PI * 2;
        }
    }

    public void acelerar(int quantoAcelera) {
        velocidade += quantoAcelera;
        if (velocidade > VELOCIDADE_MAXIMA) {
            velocidade = VELOCIDADE_MAXIMA;
        }
    }

    public void desenhar(Graphics g) {
        Image img = null;
        try {
            img = Image.createImage(caminhoImagem);
            g.drawImage(img, posicaoX, posicaoY, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        spt.paint(g);
    }

    public int getRaio() {
        return raio;
    }

    public int getX() {
        return posicaoX;
    }

    public int getY() {
        return posicaoY;
    }

    public double getAngulo() {
        return angulo;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public void setY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public boolean isMovendo() {
        return movendo;
    }

    public void setMovendo(boolean movendo) {
        this.movendo = movendo;
    }

    public Sprite getSpt() {
        return spt;
    }

    public void setSpt(Sprite spt) {
        this.spt = spt;
    }

    public int getLARGURA() {
        return LARGURA;
    }

    public void setLARGURA(int LARGURA) {
        this.LARGURA = LARGURA;
    }

    public int getALTURA() {
        return ALTURA;
    }

    public void setALTURA(int ALTURA) {
        this.ALTURA = ALTURA;
    }

}
