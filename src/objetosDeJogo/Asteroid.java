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
    
    // QUAL = 0; Pequeno
    // QUAL = 1; Medio
    // QUAL = 2; Grande
    
    public Asteroid(int posicaoX, int posicaoY, int raio, double angulo,int qual) {
        super(posicaoX, posicaoY, raio, angulo,IMAGEM_ASTEROID);
        IMAGEM_ASTEROID += qual + ".png";
    }
}
