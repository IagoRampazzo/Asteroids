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
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import objetosDeJogo.Asteroid;
import objetosDeJogo.Nave;

/**
 *
 * @author u12161
 */
public class Fase extends Estado {

    private final Nave n;
    private int tempoAnterior;
    private final int fatorDeCorrecao = 20;
    private Asteroid[] grandes;
    private Asteroid[] medios;
    private Asteroid[] pequenos;
    private Vector v;

    public Fase(Manipulador manip, LayerManager lm) {
        this.fundo.url = "/fundo.png";
        try {
            this.fundo.img = Image.createImage(this.fundo.url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.n = new Nave(Tela.largura / 2, Tela.altura / 2, 10, 0);
        this.lm = lm;
        this.v = new Vector(14);

        Asteroid grande1 = new Asteroid(1, 0, 0, 16, new Random().nextInt(360)); // 360 = uma circunferencia
        grande1.setarPosicaoAleatoria(Tela.largura, Tela.altura);

        Asteroid grande2 = new Asteroid(1, 0, 0, 16, new Random().nextInt(360)); // 360 = uma circunferencia
        grande2.setarPosicaoAleatoria(Tela.largura, Tela.altura);

        // Instanciamos o vetor com o max de asteroids possivel
        grandes = new Asteroid[2];
        medios = new Asteroid[4];
        pequenos = new Asteroid[8];

        grandes[0] = grande1;
        grandes[1] = grande2;

        this.v.addElement(grandes[0]);
        this.v.addElement(grandes[1]);

        lm.append(((Asteroid)v.elementAt(0)).getSpt());
        lm.append(((Asteroid)v.elementAt(1)).getSpt());
        lm.append(n.getSpt());
    }

    protected void desenhar(Graphics g) {
        g.drawImage(this.fundo.img, 0, 0, 0);
        lm.paint(g, 0, 0);
        /*n.desenhar(g);
         //        lm.paint(g, 100, 100);

         for (int indiceGrande = 0; indiceGrande < grandes.length; indiceGrande++) {
         if (grandes[indiceGrande] != null && grandes[indiceGrande].isVivo()) {
         grandes[indiceGrande].desenhar(g);
         }
         }
         for (int indiceMedio = 0; indiceMedio < medios.length; indiceMedio++) {
         if (medios[indiceMedio] != null) {
         System.out.println("Medio [" + indiceMedio + "] " + medios[indiceMedio].isVivo());
         }
         if (medios[indiceMedio] != null && medios[indiceMedio].isVivo()) {
         System.out.println("Desenhou o médio");
         medios[indiceMedio].desenhar(g);
         }
         }
         for (int indicePequeno = 0; indicePequeno < pequenos.length; indicePequeno++) {
         if (pequenos[indicePequeno] != null && pequenos[indicePequeno].isVivo()) {
         pequenos[indicePequeno].desenhar(g);
         }
         }*/
    }

    protected void lerTeclado(int tecla) {
        int agora = (int) System.currentTimeMillis();

        if ((tecla & GameCanvas.DOWN_PRESSED) != 0) {
            n.getSpt().setTransform(Sprite.TRANS_ROT180);

            //n.getSpt().move((int) (n.getY() + Nave.VELOCIDADE * ((agora - tempoAnterior) / fatorDeCorrecao)), n.getSpt().getX());
            n.getSpt().setPosition(n.getSpt().getX(), (int) (n.getY() + Nave.VELOCIDADE));
            if (n.getSpt().getY() + n.getALTURA() >= Tela.altura) {
                n.getSpt().setPosition(n.getSpt().getX(), Tela.altura - n.getALTURA());
            }
            n.setOrientacao(1);
        }
        if ((tecla & GameCanvas.UP_PRESSED) != 0) {
            n.getSpt().setTransform(Sprite.TRANS_NONE);

            n.getSpt().setPosition((int) (n.getY() - Nave.VELOCIDADE), n.getSpt().getX());
//            n.getSpt().move((int) (n.getY() - Nave.VELOCIDADE * ((agora - tempoAnterior) / fatorDeCorrecao)), n.getSpt().getX());
            if (n.getSpt().getY() <= 0) {
                n.getSpt().setPosition(n.getSpt().getX(), 0);
            }
            n.setOrientacao(0);

        }
        if ((tecla & GameCanvas.LEFT_PRESSED) != 0) {
            n.getSpt().setTransform(Sprite.TRANS_ROT270);

            n.getSpt().setPosition(n.getSpt().getY(), (int) (n.getX() - Nave.VELOCIDADE));
//            n.getSpt().move(n.getSpt().getY(), (int) (n.getX() - Nave.VELOCIDADE * ((agora - tempoAnterior) / fatorDeCorrecao)));
            if (n.getSpt().getX() <= 0) {
                n.getSpt().setPosition(n.getSpt().getY(), 0);
            }
            n.setOrientacao(3);

        }
        if ((tecla & GameCanvas.RIGHT_PRESSED) != 0) {
            n.getSpt().setTransform(Sprite.TRANS_ROT90);

            n.getSpt().setPosition(n.getSpt().getY(), (int) (n.getX() + Nave.VELOCIDADE));
//            n.getSpt().move(n.getSpt().getY(), (int) (n.getX() + Nave.VELOCIDADE * ((agora - tempoAnterior) / fatorDeCorrecao)));
            if (n.getSpt().getX() >= Tela.largura - n.getLARGURA()) {
                n.getSpt().setPosition(n.getSpt().getY(), Tela.largura - n.getLARGURA());
            }
            n.setOrientacao(2);

        }
        if ((tecla & GameCanvas.FIRE_PRESSED) != 0) {
            n.atirar();
        }

        n.atualizar();
        //n.getSpt().move(0, 0);
        this.tempoAnterior = agora;
    }

    protected void tocarMusica() {

    }

    protected void atualizar() {
        //Atualizar todos os objetos da fase

        //Atualizar nave
        for (int i = 0; i < v.size(); i++) {
            Asteroid a = (Asteroid) v.elementAt(i);
            if (a != null) {
                a.atualizar();
            }
        }
        n.atualizar();
        // Atualizar os 3 vetores de asteroids
        /*for (int indiceGrande = 0; indiceGrande < grandes.length; indiceGrande++) {
         if (grandes[indiceGrande] != null && grandes[indiceGrande].isVivo()) {
         //                System.out.println("Indice Grande : " + indiceGrande);
         grandes[indiceGrande].atualizar();
         if (grandes[indiceGrande].getSpt().collidesWith(n.getSpt(), true)) {
         n.perderVida();
         Asteroid[] novo = grandes[indiceGrande].morrer();
         for (int i = 0; i < novo.length; i++) {
         medios[i] = novo[i];
         System.out.println("Adicionou");
         }
         }
         }
         }
         for (int indiceMedio = 0; indiceMedio < medios.length; indiceMedio++) {
         if (medios[indiceMedio] != null && medios[indiceMedio].isVivo()) {
         System.out.println("TESTE");
         medios[indiceMedio].atualizar();
         if (grandes[indiceMedio].getSpt().collidesWith(n.getSpt(), true)) {
         n.perderVida();
         }
         }
         }
         for (int indicePequeno = 0; indicePequeno < pequenos.length; indicePequeno++) {
         if (pequenos[indicePequeno] != null && pequenos[indicePequeno].isVivo()) {
         pequenos[indicePequeno].atualizar();

         if (grandes[indicePequeno].getSpt().collidesWith(n.getSpt(), true)) {
         n.perderVida();
         }
         }
         }*/

        if (n.getQuantasVidas() <= 0) {
            System.out.println("SAIU DA FASE");
            manip.setEstadoAtual(0);
        }
        n.atualizar();
    }

}
