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
import objetosDeJogo.Nave;

/**
 *
 * @author u12161
 */
public class Fase extends Estado{
    
   private Nave n; 
    
   public Fase(Manipulador manip){
      this.fundo.url = "/fundo.png";
       try {
           this.fundo.img = Image.createImage(this.fundo.url);
       } catch (IOException ex) {
           ex.printStackTrace();
       }
       
       this.n = new Nave(Tela.largura /2, Tela.altura / 2, 10, 0);
   }

    protected void desenhar(Graphics g) {
       g.drawImage(this.fundo.img, 0, 0, 0);
       n.desenhar(g);
    }

    protected void lerTeclado(int tecla) {
    
    }

    protected void tocarMusica() {
    
    }

}
