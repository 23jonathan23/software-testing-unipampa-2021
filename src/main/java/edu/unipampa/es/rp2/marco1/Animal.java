package main.java.edu.unipampa.es.rp2.marco1;

import java.util.Random;

/**
 *
 * @author Rafael
 */
public class Animal {
    
    protected static int IDADE_PROCRIACAO;
    protected static  int IDADE_MAXIMA;
    protected static double PROBABILIDADE_PROCRIACAO;
    protected static  int TAMANHO_MAXIMO_NINHADA;    
    protected static Random rand = Randomizador.getRandom();

    protected int idade;
    protected boolean vivo;
    protected Localizacao localizacao;
    protected Campo campo;  
}
