package main.java.edu.unipampa.es.rp2.marco1;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Rafael
 */
public abstract class Animal {
    
    protected static int IDADE_PROCRIACAO;
    protected static  int IDADE_MAXIMA;
    protected static double PROBABILIDADE_PROCRIACAO;
    protected static  int TAMANHO_MAXIMO_NINHADA;    
    protected static Random rand = Randomizador.getRandom();

    protected int idade;
    protected boolean vivo;
    protected Localizacao localizacao;
    protected Campo campo;  

    protected abstract void incrementaIdade();
    protected abstract void setMorte();
    protected abstract boolean podeProcriar();
    protected abstract int procria();
    protected abstract void setLocalizacao(Localizacao localizacao);
    protected abstract  Localizacao getLocalizacao();


}
