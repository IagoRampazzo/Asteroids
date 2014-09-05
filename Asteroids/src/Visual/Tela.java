/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visual;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.m3g.Graphics3D;

/**
 *
 * @author u12161
 */
public class Tela extends GameCanvas implements Runnable{

    private class Imagem{
      protected String url = "";
      protected Image img;
    }
    
    private int     largura; 
    private int     altura; 
    private Imagem  fundo;
    private boolean rodando;
    
    public Tela(boolean suppressKeyEvents) {
        super(suppressKeyEvents);
        
        try {
            this.fundo.img = Image.createImage(this.fundo.url);
            this.fundo.url = "/fundo.png";
        } catch (IOException ex) {
            System.out.println("Erro ao carregar a imagem " + this.fundo.url);
            ex.printStackTrace();
        }
        
        this.altura  = getHeight();
        this.largura = getWidth();
        this.rodando = true;
    }

    public void run() {
         while (this.rodando){
             
         }
    }
    
    public void desenhar(Graphics g){
        g.drawImage(fundo.img, 0, 0, 0);
    } 
    
}
