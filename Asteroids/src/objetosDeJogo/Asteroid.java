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
public class Asteroid extends ObjetoDoJogo{
    private int ALCANCE_TIRO = 400;
    private int MAXIMO_DE_TIROS = 6; 
            
    
    public Asteroid(int posicaoX, int posicaoY, int raio, double angulo) {
        super(posicaoX, posicaoY, raio, angulo);
    }
    
}
