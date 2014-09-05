/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroids;

import javax.microedition.midlet.*;
import objetosDeJogo.ObjetoDoJogo;

/**
 * @author u12175
 */
public class Midlet extends MIDlet {

    public void startApp() {
        ObjetoDoJogo obj = new ObjetoDoJogo(50,50,50,Math.PI/2);
        System.out.println(obj.getAngulo());
        obj.girar(Math.PI*2);
        System.out.println(obj.getAngulo());
        obj.girar(-Math.PI*2);
        System.out.println(obj.getAngulo());
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
