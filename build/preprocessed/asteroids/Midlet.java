/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import Visual.Tela;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;

/**
 * @author u12161
 */
public class Midlet extends MIDlet implements CommandListener {

    private Display display;
    private Command cmdSair;
    private Thread jogo;
    private Tela   tela;

    public void startApp() {
        display = Display.getDisplay(this);
        tela    = new Tela(true);
        jogo    = new Thread(tela);
        jogo.start();
        if (tela != null){
           this.display.setCurrent(tela);
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSair) {
            System.gc();
            destroyApp(true);
            notifyDestroyed();
        }
    }
}
