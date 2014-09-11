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
public class Tiro extends ObjetoDoJogo{
    private static final int RAIO_TIRO = 3;         //padrão
    private static final int VELOCIDADE_TIRO = 10;  //
    private static final String IMAGEM_TIRO = "images/tiro.png";
    
    public Tiro(int posicaoX, int posicaoY, double angulo) {
        super(posicaoX, posicaoY, RAIO_TIRO, angulo,IMAGEM_TIRO);
        this.setVelocidade(VELOCIDADE_TIRO);
    }
}
