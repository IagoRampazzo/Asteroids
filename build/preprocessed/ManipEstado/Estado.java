/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManipEstado;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author u12161
 */
public abstract class Estado {

    protected String nomeMusica;
    protected Imagem fundo = new Imagem();
    protected Manipulador manip;

    protected class Imagem {

        public String url = "";
        public Image img;
    
    }

    protected abstract void desenhar(Graphics g);

    protected abstract void lerTeclado(int tecla);

    protected abstract void tocarMusica();

}
