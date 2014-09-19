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
import objetosDeJogo.Tiro;

/**
 *
 * @author u12161
 */
public class Fase extends Estado {

    private final Nave n;
    private Vector v;
    private boolean perdeu;

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
        this.manip = manip;
        this.perdeu = false;

        Asteroid grande1 = new Asteroid(1, 0, 0, 16, new Random().nextInt(360)); // 360 = uma circunferencia
        grande1.setarPosicaoAleatoria(Tela.largura, Tela.altura);

        Asteroid grande2 = new Asteroid(1, 0, 0, 16, new Random().nextInt(360)); // 360 = uma circunferencia
        grande2.setarPosicaoAleatoria(Tela.largura, Tela.altura);

        this.v.addElement(grande1);
        this.v.addElement(grande2);

        lm.append(((Asteroid) v.elementAt(0)).getSpt());
        lm.append(((Asteroid) v.elementAt(1)).getSpt());
        lm.append(n.getSpt());
    }

    protected void desenhar(Graphics g) {
        g.drawImage(this.fundo.img, 0, 0, 0);
        lm.paint(g, 0, 0);
        if (perdeu) {
            manip.limparEstados();
            manip.adicionar(new Resultado(manip, false, lm));
            manip.setEstadoAtual(manip.getEstadoAtual() + 1);
        }
    }

    protected void lerTeclado(int tecla) {

        if ((tecla & GameCanvas.DOWN_PRESSED) != 0) {
            n.getSpt().setTransform(Sprite.TRANS_ROT180);

            n.getSpt().move(0, (int) Nave.VELOCIDADE);

            if (n.getSpt().getY() > Tela.altura - n.getALTURA()) {
                n.getSpt().setPosition(n.getSpt().getX(), Tela.altura - n.getALTURA());
            }
            n.setOrientacao(1);
        }
        if ((tecla & GameCanvas.UP_PRESSED) != 0) {
            n.getSpt().setTransform(Sprite.TRANS_NONE);

            n.getSpt().move(0, (int) -Nave.VELOCIDADE);

            if (n.getSpt().getY() < 0) {
                n.getSpt().setPosition(n.getSpt().getX(), 0);
            }
            n.setOrientacao(0);

        }
        if ((tecla & GameCanvas.LEFT_PRESSED) != 0) {
            n.getSpt().setTransform(Sprite.TRANS_ROT270);

            n.getSpt().move((int) -Nave.VELOCIDADE, 0);

            if (n.getSpt().getX() < 0) {
                n.getSpt().setPosition(0, n.getSpt().getY());
            }
            n.setOrientacao(3);

        }
        if ((tecla & GameCanvas.RIGHT_PRESSED) != 0) {
            n.getSpt().setTransform(Sprite.TRANS_ROT90);

            n.getSpt().move((int) Nave.VELOCIDADE, 0);

            if (n.getSpt().getX() > Tela.largura - n.getLARGURA()) {
                n.getSpt().setPosition(Tela.largura - n.getLARGURA(), n.getSpt().getY());
            }
            n.setOrientacao(2);

        }
        if ((tecla & GameCanvas.FIRE_PRESSED) != 0) {
            n.atirar();
        }

        n.atualizar();
    }

    protected void tocarMusica() {

    }

    protected void atualizar() {
        //Atualizar todos os objetos da fase

        //Atualizar nave
        if (n != null) {
            Tiro[] tiros = null;
            try {
                tiros = n.getTiros();

                if (v != null) {
                    //colisao de tiros com asteroids
                    for (int i = 0; i < tiros.length; i++) {
                        Tiro tiroAtual = tiros[i];
                        boolean existe = false;
                        for (int indice = 0; indice < lm.getSize(); ++indice) {
                            if (lm.getLayerAt(indice).equals(tiroAtual.getSpt())) {
                                existe = true;
                            }
                        }
                        if (!existe && !tiroAtual.estaMorto()) {
                            lm.append(tiroAtual.getSpt());
                        } else if (tiroAtual.estaMorto()) {
                            lm.remove(tiroAtual.getSpt());
                        }

                        for (int j = 0; j < v.size(); j++) {
                            Asteroid asteroidAtual = (Asteroid) v.elementAt(j);
                            if (tiroAtual != null && !tiroAtual.estaMorto()) {
                                if (tiroAtual.getSpt().collidesWith(asteroidAtual.getSpt(), true)) {
                                    //if (tiroAtual.getX() <= (asteroidAtual.getX() + asteroidAtual.getLARGURA() / 2)
                                    //        && tiroAtual.getX() >= (asteroidAtual.getX() - asteroidAtual.getLARGURA() / 2)) {
                                    //    if (tiroAtual.getY() <= (asteroidAtual.getY() + asteroidAtual.getALTURA() / 2)
                                    //            && tiroAtual.getY() >= (asteroidAtual.getY() - asteroidAtual.getALTURA() / 2)) {
                                    //colidiu
                                    tiroAtual.matarTiro();
                                    Asteroid[] filhos = asteroidAtual.morrer();

                                    v.removeElement(asteroidAtual); //Asteroid pai morreu
                                    lm.remove(asteroidAtual.getSpt());
                                    if (filhos != null) {
                                        for (int k = 0; k < filhos.length; k++) //asteroids filhos são adicionados
                                        {
                                            v.addElement(filhos[k]);
                                            lm.append(filhos[k].getSpt());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                for (int i = 0; i < v.size(); ++i) {
                    Asteroid a = (Asteroid) v.elementAt(i);
                    if (a != null) {
                        a.atualizar();
                        if (a.getSpt().collidesWith(n.getSpt(), true)) {
                            Asteroid[] ast = a.morrer();
                            a.getSpt().setPosition(-100, -100);
                            v.removeElement(a);
                            lm.remove(a.getSpt());
                            if (ast == null) {
                                System.out.println("ERRO");
                            } else {
                                for (int indice = 0; indice < ast.length; indice++) {
                                    v.addElement(ast[indice]);
                                    lm.append(ast[indice].getSpt());
                                }
                            }
                            n.perderVida();
                        }
                    }
                }

                if (n.getQuantasVidas() <= 0) {
                    System.out.println("SAIU DA FASE");
                    for (int i = 0; i < lm.getSize(); ++i) {
                        lm.remove(lm.getLayerAt(i));
                    }
                    perdeu = true;
                } else if (v.size() <= 0) { // Ganhou
                    System.out.println("GANHOU");
                    manip.limparEstados();
                    for (int i = 0; i < lm.getSize(); ++i) {
                        lm.remove(lm.getLayerAt(i));
                    }
                    manip.adicionar(new Resultado(manip, true, lm));
                    manip.setEstadoAtual(manip.getEstadoAtual() + 1);
                }

                n.atualizar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
