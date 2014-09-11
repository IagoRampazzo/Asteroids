/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManipEstado;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author u12161
 */
public class Manipulador {

    private Estado[] vetManip;  
    private int      estadoAtual;
    private int      qtos;
    
    public Manipulador(int qtos) {
        vetManip    = new Estado[qtos];
        estadoAtual = 0;
        qtos        = 0;
    }
    
    public void adicionar(Estado e){
        if (qtos > 0) // Alterado a forma de adicionar 
            // Utilização da var qtos que representa minha lógica
           if (estadoAtual + 1 == vetManip.length)
               throw new IllegalArgumentException("Limite de estados Atingidos");
           else
               vetManip[estadoAtual + 1] = e;
        else
            vetManip[estadoAtual] = e;
        
        qtos++;
    }
    
    public Estado remover(Estado e){
          Estado removido = null;
          for (int i = 0; i < vetManip.length; i++)
              if (e == vetManip[i]){
                 removido = vetManip[i];
                 if (i + 1 < vetManip.length)
                    vetManip[i] = vetManip[i + 1];
              }
          return removido;
    }
    
    
    public void desenhar(Graphics g){
        vetManip[estadoAtual].desenhar(g);
    }
    
    public void tocarMusica(){
         vetManip[estadoAtual].tocarMusica();
    }
    
    public void lerTeclado(int tecla){
         vetManip[estadoAtual].lerTeclado(tecla);
    }
    
    public Estado getEstado(){
         return vetManip[estadoAtual];
    }
    
    public int getEstadoAtual(){
         return estadoAtual;
    }
    
    public void setEstadoAtual(int estadoAtual){
         this.estadoAtual = estadoAtual;
    }
    
}
