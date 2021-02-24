package edu.unipampa.es.rp2.marco1;

import java.util.Random;

public abstract class Animal {

    protected int IDADE_PROCRIACAO;
    protected int IDADE_MAXIMA;
    protected double PROBABILIDADE_PROCRIACAO;
    protected int TAMANHO_MAXIMO_NINHADA;
    protected static Random RAND = Randomizador.getRandom();

    protected int idade;
    protected boolean vivo;
    protected Localizacao localizacao;
    protected Campo campo;

    public Animal() {
        vivo = true;
        idade = 0;
    }

    protected abstract void incrementaIdade();

    protected abstract void setMorte();

    protected abstract boolean podeProcriar();

    protected abstract int procria();

    protected abstract void setLocalizacao(Localizacao localizacao);

    protected abstract Localizacao getLocalizacao();

}
