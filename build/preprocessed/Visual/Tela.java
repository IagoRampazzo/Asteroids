/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Estados.Fase;
import Estados.Menu;
import ManipEstado.Manipulador;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author u12161
 */
public class Tela extends GameCanvas implements Runnable {

    public static int largura;
    public static int altura;
    private boolean rodando;
    private Manipulador manip;

    public Tela(boolean suppressKeyEvents) {
        super(suppressKeyEvents);

        this.altura     = getHeight();
        this.largura    = getWidth();
        this.rodando    = true;
        
        // Setamos os "Layouts do jogo" (Menu,Ranking,Ajuda,Jogo)
        this.manip      = new Manipulador(2);
        this.manip.adicionar(new Menu(this.manip));
        this.manip.adicionar(new Fase(this.manip));
    }

    public void run() {
        Graphics g = getGraphics();
        while (this.rodando) {
            lerTeclado();
            desenhar(g);
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void desenhar(Graphics g) {
        manip.desenhar(g);
        flushGraphics();
    }

    public void lerTeclado() {
        int tecla = getKeyStates();
        manip.lerTeclado(tecla);
    }

}
