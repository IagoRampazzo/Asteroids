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

/**
 *
 * @author u12161
 */
public class Menu extends Estado {

    private final String[] menu = {"Jogar", "Ranking", "Ajuda", "Sair"};
    private int opcaoAtual;

    public Menu(Manipulador manip) {
        this.nomeMusica = "/menuMusic.mp3";
        this.manip      = manip;
        try {
            this.fundo.url  = "/fundoMenu.png";
            this.fundo.img  = Image.createImage(this.fundo.url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void desenhar(Graphics g) {
        g.drawImage(this.fundo.img, 0, 0, 0);
        for (int i = 0; i < menu.length; i++){
           if (opcaoAtual == i)
               g.setColor(100, 155, 155);
           else
               g.setColor(255, 255, 255);
           
           g.drawString(menu[i], Tela.largura / 2 - 25, Tela.altura / 2 + i * 20, 0);
        }
    }

    protected void lerTeclado(int tecla) {
        if ((tecla & GameCanvas.DOWN_PRESSED) != 0) {
            opcaoAtual++;
            if (opcaoAtual >= menu.length) {
                opcaoAtual = 0;
            }
        } else if ((tecla & GameCanvas.UP_PRESSED) != 0) {
            opcaoAtual--;
            if (opcaoAtual < 0) {
                opcaoAtual = menu.length - 1;
            }
        } else if ((tecla & GameCanvas.FIRE_PRESSED) != 0){
             manip.setEstadoAtual(1);
        }
    }

    protected void tocarMusica() {

    }

}
