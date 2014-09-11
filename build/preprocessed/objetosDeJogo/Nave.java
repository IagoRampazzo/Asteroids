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
public class Nave extends ObjetoDoJogo{
    private static final int ALCANCE_TIRO = 400;
    private static final int MAXIMO_DE_TIROS = 8;
    private static final int VIDA_INICIAL = 3;
    private static final String IMAGEM_NAVE = "/nave.png";
    private Tiro tiros[];
    private int tiroAtual;
    private int quantasVidas;

    public Nave(int posicaoX, int posicaoY, int raio, double angulo) {
        super(posicaoX, posicaoY, raio, angulo, IMAGEM_NAVE);
        tiros = new Tiro[MAXIMO_DE_TIROS];          //haverá, no máximo 8 tiros (V 1.0)
        tiroAtual=0;
        quantasVidas = VIDA_INICIAL;
    }
    
    public void atirar(){
        Tiro novoTiro = new Tiro(this.getX(),this.getY(),this.getAngulo());
        tiros[tiroAtual] = novoTiro;    
        tiroAtual++;
        if (tiroAtual>MAXIMO_DE_TIROS)      //faz a reciclagem dos tiros, possui o máximo possível
            tiroAtual = 0;
    }
    
    public void perderVida(){
        quantasVidas--;
    }
    
    public Tiro[] getTiros(){
        return tiros;
    }
    
    public int getQuantasVidas(){
        return quantasVidas;
    }
    
}
