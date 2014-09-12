/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import ManipEstado.Estado;
import ManipEstado.Manipulador;
import Visual.Tela;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import objetosDeJogo.Nave;

/**
 *
 * @author u12161
 */
public class Fase extends Estado {

    private final Nave n;
    private int tempoAnterior;
    private final int fatorDeCorrecao = 30;

    public Fase(Manipulador manip) {
        this.fundo.url = "/fundo.png";
        try {
            this.fundo.img = Image.createImage(this.fundo.url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.n = new Nave(Tela.largura / 2, Tela.altura / 2, 10, 0);
    }

    protected void desenhar(Graphics g) {
        g.drawImage(this.fundo.img, 0, 0, 0);
        n.desenhar(g);
        this.tempoAnterior = (int) System.currentTimeMillis();
    }

    protected void lerTeclado(int tecla) {
        int agora = (int) System.currentTimeMillis();

        if ((tecla & GameCanvas.DOWN_PRESSED) != 0) {
            n.setY((int) (n.getY() + Nave.VELOCIDADE * ((agora - tempoAnterior) / fatorDeCorrecao)));
            if (n.getY() + Nave.ALTURA >= Tela.altura)
                n.setY(Tela.altura - Nave.ALTURA);
            n.setMovendo(true);
            
        } else if ((tecla & GameCanvas.UP_PRESSED) != 0) {
            n.setY((int) (n.getY() - Nave.VELOCIDADE * ((agora - tempoAnterior) / fatorDeCorrecao)));
            if (n.getY() <= 0)
                n.setY(0);
            n.setMovendo(true);

        } else if ((tecla & GameCanvas.LEFT_PRESSED) != 0) {
            n.setAngulo(n.getAngulo() - 2);
            
        } else if ((tecla & GameCanvas.RIGHT_PRESSED) != 0) {
            n.setAngulo(n.getAngulo() + 2);
            
        } else if ((tecla & GameCanvas.FIRE_PRESSED) != 0) {
            n.atirar();
            
        }else
            n.setMovendo(false);
        
        n.atualizar();
        this.tempoAnterior = agora;
    }

    protected void tocarMusica() {

    }

}
