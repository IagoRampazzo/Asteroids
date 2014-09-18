/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosDeJogo;

/**
 *
 * @author u12175
 */
public class Tiro extends ObjetoDoJogo {

    private static final int ALCANCE_TIRO = 100;
    private static final int RAIO_TIRO = 3;         //padrão
    private static final int VELOCIDADE_TIRO = 10;  //
    private static final String IMAGEM_TIRO = "/tiro3.png";
    private int estado; //0 = morto; 1=vivo
    private int orientacao;
    private int xPrimitivo, yPrimitivo;
    private int distanciaPercorrida;

    public Tiro(int posicaoX, int posicaoY, double angulo, int orientacao) {
        super(posicaoX, posicaoY, RAIO_TIRO, angulo, IMAGEM_TIRO);
        this.setVelocidade(VELOCIDADE_TIRO);
        this.estado = 1;
        this.orientacao = orientacao;
        this.xPrimitivo = posicaoX;
        this.yPrimitivo = posicaoY;
        this.distanciaPercorrida = 0;
        this.ALTURA  = 8;
        this.LARGURA = 8;
    }

    public void matarTiro() {
        estado = 0;
    }

    public boolean estaMorto() {
        return estado == 0;
    }

    public void atualizar() {
        
//        if ((Math.abs(getX() - this.xPrimitivo) >= ALCANCE_TIRO) || (Math.abs(getY() - this.yPrimitivo) >= ALCANCE_TIRO)) {
        if (distanciaPercorrida >= ALCANCE_TIRO){ 
           this.matarTiro();
           distanciaPercorrida = 0;
        } else {
            if (orientacao == 0) { // CIMA
                this.setY(getY() - 10);
            }
            if (orientacao == 1) { // BAIXO
                this.setY(getY() + 10);
            }
            if (orientacao == 2) { // DIREITA
                this.setX(getX() + 10);
            }
            if (orientacao == 3) { // ESQUEDA
                this.setX(getX() - 10);
            }
            distanciaPercorrida += 10;
        }
    }

    public int getEstado() {
        return estado;
    }

    public void nascer() {
        this.estado = 1;
    }

    public int getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(int orientacao) {
        this.orientacao = orientacao;
    }

    public int getxPrimitivo() {
        return xPrimitivo;
    }

    public void setxPrimitivo(int xPrimitivo) {
        this.xPrimitivo = xPrimitivo;
    }

    public int getyPrimitivo() {
        return yPrimitivo;
    }

    public void setyPrimitivo(int yPrimitivo) {
        this.yPrimitivo = yPrimitivo;
    }

}
