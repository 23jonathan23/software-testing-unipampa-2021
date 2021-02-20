
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
}
