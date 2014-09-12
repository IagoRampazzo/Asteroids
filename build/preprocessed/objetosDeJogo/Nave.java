/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosDeJogo;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author u12175
 */
public class Nave extends ObjetoDoJogo {

    private static final int ALCANCE_TIRO = 400;
    private static final int MAXIMO_DE_TIROS = 8;
    private static final int VIDA_INICIAL = 3;
    public static final int LARGURA = 45;
    public static final int ALTURA = 47;
    public static final double VELOCIDADE = 1;
    private static final String IMAGEM_NAVE = "/nave.png";
    private Tiro tiros[];
    private int tiroAtual;
    private int quantasVidas;
    private final int[] setores = {0, 55, 110, 165, 220, 275, 325, 359};

    public Nave(int posicaoX, int posicaoY, int raio, double angulo) {
        super(posicaoX, posicaoY, raio, angulo, IMAGEM_NAVE);
        tiros = new Tiro[MAXIMO_DE_TIROS];          //haverá, no máximo 8 tiros (V 1.0)
        tiroAtual = 0;
        quantasVidas = VIDA_INICIAL;
    }

    public void atirar() {
        Tiro novoTiro = new Tiro(this.getX(), this.getY(), this.getAngulo());
        tiros[tiroAtual] = novoTiro;
        tiroAtual++;
        if (tiroAtual > MAXIMO_DE_TIROS) //faz a reciclagem dos tiros, possui o máximo possível
        {
            tiroAtual = 0;
        }
    }

    public void perderVida() {
        quantasVidas--;
    }

    public Tiro[] getTiros() {
        return tiros;
    }

    public int getQuantasVidas() {
        return quantasVidas;
    }

    public void atualizar() {
        this.spt.setPosition(this.getX(), this.getY());
    }

    public void desenhar(Graphics g) {
        verificarAngulo();
        //rotateImage(Image.createImage(IMAGEM_NAVE), (int) getAngulo(), g);
        g.drawLine(getX(), getY(), getX() - getRaio(), getY() + getRaio());
        g.drawLine(getX() - getRaio(), getY() + getRaio(), getX(), getY() - getRaio());
        g.drawLine(getX(), getY() - getRaio(), getX() + getRaio(), getY() + getRaio());
        g.drawLine(getX() + getRaio(), getY() + getRaio(), getX(), getY());
    }

    private void verificarAngulo() {
        for (int indice = 0; indice < setores.length; indice++) {
            if (indice + 1 < setores.length) { // se n for o ultimo.
                if (setores[indice] <= getAngulo() && getAngulo() <= setores[indice + 1]) // se o indice tiver entre 0 e 1 
                {
                    switch (indice) {
                        case 0: // entre 0 e 55 graus
                            if (isMovendo()) {
                                setX(getX() + 3);
                                setY(getY() - 3);
                            }
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                    }
                }
            }
        }
    }

    private void rotateImage(Image src, float angle, Graphics g) {
        int sw = src.getWidth();
        int sh = src.getHeight();
        int[] srcData = new int[sw * sh];

        src.getRGB(srcData, 0, sw, 0, 0, sw, sh);
        int[] dstData = new int[sw * sh];

        double rads = angle * Math.PI / 180.f;
        float sa = (float) Math.sin(rads);
        float ca = (float) Math.cos(rads);
        int isa = (int) (256 * sa);
        int ica = (int) (256 * ca);

        int my = -(sh >> 1);
        for (int i = 0; i < sh; i++) {
            int mx = -(sw >> 1);
            for (int j = 0; j < sw; j++) {
                int srcx = (mx * ica + my * isa) >> 8;
                int srcy = (-mx * isa + my * ica) >> 8;

                srcx += sw >> 1;
                srcy += sh >> 1;

                if (srcx < 0) {
                    srcx = 0;
                }
                if (srcy < 0) {
                    srcy = 0;
                }
                if (srcx > sw - 1) {
                    srcx = sw - 1;
                }
                if (srcy > sh - 1) {
                    srcy = sh - 1;
                }

                dstData[j + i * sw] = srcData[srcx + srcy * sw];

                mx++;
            }
            my++;
        }

        g.drawRGB(dstData, 0, sw, getX(), getY(), sw, sh, true);
    }
}
