/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosDeJogo;

import Visual.Tela;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author u12175
 */
public class Nave extends ObjetoDoJogo {

    // Constantes
    private static final int MAXIMO_DE_TIROS = 5;
    private static final int VIDA_INICIAL = 3;
    public static final double VELOCIDADE = 2;
    private static final String IMAGEM_NAVE = "/nave.png";

    private Tiro tiros[];
    private int qtosTiros;
    private int quantasVidas;
    private int orientacao;
    private final int[] setores = {0, 55, 110, 165, 220, 275, 325, 359};

    public Nave(int posicaoX, int posicaoY, int raio, double angulo) {
        super(posicaoX, posicaoY, raio, angulo, IMAGEM_NAVE);
        qtosTiros = 0;
        quantasVidas = VIDA_INICIAL;
        LARGURA = 18;
        ALTURA = 19;
        tiros = new Tiro[MAXIMO_DE_TIROS];          //haverá, no máximo 8 tiros (V 1.0)
        for (int i = 0; i < MAXIMO_DE_TIROS; i++) {
            tiros[i] = new Tiro(this.getX(), this.getY(), this.getAngulo(), 0);
            tiros[i].matarTiro();
        }

        this.orientacao = 0;
    }

    public void atirar() {
        if (qtosTiros >= MAXIMO_DE_TIROS) {
            tiros[0].setX(getX());
            tiros[0].setY(getY());
            tiros[0].setAngulo(getAngulo());
            tiros[0].setOrientacao(orientacao);
            tiros[0].nascer();
        } else {
            for (int i = 0; i < MAXIMO_DE_TIROS; i++) {
                if (tiros[i].estaMorto()) {      //faz a reciclagem dos tiros

                    tiros[i].setX(getX());
                    tiros[i].setY(getY());
                    tiros[i].setAngulo(getAngulo());
                    tiros[i].setOrientacao(orientacao);
                    tiros[i].nascer();

                    qtosTiros++;
                    break;
                }
            }
        }

    }

    public void perderVida() {
        quantasVidas--;
        getSpt().move(Tela.largura / 2,getSpt().getY());
        getSpt().move(getSpt().getX(),Tela.altura / 2);
        System.out.println("Quantas vidas " + quantasVidas);
    }

    public Tiro[] getTiros() {
        return tiros;
    }

    public int getQuantasVidas() {
        return quantasVidas;
    }

    public int getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(int orientacao) {
        this.orientacao = orientacao;
    }

    public void atualizar() {
        //this.spt.move(this.getX(), this.getY());
//        System.out.println("X E Y = " + this.spt.getX() + " / " + this.spt.getY());

        // Verificar se está dentro da tela.
        //if (getSpt().getX() >= Tela.largura){
        
        //}
            
        qtosTiros = 0;
        for (int i = 0; i < MAXIMO_DE_TIROS; i++) {
            if (!tiros[i].estaMorto()) {
                this.tiros[i].atualizar();
                qtosTiros++;
            } else {
                qtosTiros--;
            }
        }
        if (qtosTiros < 0) {
            qtosTiros = 0;
        }

    }

    public void desenhar(Graphics g) {

        // Percorrer todos os tiros e pintá-los
        for (int i = 0; i < MAXIMO_DE_TIROS; i++) {
            if (tiros[i] != null && !tiros[i].estaMorto()) {
                tiros[i].desenhar(g);
            }
        }

        /*
         int[] pontos = atualizarAng();
         //rotateImage(Image.createImage(IMAGEM_NAVE), (int) getAngulo(), g);
         //        g.drawLine(getX(), getY(), getX() - getRaio(), getY() + getRaio());
         //        g.drawLine(getX() - getRaio(), getY() + getRaio(), getX(), getY() - getRaio());
         //        g.drawLine(getX(), getY() - getRaio(), getX() + getRaio(), getY() + getRaio());
         //        g.drawLine(getX() + getRaio(), getY() + getRaio(), getX(), getY());

         g.drawLine(pontos[0], pontos[1], pontos[0], pontos[1]);

         //        g.drawLine(pontos[0], pontos[1], pontos[0] - getRaio(), pontos[1] + getRaio());
         //        g.drawLine(pontos[0] - getRaio(), pontos[1] + getRaio(), pontos[0], pontos[1] - getRaio());
         //        g.drawLine(pontos[0], pontos[1] - getRaio(), pontos[0] + getRaio(), pontos[1] + getRaio());
         //        g.drawLine(pontos[0] + getRaio(), pontos[1] + getRaio(), pontos[0], pontos[1] + getRaio());

         //g.drawChar('a', pontos[0], pontos[1], 0);
                
         */
    }

    // Método retornará o ponto pertencente a circ
    // int[] ponto = new int[]{X,Y};
    public int[] atualizarAng() {
        double ang = getAngulo();
        System.out.println("Angulo " + ang);

        int novoX = getX();
        int novoY = getY();
        int fatorCorrecaoX = 1;
        int fatorCorrecaoY = -1;

        // Vamos calcular os pontos sabendo a distância entre os 2 pontos = raio
        int quadrante = 0;
        if (ang >= 0 && ang <= 90) // Primeiro Quadrante
        {
            quadrante = 90;
            fatorCorrecaoX = 1;
            fatorCorrecaoY = -1;
        } else if (ang >= 91 && ang <= 180) // Segundo Quadrante
        {
            quadrante = 180;
            fatorCorrecaoX = 1;
            fatorCorrecaoY = -1;
        } else if (ang >= 181 && ang <= 270) // Terceiro Quadrante
        {
            quadrante = 270;
            fatorCorrecaoX = 1;
            fatorCorrecaoY = -1;

        } else if (ang >= 271 && ang <= 360) // Quarto Quadrante
        {
            quadrante = 360;
            fatorCorrecaoX = 1;
            fatorCorrecaoY = -1;
        }
//        System.err.println("quad " + quadrante);
        int angComplementar = (int) (quadrante - ang);
        System.out.println("Angulo complementar " + angComplementar);
        int parteX = (int) (Math.cos(Math.toRadians(angComplementar)) * getRaio()) * fatorCorrecaoX;
        novoX += parteX;
        novoY += (int) (Math.sqrt(getRaio() * getRaio() - parteX * parteX)) * fatorCorrecaoY;

//        System.out.println("XF" + fatorCorrecaoX);
//        System.out.println("YF" + fatorCorrecaoY);
        System.out.println("NovoX Sem Ajuste " + novoX);
        System.out.println("NovoY Sem Ajuste " + novoY);

        return new int[]{novoX, novoY};
    }

}
