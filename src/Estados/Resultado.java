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
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;

/**
 *
 * @author Fabio
 */
public class Resultado extends Estado {

    private boolean ganhou;
    private final String[] imgResultado = {"/ganhou.png", "/perdeu.png"};

    public Resultado(Manipulador manip, boolean ganhou, LayerManager lm) {
        this.manip = manip;
        this.ganhou = ganhou;
        this.lm = lm;
        int qual = 0;
        if (!ganhou) {
            qual = 1;
        }
        try {
            this.fundo.url = imgResultado[qual];
            this.fundo.img = Image.createImage(this.fundo.url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void desenhar(Graphics g) {
        g.drawImage(fundo.img, 0, 0, 0);
    }

    protected void lerTeclado(int tecla) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (tecla != 0) {
            this.manip.adicionar(new Menu(manip, lm));
            this.manip.setEstadoAtual(this.manip.getEstadoAtual() + 1);
        }
    }

    protected void tocarMusica() {
    }

    protected void atualizar() {
    }

}
