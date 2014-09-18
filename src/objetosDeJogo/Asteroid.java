/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosDeJogo;

import Visual.Tela;
import java.util.Random;

/**
 *
 * @author u12175
 */
public class Asteroid extends ObjetoDoJogo {

    private static String IMAGEM_ASTEROID = "/1.png";
    private static final int[] QUANTOS_FILHOS_GERA = {2, 2, 0};    //1grande=>2 médios;1médio=>2 pequenos;
    private int tipoAsteroid; //0 = MAIOR, 1 = Médio, 2 = menor
    private boolean vivo;

    private int fatorX;
    private int fatorY;

    public Asteroid(int tipo, int posicaoX, int posicaoY, int raio, double angulo) {
        super(posicaoX, posicaoY, raio, angulo, IMAGEM_ASTEROID);
        IMAGEM_ASTEROID = "/" + tipo + ".png";
        System.out.println(IMAGEM_ASTEROID);
        this.tipoAsteroid = tipo;
        this.vivo = true;
        this.setMovendo(true);
        this.fatorX = new Random().nextInt(3) * 3 - 1;
        this.fatorY = new Random().nextInt(3) * 3 - 1;
        System.out.println("FatorX " + this.fatorX);
        System.out.println("FatorY " + this.fatorY);
        switch (tipo) {
            case 0:
                this.LARGURA = 66;
                this.ALTURA = 66;
                break;
            case 1:
                this.LARGURA = 36;
                this.ALTURA = 36;
                break;
            case 2:
                this.LARGURA = 18;
                this.ALTURA = 18;
                break;
        }
    }

    /**
     *
     * @return retorna os filhos do Asteroid, ou seja, os novos que irão surgir
     */
    public Asteroid[] morrer() {
        vivo = false;
        int quantoGera = QUANTOS_FILHOS_GERA[tipoAsteroid]; //quantos asteroids irao gerar caso seja atingido
        int tipoFilho = tipoAsteroid + 1;
        Asteroid[] filhos = new Asteroid[quantoGera];
        double anguloAux = 30;
        if (tipoFilho < 2) {
            for (int i = 0; i < quantoGera; i++, anguloAux *= -1) {
                Asteroid novo = new Asteroid(tipoFilho, getX(), getY(), getRaio() / 2, getAngulo() + anguloAux);
                filhos[i] = novo;
            }
        }
        return filhos;
    }

    public void atualizar() {
        this.setX(getX() + fatorX);
        this.setY(getY() + fatorY);
        
        this.spt.setPosition(getX(), getY());
        System.out.println("X E Y AST= " + this.spt.getX() + " / " + this.spt.getY());

        if (this.spt.getX() + getLARGURA() < 0) {
            this.spt.setPosition(Tela.largura, this.spt.getY());
            
        }
        if (this.spt.getX() > Tela.largura) {
            this.spt.setPosition(0, this.spt.getY());
        }

        if (this.spt.getY() + getALTURA() < 0) {
            this.spt.setPosition(this.spt.getX(), Tela.altura);
            
        }
        if (this.spt.getY() > Tela.altura) {
            this.spt.setPosition(this.spt.getX(), 0);
        }
//         
//         this.spt.move(getX(), getY());
//         System.out.println("X = " + getX());
//         System.out.println("Y = " + getY());
        this.setX(getSpt().getX());
        this.setY(getSpt().getY());
        //System.out.println("X E Y AST NOVO= " + this.spt.getX() + " / " + this.spt.getY());
    }

    public boolean isVivo() {
        return vivo;
    }

}
