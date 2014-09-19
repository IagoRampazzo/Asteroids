/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Estados.Menu;
import ManipEstado.Manipulador;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;

/**
 *
 * @author u12161
 */
public class Tela extends GameCanvas implements Runnable {

    public static int largura;
    public static int altura;
    private boolean rodando;
    private Manipulador manip;
    private LayerManager lm;

    public Tela(boolean suppressKeyEvents) {
        super(suppressKeyEvents);

        this.altura     = getHeight();
        this.largura    = getWidth();
        this.rodando    = true;
        
        // Setamos os "Layouts do jogo" (Menu,Ranking,Ajuda,Jogo)
        this.lm = new LayerManager();
        this.lm.setViewWindow(0, 0, largura, altura);
        this.manip = new Manipulador(50);
        this.manip.adicionar(new Menu(this.manip,lm));
    }

    public void run() {
        Graphics g = getGraphics();
        while (this.rodando) {
            if (manip.getEstadoAtual() < 0)
                this.rodando = false;
            lerTeclado();
            desenhar(g);
            atualizar();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void desenhar(Graphics g) {
        g.fillRect(0, 0, 240, 320);
        manip.desenhar(g);
        flushGraphics();
    }

    public void lerTeclado() {
        int tecla = getKeyStates();
        manip.lerTeclado(tecla);
    }

    public void atualizar(){
        manip.atualizar();
    }
    
}
