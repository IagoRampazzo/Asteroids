/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetosDeJogo;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author u12175
 */
public class Asteroid extends ObjetoDoJogo{
    
    private static String IMAGEM_ASTEROID = "images/";
    private static int[] QUANTOS_FILHOS_GERA = {2,2,0};    //1grande=>2 médios;1médio=>2 pequenos;
    private int tipoAsteroid; //0 = MAIOR, 1 = Médio, 2 = menor
    private boolean vivo;
    
    public Asteroid(int tipo,int posicaoX, int posicaoY, int raio, double angulo) {
        super(posicaoX, posicaoY, raio, angulo,IMAGEM_ASTEROID);
        IMAGEM_ASTEROID += tipo + ".png";
        this.tipoAsteroid = tipo;
        this.vivo = true;
    }
    
    /**
     * 
     * @return retorna os filhos do Asteroid, ou seja, os novos que irão surgir
     */
    public Asteroid[] morrer(){
        vivo = false;
        int quantoGera = QUANTOS_FILHOS_GERA[tipoAsteroid]; //quantos asteroids irao gerar caso seja atingido
        int tipoFilho=tipoAsteroid+1;
        Asteroid[] filhos = new Asteroid[quantoGera];
        double anguloAux = 30;
        for (int i=0;i<quantoGera;i++,anguloAux*=-1){
            Asteroid novo = new Asteroid(tipoFilho,getX(),getY(),getRaio()/2,getAngulo()+anguloAux);
            filhos[i] = novo;
        }
        return filhos;
    }
    
}
